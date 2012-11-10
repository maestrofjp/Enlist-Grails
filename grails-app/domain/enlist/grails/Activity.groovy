package enlist.grails

import groovy.swing.binding.JListProperties;

import java.sql.Timestamp;

class Activity {
	
	String title
	String description
	Integer numPeopleNeeded
	Date startDate
	Date endDate
	String location
    Address locationAddress
	Event event
	String pointsType
	Integer points
	
	static hasMany = [coordinators:User, volunteers:User]

    static embedded = ['locationAddress']

    static constraints = {
		title(blank: false
			, size: 3..100
		)
		description(maxLength: 500)
		numPeopleNeeded(size: 1..256)
		
		location(blank: false)
		event(nullable: false)
        locationAddress(nullable: true)
    }
}
