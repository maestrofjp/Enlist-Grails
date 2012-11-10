package enlist.grails

class DashboardController {

    def eventService

    def index() {
        render(view: "/index", model: [upcomingEvents: eventService.getUpcomingEvents(), featuredActivities: eventService.getFeaturedActivities()])
        return
    }
}
