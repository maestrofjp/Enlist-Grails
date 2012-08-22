package enlist.grails



import org.junit.*
import grails.test.mixin.*

@TestFor(ChapterController)
@Mock(Chapter)
class ChapterControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/chapter/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.chapterInstanceList.size() == 0
        assert model.chapterInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.chapterInstance != null
    }

    void testSave() {
        controller.save()

        assert model.chapterInstance != null
        assert view == '/chapter/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/chapter/show/1'
        assert controller.flash.message != null
        assert Chapter.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/chapter/list'

        populateValidParams(params)
        def chapter = new Chapter(params)

        assert chapter.save() != null

        params.id = chapter.id

        def model = controller.show()

        assert model.chapterInstance == chapter
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/chapter/list'

        populateValidParams(params)
        def chapter = new Chapter(params)

        assert chapter.save() != null

        params.id = chapter.id

        def model = controller.edit()

        assert model.chapterInstance == chapter
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/chapter/list'

        response.reset()

        populateValidParams(params)
        def chapter = new Chapter(params)

        assert chapter.save() != null

        // test invalid parameters in update
        params.id = chapter.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/chapter/edit"
        assert model.chapterInstance != null

        chapter.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/chapter/show/$chapter.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        chapter.clearErrors()

        populateValidParams(params)
        params.id = chapter.id
        params.version = -1
        controller.update()

        assert view == "/chapter/edit"
        assert model.chapterInstance != null
        assert model.chapterInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/chapter/list'

        response.reset()

        populateValidParams(params)
        def chapter = new Chapter(params)

        assert chapter.save() != null
        assert Chapter.count() == 1

        params.id = chapter.id

        controller.delete()

        assert Chapter.count() == 0
        assert Chapter.get(chapter.id) == null
        assert response.redirectedUrl == '/chapter/list'
    }
}
