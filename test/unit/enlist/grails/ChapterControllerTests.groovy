package enlist.grails

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import org.junit.Ignore

@TestFor(ChapterController)
@Build(Chapter)
class ChapterControllerTests {

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

	@Ignore
	void testSave() {
		controller.save()

		assert model.chapterInstance != null
		assert view == '/chapter/create'

		response.reset()

		controller.save()

		assert response.redirectedUrl == '/chapter/show/1'
		assert controller.flash.message != null
		assert Chapter.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/chapter/list'

		def chapter = Chapter.build()

		assert chapter.save() != null

		params.id = chapter.id

		def model = controller.show()

		assert model.chapterInstance == chapter
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/chapter/list'

		def chapter = Chapter.build()

		assert chapter.save() != null

		params.id = chapter.id

		def model = controller.edit()

		assert model.chapterInstance == chapter
	}

	@Ignore
	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/chapter/list'

		response.reset()

		def chapter = Chapter.build()

		assert chapter.save() != null

		// test invalid parameters in update
		params.id = chapter.id
		//TODO: add invalid values to params object

		controller.update(chapter.id,chapter.version)

		assert view == "/chapter/edit"
		assert model.chapterInstance != null

		chapter.clearErrors()

		controller.update()

		assert response.redirectedUrl == "/chapter/show/$chapter.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		chapter.clearErrors()

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

		def chapter = Chapter.build()

		assert chapter.save() != null
		assert Chapter.count() == 1

		params.id = chapter.id

		controller.delete()

		assert Chapter.count() == 0
		assert Chapter.get(chapter.id) == null
		assert response.redirectedUrl == '/chapter/list'
	}
}
