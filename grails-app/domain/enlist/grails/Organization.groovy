package enlist.grails

class Organization {
	
	String name
	Address address
	String pointName
	Integer pointValueDefault
	Boolean emailSend
	String emailSender
	Boolean sendEmail

    static constraints = {
		name(blank:false)
		pointName(default:'point')
		pointValueDefault(default:1)
		emailSend(default:true)
		emailSender(blank:false)
    }
	
	String toString() {
		return "${name} (${id})"
	}
}
