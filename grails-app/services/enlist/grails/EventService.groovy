package enlist.grails

class EventService {

    def getUpcomingEvents(Chapter chapter=null) {
        def today = new Date().clearTime()
        def status = Status.findByStatus('Active')

        if (chapter) {
            return Event.findAllByStatusAndStartGreaterThanEqualsAndChapter(status, today, chapter)
        } else {
            return Event.findAllByStatusAndStartGreaterThanEquals(status, today)
        }
   }

    def getFeaturedActivities() {
        return Activity.findAllByFeatured(true)
    }
}
