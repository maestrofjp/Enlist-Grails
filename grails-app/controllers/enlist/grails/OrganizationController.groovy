package enlist.grails

import org.springframework.dao.DataIntegrityViolationException

class OrganizationController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [organizationInstanceList: Organization.list(params), organizationInstanceTotal: Organization.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[organizationInstance: new Organization(params)]
			break
		case 'POST':
	        def organizationInstance = new Organization(params)
	        if (!organizationInstance.save(flush: true)) {
	            render view: 'create', model: [organizationInstance: organizationInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'organization.label', default: 'Organization'), organizationInstance.id])
	        redirect action: 'show', id: organizationInstance.id
			break
		}
    }

    def show() {
        def organizationInstance = Organization.get(params.id)
        if (!organizationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
            redirect action: 'list'
            return
        }

        [organizationInstance: organizationInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def organizationInstance = Organization.get(params.id)
	        if (!organizationInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [organizationInstance: organizationInstance]
			break
		case 'POST':
	        def organizationInstance = Organization.get(params.id)
	        if (!organizationInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (organizationInstance.version > version) {
	                organizationInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'organization.label', default: 'Organization')] as Object[],
	                          "Another user has updated this Organization while you were editing")
	                render view: 'edit', model: [organizationInstance: organizationInstance]
	                return
	            }
	        }

	        organizationInstance.properties = params

	        if (!organizationInstance.save(flush: true)) {
	            render view: 'edit', model: [organizationInstance: organizationInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'organization.label', default: 'Organization'), organizationInstance.id])
	        redirect action: 'show', id: organizationInstance.id
			break
		}
    }

    def delete() {
        def organizationInstance = Organization.get(params.id)
        if (!organizationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
            redirect action: 'list'
            return
        }

        try {
            organizationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
