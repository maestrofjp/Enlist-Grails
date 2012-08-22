package enlist.grails

class Status {

	String status

    static constraints = {
    	status(blank:false)
    }

    String toString() {
    	return status
    }
}
