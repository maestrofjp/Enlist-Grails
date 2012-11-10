package enlist.grails

class Address {
	
	String address1
	String address2
	String city
	String state
	String zip

    static constraints = {
		
		address1(blank:false)
		address2(nullable: true)
		city(blank:false)
		state(blank:false)
		zip(blank:false)
		id(nullable:true, display:false)
		version(nullable:true, display:false)
    }
	
	String toString() {
        "${address1}, ${city}, ${state} ${zip}"
    }
}
