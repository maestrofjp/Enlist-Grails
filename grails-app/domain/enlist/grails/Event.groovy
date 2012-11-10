package enlist.grails

import java.sql.Timestamp;

class Event {
	
	String name
	String location
	Date start
	Date end
	Status status

	static hasMany = [activities:Activity]

    static constraints = {
		status(blank: false)
    	name(blank:false)
    	location(blank:false)
		start()
		end()
		activities(display: false)
    }

    String toString() {
    	return "${name} (${location})"
    }
}
