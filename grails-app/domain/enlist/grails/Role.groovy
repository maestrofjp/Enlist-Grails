package enlist.grails

class Role {

	String role

    static constraints = {
    	role(blank:false)
    }

    String toString() {
    	return role
    }
}
