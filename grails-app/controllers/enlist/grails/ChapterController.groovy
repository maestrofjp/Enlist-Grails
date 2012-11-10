package enlist.grails

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class ChapterController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [chapterInstanceList: Chapter.list(params), chapterInstanceTotal: Chapter.count()]
    }

    def create() {
        [chapterInstance: new Chapter(params)]
    }

    def save() {
//        def chapterInstance = new Chapter(params)
        def chapterInstance = new Chapter()
        bindData(chapterInstance, params)
        //flush: true
        if (!chapterInstance.save()) {
            render(view: "create", model: [chapterInstance: chapterInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'chapter.label', default: 'Chapter'), chapterInstance.id])
        redirect(action: "show", id: chapterInstance.id)
    }

    def show(Long id) {
        def chapterInstance = Chapter.get(id)
        if (!chapterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chapter.label', default: 'Chapter'), id])
            redirect(action: "list")
            return
        }

        [chapterInstance: chapterInstance]
    }

    def edit(Long id) {
        def chapterInstance = Chapter.get(id)
        if (!chapterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chapter.label', default: 'Chapter'), id])
            redirect(action: "list")
            return
        }

        [chapterInstance: chapterInstance]
    }

    def update(Long id, Long version) {
        def chapterInstance = Chapter.get(id)
        if (!chapterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chapter.label', default: 'Chapter'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (chapterInstance.version > version) {
                chapterInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'chapter.label', default: 'Chapter')] as Object[],
                          "Another user has updated this Chapter while you were editing")
                render(view: "edit", model: [chapterInstance: chapterInstance])
                return
            }
        }

        chapterInstance.properties = params

        if (!chapterInstance.save(flush: true)) {
            render(view: "edit", model: [chapterInstance: chapterInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'chapter.label', default: 'Chapter'), chapterInstance.id])
        redirect(action: "show", id: chapterInstance.id)
    }

    def delete(Long id) {
        def chapterInstance = Chapter.get(id)
        if (!chapterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chapter.label', default: 'Chapter'), id])
            redirect(action: "list")
            return
        }

        try {
            chapterInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'chapter.label', default: 'Chapter'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'chapter.label', default: 'Chapter'), id])
            redirect(action: "show", id: id)
        }
    }
}
