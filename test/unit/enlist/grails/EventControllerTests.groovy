package enlist.grails

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import org.junit.Ignore

@TestFor(EventController)
@Build([Event, Activity])
class EventControllerTests {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		//params["name"] = 'someValidName'
	}

	void testIndex() {
		controller.index()
		assert "/event/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.eventInstanceList.size() == 0
		assert model.eventInstanceTotal == 0
	}

	void testCreate() {
		def model = controller.create()

		assert model.eventInstance != null
	}

	@Ignore
	void testSave() {
		controller.save()

		assert model.eventInstance != null
		assert view == '/event/create'

		response.reset()


		controller.save()

		assert response.redirectedUrl == '/event/show/1'
		assert controller.flash.message != null
		assert Event.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/event/list'

		def d = new Date()
		def event = Event.build(start: d, end: d + 2)

		assert event.save() != null

		params.id = event.id

		def model = controller.show()

		assert model.eventInstance == event
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/event/list'

		def d = new Date()
		def event = Event.build(start: d, end: d + 2)

		assert event.save() != null

		params.id = event.id

		def model = controller.edit()

		assert model.eventInstance == event
	}

	@Ignore
	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/event/list'

		response.reset()

		def d = new Date()
		def event = Event.build(start: d, end: d + 2)

		assert event.save() != null

		// test invalid parameters in update
		params.id = event.id
		//TODO: add invalid values to params object

		controller.update()

		assert view == "/event/edit"
		assert model.eventInstance != null

		event.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/event/show/$event.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		event.clearErrors()

		populateValidParams(params)
		params.id = event.id
		params.version = -1
		controller.update()

		assert view == "/event/edit"
		assert model.eventInstance != null
		assert model.eventInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/event/list'

		response.reset()

		def d = new Date()
		def event = Event.build(start:d,end:d+2)

		assert event.save() != null
		assert Event.count() == 1

		params.id = event.id

		controller.delete()

		assert Event.count() == 0
		assert Event.get(event.id) == null
		assert response.redirectedUrl == '/event/list'
	}
}
