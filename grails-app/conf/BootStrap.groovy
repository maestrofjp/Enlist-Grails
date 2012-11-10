import grails.util.GrailsUtil
import enlist.grails.*

class BootStrap {

    def init = { servletContext ->
		// Setup some DB defaults
		if (GrailsUtil.environment == 'development') {
			// Use save(failOnError:true) otherwise failures are not trapped 
			
			// Organization
			new Address(address1: '123 Main St', city: 'Anywhere', state: 'MN', zip: '12345').save(failOnError:true)
			Email email = new Email(username:"enlistappg48@gmail.com", password:"Grails48Hack", host:"smtp.gmail.com", port:465).save(failOnError:true)
			new Organization(name: 'Cool Project',
								emailSender: 'enlist@example.com',
								address: new Address().findWhere(zip: '12345'),
								email: email
			).save(failOnError:true)
			
			// Statuses
			new Status(status: 'Stub').save()
			new Status(status: 'Active').save()
			new Status(status: 'Archived').save()
			new Status(status: 'Pending').save()
			
			// Roles
			new Role(name: 'Organization Administrator', authority: 'ROLE_ADMIN').save()
			new Role(name: 'Chapter Coordinator', authority: 'ROLE_CHAPTER_ADMIN').save()
			new Role(name: 'Activity Coordinator', authority: 'ROLE_ACTIVITY_COORDINATOR').save()
			new Role(name: 'Volunteer', authority: 'ROLE_VOLUNTEER').save()

            // CatalogItemCategories
            new CatalogItemCategory(category: 'Apparel').save()
            new CatalogItemCategory(category: 'Race Discount').save()
						
			/* Users */
			User adminUser = new User(
                firstName: 'Joe',
			    lastName: 'Tester',
				email: 'joe@example.com',
                username: 'admin',
                password:  'test123',
                enabled: true,
				//role: new Role().findWhere(authority: 'ROLE_ADMIN'),
				status: new Status().findWhere(status: 'Active')
			).save(failOnError:true)

            new UserRole(
                    secUser: adminUser,
                    secRole: new Role().findWhere(authority: 'ROLE_ADMIN')
            ).save(failOnError: true)

		}
    }
    def destroy = {
    }
}