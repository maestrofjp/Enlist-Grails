package enlist.grails

class EventService {

    def getUpcomingEvents(Chapter chapter=null) {
        def today = new Date().clearTime()
        def status = Status.findByStatus('Active')

        if (chapter) {
            return Event.findAllByStatusAndStartGreaterThanEqualsAndChapter(status, today, chapter, [sort:'start', order:'asc'])
        } else {
            return Event.findAllByStatusAndStartGreaterThanEquals(status, today, [sort:'start', order:'asc'])
        }
    }

    def getRegisteredEvents(User user) {
        return ActivitySignUp.findAllByUser(user)
    }

    def getFeaturedActivities() {
        return Activity.findAllByFeatured(true)
    }
}
