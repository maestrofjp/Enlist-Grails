package enlist.grails

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor

@TestFor(AddressController)
@Build(Address)
class AddressControllerTests {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		//params["name"] = 'someValidName'
	}

	void testIndex() {
		controller.index()
		assert "/address/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.addressInstanceList.size() == 0
		assert model.addressInstanceTotal == 0
	}

	void testCreate() {
		request.method = "GET"

		def model = controller.create()

		assert model.addressInstance != null
	}


	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/address/list'



		def address = Address.build()

		assert address.save() != null

		params.id = address.id

		def model = controller.show()

		assert model.addressInstance == address
	}

	void testEdit() {
		request.method = "GET"
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/address/list'


		def address = Address.build()

		assert address.save() != null

		params.id = address.id

		def model = controller.edit()

		assert model.addressInstance == address
	}



	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/address/list'

		response.reset()

		def address = Address.build()

		assert address.save() != null
		assert Address.count() == 1

		params.id = address.id

		controller.delete()

		assert Address.count() == 0
		assert Address.get(address.id) == null
		assert response.redirectedUrl == '/address/list'
	}
}
