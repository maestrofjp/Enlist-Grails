package enlist.grails

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class PointTransactionController extends AbstractBaseController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    protected def getRolesWithWriteAccess() { [Role.VOLUNTEER]}

    def index() {
        redirect action: 'list', params: params
    }

    def getLoginUserPointTransactions() { loginUser.retrievePointTransactions() }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        // if not admin, only shows his own transaction
        def txnList = loginUser ? (loginUser.checkAdmin() ? PointTransaction.list(params) : loginUserPointTransactions) : []
        [currBalance : loginUser?.currPoints , pointTransactionInstanceList: txnList, pointTransactionInstanceTotal: txnList.size()]
    }

    def show() {
        def pointTransactionInstance = PointTransaction.get(params.id)
        if (!pointTransactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointTransaction.label', default: 'PointTransaction'), params.id])
            redirect action: 'list'
            return
        }

        [pointTransactionInstance: pointTransactionInstance]
    }

    @Secured(['ROLE_CHAPTER_ADMIN', 'ROLE_ADMIN'])
    def edit() {
        switch (request.method) {
            case 'GET':
                def pointTransactionInstance = PointTransaction.get(params.id)
                if (!pointTransactionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointTransaction.label', default: 'PointTransaction'), params.id])
                    redirect action: 'list'
                    return
                }

                [pointTransactionInstance: pointTransactionInstance]
                break
            case 'POST':
                def pointTransactionInstance = PointTransaction.get(params.id)
                if (!pointTransactionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointTransaction.label', default: 'PointTransaction'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (pointTransactionInstance.version > version) {
                        pointTransactionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'pointTransaction.label', default: 'PointTransaction')] as Object[],
                                "Another user has updated this PointTransaction while you were editing")
                        render view: 'edit', model: [pointTransactionInstance: pointTransactionInstance]
                        return
                    }
                }

                pointTransactionInstance.properties = params

                if (!pointTransactionInstance.save(flush: true)) {
                    render view: 'edit', model: [pointTransactionInstance: pointTransactionInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'pointTransaction.label', default: 'PointTransaction'), pointTransactionInstance.id])
                redirect action: 'show', id: pointTransactionInstance.id
                break
        }
    }

    @Secured(['ROLE_CHAPTER_ADMIN', 'ROLE_ADMIN'])
    def delete() {
        def pointTransactionInstance = PointTransaction.get(params.id)
        if (!pointTransactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointTransaction.label', default: 'PointTransaction'), params.id])
            redirect action: 'list'
            return
        }

        try {
            pointTransactionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pointTransaction.label', default: 'PointTransaction'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pointTransaction.label', default: 'PointTransaction'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
