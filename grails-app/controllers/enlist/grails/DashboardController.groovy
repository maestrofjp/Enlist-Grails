package enlist.grails

class DashboardController {

    def eventService

    def home() {
        render(view: "/index", model: [upcomingEvents: eventService.getUpcomingEvents(), featuredActivities: eventService.getFeaturedActivities()])
        return
    }

    def dashboard() {
        render(view: "index")
        return
    }
}
