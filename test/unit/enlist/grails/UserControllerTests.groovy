package enlist.grails

import grails.buildtestdata.mixin.Build
import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.junit.Ignore

@TestFor(UserController)
@Mock(SpringSecurityService)
@Build(User)
class UserControllerTests {

	def springSecurityService

	void setUp(){
		User.metaClass.encodePassword = {"bob"}
		User.metaClass.isDirty = {false}
	}


	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		//params["name"] = 'someValidName'
	}

	void testIndex() {
		controller.index()
		assert "/user/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.userInstanceList.size() == 0
		assert model.userInstanceTotal == 0
	}

	void testCreate() {
		def model = controller.create()

		assert model.userInstance != null
	}

	@Ignore
	void testSave() {
		controller.save()

		assert model.userInstance != null
		assert view == '/user/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/user/show/1'
		assert controller.flash.message != null
		assert User.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		def user = User.build()

		assert user.save() != null

		params.id = user.id

		def model = controller.show()

		assert model.userInstance == user
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/user/list'


		def user = User.build()

		assert user.save() != null

		params.id = user.id

		def model = controller.edit()

		assert model.userInstance == user
	}

	@Ignore
	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		response.reset()

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null

		// test invalid parameters in update
		params.id = user.id
		//TODO: add invalid values to params object

		controller.update()

		assert view == "/user/edit"
		assert model.userInstance != null

		user.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/user/show/$user.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		user.clearErrors()

		populateValidParams(params)
		params.id = user.id
		params.version = -1
		controller.update()

		assert view == "/user/edit"
		assert model.userInstance != null
		assert model.userInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		response.reset()


		def user = User.build()

		assert user.save() != null
		assert User.count() == 1

		params.id = user.id

		controller.delete()

		assert User.count() == 0
		assert User.get(user.id) == null
		assert response.redirectedUrl == '/user/list'
	}

	@Ignore //Should be an integration test
	void testSearch() {
		def user = User.build(
			firstName: 'Joe',
			lastName: 'Tester',
			email: 'joe@example.com',
			username: 'admin',
			password: 'test123',
			enabled: true)

		assert user.save() != null
		assert User.count() == 1
		params.q = "joe"
		controller.search()
		assert response.redirectedUrl == '/user/list'
		assert model.userInstanceList.size() == 1

	}
}
