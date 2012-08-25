package enlist.grails

class Organization {
	
	String name
	Address address
	String pointName
	Integer pointValueDefault
	Boolean sendEmail

    static constraints = {
		name(blank:false)
		pointName(default:'point')
		pointValueDefault(default:1)
		sendEmail(default:true)
    }
	
	String toString() {
		return "${organizationName} (${id})"
	}
}