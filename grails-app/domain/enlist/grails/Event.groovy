package enlist.grails

class Event {
	
	String name
	String location
	Date start
	Date end
	Status status
    Chapter chapter

    static searchable = {
        name index : "analyzed"
        chapter component : true
    }

	static hasMany = [activities:Activity]

    static belongsTo = [chapter: Chapter]

    static constraints = {
		status(blank: false)
    	name(blank:false)
    	location(blank:false)
        chapter(nullable: true)
		start()
		end(validator: {val, obj, errors ->
            if (obj.end && obj.start) {
                if (obj.end.time < obj.start.time) {
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
