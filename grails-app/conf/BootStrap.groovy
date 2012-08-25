import grails.util.GrailsUtil
import enlist.grails.*

class BootStrap {

    def init = { servletContext ->
		// Setup some DB defaults
		if (GrailsUtil.environment == "development") {
			// Setting
			new Organization(organizationName: 'Cool Project', organizationAddress: '123 Main Street').save()
			
			// Statuses
			new Status(status: 'Active').save()
			new Status(status: 'Archived').save()
			
			// Roles
			new Role(role: 'Admin').save()
			new Role(role: 'User').save()
		}
    }
    def destroy = {
    }
}