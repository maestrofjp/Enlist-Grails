package enlist.grails

class Activity {
	
	String title
	String description
	Integer numPeopleNeeded = 1
	Date startDate
	Date endDate
	String location
    Address locationAddress
	Event event
	String pointsType
	Integer points = 0

	static hasMany = [coordinators:User]
    def getVolunteers() { ActivitySignUp.findAllByActivityId(this.id)}

    static embedded = ['locationAddress']


    static mapping = {
        endDate index : 'batchActivityIdx'
    }
    static constraints = {
		title(blank: false
			, size: 3..100
		)
		description(maxLength: 500)
		numPeopleNeeded(size: 1..256)
		
		location(blank: false)
		event(nullable: false)
        locationAddress(nullable: true)
        endDate(validator: {val, obj, errors ->
            if (obj.endDate && obj.startDate) {
                if (obj.endDate.time <= obj.startDate.time) {
                    obj.errors.reject("enddate.shouldbe.after.startdate", "End date should be after the start date.");
                }
            }
        })
        points min: 0
    }
}
