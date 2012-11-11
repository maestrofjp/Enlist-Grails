package enlist.grails

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build


@TestFor(Address)
@Build(Address)
class AddressSpec extends Specification {

	def "test toString"(){
		given:
			Address a = Address.build(address1:'line1',city:'moline',state:'il',zip:'55412')

		expect:
			a.toString() == "line1, moline, il 55412"

	}
}
