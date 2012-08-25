import grails.util.GrailsUtil
import enlist.grails.*

class BootStrap {

    def init = { servletContext ->
		// Setup some DB defaults
		if (GrailsUtil.environment == "development") {
			// Use save(failOnError:true) otherwise failures are not trapped 
			
			// Organization
			new Address(address1: '123 Main St', city: 'Anywhere', state: 'MN', zip: '12345').save(failOnError:true)
			new Organization(name: 'Cool Project',
								emailSender: 'enlist@example.com',
								address: new Address().findWhere(zip: '12345')
			).save(failOnError:true)
			
			// Statuses
			new Status(status: 'Active').save()
			new Status(status: 'Archived').save()
			
			// Roles
			new Role(authority: 'Admin').save()
			new Role(authority: 'User').save()
			
			/* Users
			new User(firstName: 'Joe', 
						lastName: 'Tester', 
						email: 'joe@example.com',
                        username: 'joetester',
                        password:  'test',
						role: new Role().findWhere(authority: 'Admin'),
						status: new Status().findWhere(status: 'Active')
			).save(failOnError:true)
			*/
		}
    }
    def destroy = {
    }
}