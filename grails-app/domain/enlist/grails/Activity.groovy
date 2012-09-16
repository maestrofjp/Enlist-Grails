package enlist.grails

import java.sql.Timestamp;

class Activity {
	
	String title
	String description
	Integer numPeopleNeeded
	Date startDate
	Date endDate
	String location
	Event event
	String pointsType
	Integer points
	
	static hasMany = [coordinators:User, volunteers:User]	

    static constraints = {
		description(maxLength: 500)
    }
}
