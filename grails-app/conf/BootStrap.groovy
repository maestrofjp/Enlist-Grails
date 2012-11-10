import grails.util.GrailsUtil
import enlist.grails.*

class BootStrap {

    def init = { servletContext ->
        // Roles.
        new Role(name: 'Organization Administrator', authority: Role.ADMIN).save()
        new Role(name: 'Chapter Coordinator', authority: Role.CHAPTER_ADMIN).save()
        new Role(name: 'Activity Coordinator', authority: Role.ACTIVITY_COORDINATOR).save()
        new Role(name: 'Volunteer', authority: Role.VOLUNTEER).save()

		// Setup some DB defaults
		if (GrailsUtil.environment == 'development') {
			// Use save(failOnError:true) otherwise failures are not trapped 
			
			// Organization
			new Address(address1: '123 Main St', city: 'Anywhere', state: 'MN', zip: '12345').save(failOnError:true)
			Email email = new Email(username:"enlistappg48@gmail.com", password:"Grails48Hack", host:"smtp.gmail.com", port:465).save(failOnError:true)
			new Organization(name: 'Cool Project',
								emailSender: 'enlist@example.com',
								address: new Address().findWhere(zip: '12345')
			).save(failOnError:true)
			
			// Statuses
			new Status(status: 'Stub').save()
			new Status(status: 'Active').save()
			new Status(status: 'Archived').save()
			new Status(status: 'Pending').save()


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
                    secRole: new Role().findWhere(authority: Role.ADMIN)
            ).save(failOnError: true)


            User volunteerUser = new User(
                    firstName: 'Volunteer',
                    lastName: 'Tester',
                    email: 'joe2@example.com',
                    username: 'guest',
                    password:  'test123',
                    enabled: true,
                    status: new Status().findWhere(status: 'Active')
            ).save(failOnError:true)

            new UserRole(
                    secUser: volunteerUser,
                    secRole: new Role().findWhere(authority: Role.VOLUNTEER)
            ).save(failOnError: true)

		}
    }
    def destroy = {
    }
}