package enlist.grails



import org.junit.*
import grails.test.mixin.*

@TestFor(StatusController)
@Mock(Status)
class StatusControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/status/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.statusInstanceList.size() == 0
        assert model.statusInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.statusInstance != null
    }

    void testSave() {
        controller.save()

        assert model.statusInstance != null
        assert view == '/status/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/status/show/1'
        assert controller.flash.message != null
        assert Status.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/status/list'

        populateValidParams(params)
        def status = new Status(params)

        assert status.save() != null

        params.id = status.id

        def model = controller.show()

        assert model.statusInstance == status
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/status/list'

        populateValidParams(params)
        def status = new Status(params)

        assert status.save() != null

        params.id = status.id

        def model = controller.edit()

        assert model.statusInstance == status
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/status/list'

        response.reset()

        populateValidParams(params)
        def status = new Status(params)

        assert status.save() != null

        // test invalid parameters in update
        params.id = status.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/status/edit"
        assert model.statusInstance != null

        status.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/status/show/$status.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        status.clearErrors()

        populateValidParams(params)
        params.id = status.id
        params.version = -1
        controller.update()

        assert view == "/status/edit"
        assert model.statusInstance != null
        assert model.statusInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/status/list'

        response.reset()

        populateValidParams(params)
        def status = new Status(params)

        assert status.save() != null
        assert Status.count() == 1

        params.id = status.id

        controller.delete()

        assert Status.count() == 0
        assert Status.get(status.id) == null
        assert response.redirectedUrl == '/status/list'
    }
}
