package enlist.grails

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
    }
}
