package enlist.grails

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import static javax.servlet.http.HttpServletResponse.*

class ActivityController {

    static final int SC_UNPROCESSABLE_ENTITY = 422

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		response.setIntHeader('X-Pagination-Total', Activity.count())
		render Activity.list(params) as JSON
    }

    def save() {
        def activityInstance = new Activity(request.JSON)
        def responseJson = [:]
        if (activityInstance.save(flush: true)) {
            response.status = SC_CREATED
            responseJson.id = activityInstance.id
            responseJson.message = message(code: 'default.created.message', args: [message(code: 'activity.label', default: 'Activity'), activityInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = activityInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }
        render responseJson as JSON
    }

    def get() {
        def activityInstance = Activity.get(params.id)
        if (activityInstance) {
			render activityInstance as JSON
        } else {
			notFound params.id
		}
    }

    def update() {
        def activityInstance = Activity.get(params.id)
        if (!activityInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]

        if (request.JSON.version != null) {
            if (activityInstance.version > request.JSON.version) {
				response.status = SC_CONFLICT
				responseJson.message = message(code: 'default.optimistic.locking.failure',
						args: [message(code: 'activity.label', default: 'Activity')],
						default: 'Another user has updated this Activity while you were editing')
				cache false
				render responseJson as JSON
				return
            }
        }

        activityInstance.properties = request.JSON

        if (activityInstance.save(flush: true)) {
            response.status = SC_OK
            responseJson.id = activityInstance.id
            responseJson.message = message(code: 'default.updated.message', args: [message(code: 'activity.label', default: 'Activity'), activityInstance.id])
        } else {
            response.status = SC_UNPROCESSABLE_ENTITY
            responseJson.errors = activityInstance.errors.fieldErrors.collectEntries {
                [(it.field): message(error: it)]
            }
        }

        render responseJson as JSON
    }

    def delete() {
        def activityInstance = Activity.get(params.id)
        if (!activityInstance) {
            notFound params.id
            return
        }

        def responseJson = [:]
        try {
            activityInstance.delete(flush: true)
            responseJson.message = message(code: 'default.deleted.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
        } catch (DataIntegrityViolationException e) {
            response.status = SC_CONFLICT
            responseJson.message = message(code: 'default.not.deleted.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
        }
        render responseJson as JSON
    }

    private void notFound(id) {
        response.status = SC_NOT_FOUND
        def responseJson = [message: message(code: 'default.not.found.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])]
        render responseJson as JSON
    }
}
