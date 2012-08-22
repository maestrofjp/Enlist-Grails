package enlist.grails

import java.sql.Timestamp;

class Event {
	
	String name
	String location
	Date start = new Date()
	Date end = new Date()
	Status status

	static hasMany = [volunteers:User]

    static constraints = {
    	name(blank:false)
    	location(blank:false)
    }

    String toString() {
    	return "${name} (${location})"
    }
}
