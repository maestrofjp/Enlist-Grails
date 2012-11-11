import grails.util.GrailsUtil
import enlist.grails.*

class BootStrap {

    def init = { servletContext ->
		// Setup some DB defaults
		if (GrailsUtil.environment == 'development') {
			// Use save(failOnError:true) otherwise failures are not trapped 

            // Roles.
            def superAdminRole = new Role(name: 'Organization Administrator', authority: Role.ADMIN).save(failOnError: true)
            def chapAdminRole = new Role(name: 'Chapter Coordinator', authority: Role.CHAPTER_ADMIN).save(failOnError: true)
            def actCoordRole = new Role(name: 'Activity Coordinator', authority: Role.ACTIVITY_COORDINATOR).save(failOnError: true)
            def volunteerRole = new Role(name: 'Volunteer', authority: Role.VOLUNTEER).save(failOnError: true)

            // Addresses
            def mnAddress = new Address(address1: '123 Main Street', city: 'Minneapolis', state: 'MN', zip: '54321').save(failOnError: true)
            def ilAddress = new Address(address1: '456 Another Ave', city: 'Chicago', state: 'IL', zip: '65432').save(failOnError: true)
            def txAddress = new Address(address1: '789 Big Tex Drive', city: 'Dallas', state: 'TX', zip: '76543').save(failOnError: true)

            // Organization
			Email email = new Email(username:"enlistappg48@gmail.com", password:"Grails48Hack", host:"smtp.gmail.com", port:465).save(failOnError:true)
			new Organization(name: 'Cool Project',
								emailSender: 'enlist@example.com',
								address: mnAddress
			).save(failOnError:true)

            // Statuses
            def stubStatus = new Status(status: 'Stub').save(failOnError: true)
            def activeStatus = new Status(status: 'Active').save(failOnError: true)
            def archivedStatus = new Status(status: 'Archived').save(failOnError: true)
            def pendingStatus = new Status(status: 'Pending').save(failOnError: true)

            // Chapters
            def mnChapter = new Chapter(name: 'Minneapolis', address: mnAddress, status: activeStatus).save(failOnError: true)
            def ilChapter = new Chapter(name: 'Chicago', address: ilAddress, status: activeStatus).save(failOnError: true)
            def txChapter = new Chapter(name: 'Dallas', address: txAddress, status: activeStatus).save(failOnError: true)

            // CatalogItemCategories
            CatalogItemCategory CICApparel = new CatalogItemCategory(category: 'Apparel').save(failOnError: true)
            CatalogItemCategory CICRaceDiscounts = new CatalogItemCategory(category: 'Race Discounts').save(failOnError: true)

            // Events and Activities
            def testEvent1 = new Event(name: 'Test Event 1', location: 'Event Location', start: new Date().clearTime(),
                    end: new Date().clearTime(), status: activeStatus, chapter: mnChapter).save(failOnError: true)
            def nowTime = new Date().getTime()
            new Activity(title: 'Test Activity 1', description: 'Test activity!', numPeopleNeeded: 10, startDate: new Date(nowTime),
                    endDate: new Date(nowTime + (60 * 60 * 1000)), location: 'Somewhere over the rainbow',
                    event: testEvent1, pointsType: 'Flat', points: 100, featured: true).save(failOnError: true)

            // CatalogItems
			new CatalogItem(name: 'Minneapolis Marathon - 50% Race Discount',
								description: '',
								category: CICRaceDiscounts,
								available: true, 
								points: 3000, 
								photo: null).save(failOnError:true)
			new CatalogItem(name: 'Monster Dash Stocking Cap',
								description: '',
								category: CICApparel,
								available: true,
								points: 500,
								photo: null).save(failOnError:true)
						
			/* Users */
			User adminUser = new User(
                firstName: 'Joe',
			    lastName: 'Tester',
				email: 'joe@example.com',
                username: 'admin',
                password:  'test123',
                enabled: true,
				status: activeStatus,
				phone: '612-555-6789',
				chapter: mnChapter
			).save(failOnError:true)

            new UserRole(
                    secUser: adminUser,
                    secRole: superAdminRole
            ).save(failOnError: true)


            User volunteerUser = new User(
                    firstName: 'Volunteer',
                    lastName: 'Tester',
                    email: 'volunteer@example.com',
                    username: 'guest',
                    password:  'test123',
                    enabled: true,
                    status: activeStatus,
					phone: '651-555-1234',
					chapter: mnChapter
            ).save(failOnError:true)

            new UserRole(
                    secUser: volunteerUser,
                    secRole: volunteerRole
            ).save(failOnError: true)

		}
    }
    def destroy = {
    }
}