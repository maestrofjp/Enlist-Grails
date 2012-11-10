package enlist.grails

class EventService {

    def getUpcomingEvents() {
        def yesterday = new Date().minus(1).clearTime()
        def status = Status.findByStatus('Active')
        return Event.findByStatusAndStartGreaterThan(status, yesterday, [sort:'start', order:'asc'])
    }

    def getFeaturedActivities() {
        return Activity.findAllByFeatured(true)
    }
}
