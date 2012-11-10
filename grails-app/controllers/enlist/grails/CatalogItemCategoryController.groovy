package enlist.grails

import org.springframework.dao.DataIntegrityViolationException

class CatalogItemCategoryController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [catalogItemCategoryInstanceList: CatalogItemCategory.list(params), catalogItemCategoryInstanceTotal: CatalogItemCategory.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [catalogItemCategoryInstance: new CatalogItemCategory(params)]
                break
            case 'POST':
                def catalogItemCategoryInstance = new CatalogItemCategory(params)
                if (!catalogItemCategoryInstance.save(flush: true)) {
                    render view: 'create', model: [catalogItemCategoryInstance: catalogItemCategoryInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), catalogItemCategoryInstance.id])
                redirect action: 'show', id: catalogItemCategoryInstance.id
                break
        }
    }

    def show() {
        def catalogItemCategoryInstance = CatalogItemCategory.get(params.id)
        if (!catalogItemCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), params.id])
            redirect action: 'list'
            return
        }

        [catalogItemCategoryInstance: catalogItemCategoryInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def catalogItemCategoryInstance = CatalogItemCategory.get(params.id)
                if (!catalogItemCategoryInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), params.id])
                    redirect action: 'list'
                    return
                }

                [catalogItemCategoryInstance: catalogItemCategoryInstance]
                break
            case 'POST':
                def catalogItemCategoryInstance = CatalogItemCategory.get(params.id)
                if (!catalogItemCategoryInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (catalogItemCategoryInstance.version > version) {
                        catalogItemCategoryInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory')] as Object[],
                                "Another user has updated this CatalogItemCategory while you were editing")
                        render view: 'edit', model: [catalogItemCategoryInstance: catalogItemCategoryInstance]
                        return
                    }
                }

                catalogItemCategoryInstance.properties = params

                if (!catalogItemCategoryInstance.save(flush: true)) {
                    render view: 'edit', model: [catalogItemCategoryInstance: catalogItemCategoryInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), catalogItemCategoryInstance.id])
                redirect action: 'show', id: catalogItemCategoryInstance.id
                break
        }
    }

    def delete() {
        def catalogItemCategoryInstance = CatalogItemCategory.get(params.id)
        if (!catalogItemCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), params.id])
            redirect action: 'list'
            return
        }

        try {
            catalogItemCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'catalogItemCategory.label', default: 'CatalogItemCategory'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
