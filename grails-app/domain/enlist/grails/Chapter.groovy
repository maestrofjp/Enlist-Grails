
package enlist.grails

class Chapter {
	
	String name
	String location
	Date created = new Date()
	Status status

	static hasMany = [users:User]

    static constraints = {
		name(blank:false)
		location(blank:false)
    }

    String toString() {
    	return "${name} (${location})"
    }
	
}