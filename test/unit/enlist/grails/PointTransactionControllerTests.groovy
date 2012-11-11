package enlist.grails



import org.junit.*
import grails.test.mixin.*

@TestFor(PointTransactionController)
@Mock(PointTransaction)
class PointTransactionControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pointTransaction/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pointTransactionInstanceList.size() == 0
        assert model.pointTransactionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pointTransactionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pointTransactionInstance != null
        assert view == '/pointTransaction/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pointTransaction/show/1'
        assert controller.flash.message != null
        assert PointTransaction.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pointTransaction/list'


        populateValidParams(params)
        def pointTransaction = new PointTransaction(params)

        assert pointTransaction.save() != null

        params.id = pointTransaction.id

        def model = controller.show()

        assert model.pointTransactionInstance == pointTransaction
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pointTransaction/list'


        populateValidParams(params)
        def pointTransaction = new PointTransaction(params)

        assert pointTransaction.save() != null

        params.id = pointTransaction.id

        def model = controller.edit()

        assert model.pointTransactionInstance == pointTransaction
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pointTransaction/list'

        response.reset()


        populateValidParams(params)
        def pointTransaction = new PointTransaction(params)

        assert pointTransaction.save() != null

        // test invalid parameters in update
        params.id = pointTransaction.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pointTransaction/edit"
        assert model.pointTransactionInstance != null

        pointTransaction.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pointTransaction/show/$pointTransaction.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pointTransaction.clearErrors()

        populateValidParams(params)
        params.id = pointTransaction.id
        params.version = -1
        controller.update()

        assert view == "/pointTransaction/edit"
        assert model.pointTransactionInstance != null
        assert model.pointTransactionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pointTransaction/list'

        response.reset()

        populateValidParams(params)
        def pointTransaction = new PointTransaction(params)

        assert pointTransaction.save() != null
        assert PointTransaction.count() == 1

        params.id = pointTransaction.id

        controller.delete()

        assert PointTransaction.count() == 0
        assert PointTransaction.get(pointTransaction.id) == null
        assert response.redirectedUrl == '/pointTransaction/list'
    }
}
