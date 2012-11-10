package enlist.grails

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.StringUtils
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ActivityController extends AbstractBaseController {
    /**
     * Can not declare secure tag on the class level because ROLE_VOLUNTEER also need to access the controller
     * (to read & sign up).
     * @return
     */
    protected def getAdminRoles() {[Role.CHAPTER_ADMIN, Role.ADMIN]}

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [activityInstanceList: Activity.list(params), activityInstanceTotal: Activity.count(), isAdmin : hasAdminAccess()]
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

        [activityInstance: activityInstance, canVolunteer : canVolunteer, isAdmin : hasAdminAccess(),
                hasSignUp : ActivitySignUp.get(loginUser.id, activityInstance.id)]
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
}
