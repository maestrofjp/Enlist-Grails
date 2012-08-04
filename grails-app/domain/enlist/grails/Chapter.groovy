
package enlist.grails

class Chapter {
	
	String name
	String location
	String status
	Date created = new Date()

    static constraints = {
		name(blank:false)
		location(blank:false)
    }
	
}