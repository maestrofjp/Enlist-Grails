import grails.util.GrailsUtil
import enlist.grails.*

class BootStrap {

    def init = { servletContext ->
		// Setup some DB defaults
		if (GrailsUtil.environment == "development") {
			new Status(status: 'Active').save()
			new Status(status: 'Archived').save()
		}
    }
    def destroy = {
    }
}