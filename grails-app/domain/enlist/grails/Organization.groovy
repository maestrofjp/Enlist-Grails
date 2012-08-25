package enlist.grails

class Organization {
	
	String name
	Address address
	String pointName = 'point'
	Integer pointValueDefault = 1
	Boolean emailSend = true
	String emailSender

    static constraints = {
		name(blank:false)
		pointName(blank:false)
		pointValueDefault(blank:false)
		emailSend(blank:false)
		emailSender(blank:false)
		importFrom Address
		id(nullable:true, display:false)
    }
	
	String toString() {
		return "${name}"
	}
}
