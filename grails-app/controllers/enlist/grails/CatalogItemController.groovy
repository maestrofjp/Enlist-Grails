package enlist.grails

import org.springframework.dao.DataIntegrityViolationException

class CatalogItemController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [catalogItemInstanceList: CatalogItem.list(params), catalogItemInstanceTotal: CatalogItem.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [catalogItemInstance: new CatalogItem(params)]
                break
            case 'POST':
                def catalogItemInstance = new CatalogItem(params)
                if (!catalogItemInstance.save(flush: true)) {
                    render view: 'create', model: [catalogItemInstance: catalogItemInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), catalogItemInstance.id])
                redirect action: 'show', id: catalogItemInstance.id
                break
        }
    }

    def show() {
        def catalogItemInstance = CatalogItem.get(params.id)
        if (!catalogItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), params.id])
            redirect action: 'list'
            return
        }

        [catalogItemInstance: catalogItemInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def catalogItemInstance = CatalogItem.get(params.id)
                if (!catalogItemInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), params.id])
                    redirect action: 'list'
                    return
                }

                [catalogItemInstance: catalogItemInstance]
                break
            case 'POST':
                def catalogItemInstance = CatalogItem.get(params.id)
                if (!catalogItemInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (catalogItemInstance.version > version) {
                        catalogItemInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'catalogItem.label', default: 'CatalogItem')] as Object[],
                                "Another user has updated this CatalogItem while you were editing")
                        render view: 'edit', model: [catalogItemInstance: catalogItemInstance]
                        return
                    }
                }

                catalogItemInstance.properties = params

                if (!catalogItemInstance.save(flush: true)) {
                    render view: 'edit', model: [catalogItemInstance: catalogItemInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), catalogItemInstance.id])
                redirect action: 'show', id: catalogItemInstance.id
                break
        }
    }

    def delete() {
        def catalogItemInstance = CatalogItem.get(params.id)
        if (!catalogItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), params.id])
            redirect action: 'list'
            return
        }

        try {
            catalogItemInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'catalogItem.label', default: 'CatalogItem'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
