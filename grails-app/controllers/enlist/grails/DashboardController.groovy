package enlist.grails

class DashboardController {

    def eventService

    def index() {
        println eventService.getUpcomingEvents()
        render(view: "/index", model: [upcomingEvents: eventService.getUpcomingEvents()])
        return
    }
}
