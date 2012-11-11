package enlist.grails

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.StringUtils
import enlist.grails.util.DateParser
import org.compass.core.CompassQuery

class ActivityController extends AbstractBaseController {
    /**
     * Can not declare secure tag on the class level because ROLE_VOLUNTEER also need to access the controller
     * (to read & sign up).
     * @return
     */
    protected def getRolesWithWriteAccess() {[Role.CHAPTER_ADMIN, Role.ADMIN]}

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [activityInstanceList: Activity.list(params), activityInstanceTotal: Activity.count(), isAdmin : hasControllerWriteAccess()]
    }

    @Secured(['ROLE_CHAPTER_ADMIN', 'ROLE_ADMIN'])
    def create() {
        def activityInstance = new Activity()
        bindData(activityInstance, params)
        if (StringUtils.isBlank(activityInstance.locationAddress?.address1))  activityInstance.locationAddress = null
		switch (request.method) {
		case 'GET':
        	[activityInstance: activityInstance]
			break
		case 'POST':
            // no need to manually parse the date input. it has been handled in custom property editor.
//	        def activityInstance = new Activity(params)
//			// Concatenate date/time and add to properties
//			activityInstance.properties.startDate = new Date().parse('MM/dd/yyyy h:mm a', params._startDate + ' ' + params._startTime)
//			activityInstance.properties.endDate = new Date().parse('MM/dd/yyyy h:mm a', params._endDate + ' ' + params._endTime)

	        if (!activityInstance.save(flush: true)) {
	            render view: 'create', model: [activityInstance: activityInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'activity.label', default: 'Activity'), activityInstance.id])
	        redirect action: 'show', id: activityInstance.id
			break
		}
    }

    def grailsApplication
    def show() {
        def activityInstance = Activity.get(params.id)
        if (!activityInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
            redirect action: 'list'
            return
        }
        boolean canVolunteer =  loginUser?.checkVolunteer()
        if(!grailsApplication.config.rule?.activity?.allowRegistrationAfterEndDate)
            if (activityInstance.endDate && activityInstance.endDate.time <= new Date().time)
                canVolunteer = false // past event.

//        [activityInstance: activityInstance, canVolunteer : canVolunteer, isAdmin : hasControllerWriteAccess(),
//                hasSignUp : loginUser ? ActivitySignUp.get(loginUser.id, activityInstance.id) : null]
        if(loginUser) {
            ActivitySignUp signUp = ActivitySignUp.findByActivityAndUser(activityInstance, loginUser)
            return [activityInstance: activityInstance, canVolunteer : canVolunteer, isAdmin : hasControllerWriteAccess(),
                    hasSignUp : signUp != null, reminderAt : signUp?.reminderAt ]
        } else {
            return [activityInstance: activityInstance, canVolunteer : false, isAdmin : false, hasSignUp : false,
                    reminderAt : null]
        }
    }
    @Secured(['ROLE_VOLUNTEER'])
    def changeReminder() {
        println "change reminder ${params}"
        Date reminderAt = null
        if(StringUtils.equals('on',params["remindMe"]) &&
                params["reminderDate_date"] && params["reminderDate_time"]) {
            reminderAt = DateParser.parseDateTimeDefault("${params.reminderDate_date} ${params.reminderDate_time}")
        }
        def activityInstance = Activity.get(params.id)
        ActivitySignUp signUp = ActivitySignUp.findByActivityAndUser(activityInstance, loginUser)
        signUp.reminderAt = reminderAt
        signUp.save(failOnError: true, validate: false)
        redirect action: 'show', id: activityInstance.id
    }
    @Secured(['ROLE_VOLUNTEER'])
    def signUp() {
        def activityInstance = Activity.get(params.id)
        ActivitySignUp.create(loginUser, activityInstance, true)
        redirect action: 'show', id: activityInstance.id
    }
    @Secured(['ROLE_VOLUNTEER'])
    def cancelSignUp() {
        def activityInstance = Activity.get(params.id)
        ActivitySignUp.remove(loginUser, activityInstance, true)
        redirect action: 'show', id: activityInstance.id
    }

    @Secured(['ROLE_CHAPTER_ADMIN', 'ROLE_ADMIN'])
    def edit() {
		switch (request.method) {
		case 'GET':
	        def activityInstance = Activity.get(params.id)
	        if (!activityInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [activityInstance: activityInstance]
			break
		case 'POST':
	        def activityInstance = Activity.get(params.id)
	        if (!activityInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (activityInstance.version > version) {
	                activityInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'activity.label', default: 'Activity')] as Object[],
	                          "Another user has updated this Activity while you were editing")
	                render view: 'edit', model: [activityInstance: activityInstance]
	                return
	            }
	        }

	        activityInstance.properties = params
            if (StringUtils.isBlank(activityInstance.locationAddress?.address1))  activityInstance.locationAddress = null

	        if (!activityInstance.save(flush: true)) {
	            render view: 'edit', model: [activityInstance: activityInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'activity.label', default: 'Activity'), activityInstance.id])
	        redirect action: 'show', id: activityInstance.id
			break
		}
    }

    @Secured(['ROLE_CHAPTER_ADMIN', 'ROLE_ADMIN'])
    def delete() {
        def activityInstance = Activity.get(params.id)
        if (!activityInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
            redirect action: 'list'
            return
        }

        try {
            activityInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
            redirect action: 'show', id: params.id
        }
    }

    def search() {
        String keywords =params.q ? "${params.q}" : null
        if(StringUtils.isEmpty(keywords)) {
            render(view: "list", model: [activityInstanceList: Activity.list(params), activityInstanceTotal: Activity.count(),
                    isAdmin: hasControllerWriteAccess()])
            return
        }
        def searchClosure = {
            queryString(keywords)
            sort(CompassQuery.SortImplicitType.SCORE)
        }
        def searchResults = Activity.search(searchClosure, [offset : params.offset ?: 0, max : params.max ?: 10])
        render(view: "list", model: [activityInstanceList: searchResults.results, activityInstanceTotal: searchResults.total,
                isAdmin: hasControllerWriteAccess()])
    }
}
