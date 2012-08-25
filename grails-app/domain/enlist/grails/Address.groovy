package enlist.grails

class Address {
	
	String address1
	String address2
	String city
	String state
	String zip

    static constraints = {
		address1(blank:false)
		city(blank:false)
		state(blank:false)
		zip(blank:false)
    }
}
