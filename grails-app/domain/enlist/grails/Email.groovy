package enlist.grails

class Email {

	String username
	String password
	String host
	Integer port

	static constraints = {
		username(blank:false)
		password(blank:false)
		host(blank:false)
		port(blank:false)
	}
}
