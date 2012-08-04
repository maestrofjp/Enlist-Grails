package enlist.grails

import java.sql.Timestamp;

class Event {
	
	String name
	String location
	Date start = new Date()
	Date end = new Date()
	String status

    static constraints = {
    }
}
