package enlist.grails

import org.springframework.dao.DataIntegrityViolationException

class StatusController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [statusInstanceList: Status.list(params), statusInstanceTotal: Status.count()]
    }

    def create() {
        [statusInstance: new Status(params)]
    }

    def save() {
        def statusInstance = new Status(params)
        if (!statusInstance.save(flush: true)) {
            render(view: "create", model: [statusInstance: statusInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'status.label', default: 'Status'), statusInstance.id])
        redirect(action: "show", id: statusInstance.id)
    }

    def show(Long id) {
        def statusInstance = Status.get(id)
        if (!statusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), id])
            redirect(action: "list")
            return
        }

        [statusInstance: statusInstance]
    }

    def edit(Long id) {
        def statusInstance = Status.get(id)
        if (!statusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), id])
            redirect(action: "list")
            return
        }

        [statusInstance: statusInstance]
    }

    def update(Long id, Long version) {
        def statusInstance = Status.get(id)
        if (!statusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (statusInstance.version > version) {
                statusInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'status.label', default: 'Status')] as Object[],
                          "Another user has updated this Status while you were editing")
                render(view: "edit", model: [statusInstance: statusInstance])
                return
            }
        }

        statusInstance.properties = params

        if (!statusInstance.save(flush: true)) {
            render(view: "edit", model: [statusInstance: statusInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'status.label', default: 'Status'), statusInstance.id])
        redirect(action: "show", id: statusInstance.id)
    }

    def delete(Long id) {
        def statusInstance = Status.get(id)
        if (!statusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), id])
            redirect(action: "list")
            return
        }

        try {
            statusInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'status.label', default: 'Status'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'status.label', default: 'Status'), id])
            redirect(action: "show", id: id)
        }
    }
}
