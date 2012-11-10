package enlist.grails

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
		end(validator: {val, obj, errors ->
            if (obj.end && obj.start) {
                if (obj.end.time <= obj.start.time) {
                    obj.errors.reject("enddate.shouldbe.after.startdate", "End date should be after the start date.");
                }
            }
        })
		activities(display: false)
    }

    String toString() {
    	return "${name} (${location})"
    }
}
