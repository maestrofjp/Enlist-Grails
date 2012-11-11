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
            def testEvent1 = new Event(name: 'Minneapolis Marathon', location: 'Event Location', start: new Date().clearTime(),
                    end: new Date().clearTime() + 7, status: activeStatus, chapter: mnChapter).save(failOnError: true)
            def testEvent2 = new Event(name: 'Chicago Marathon', location: 'The Location', start: new Date().clearTime(),
                    end: new Date().clearTime() + 5, status: activeStatus, chapter: ilChapter).save(failOnError: true)
            def nowTime = new Date().getTime()
            def activity1 = new Activity(title: 'Coorindate Vendors', description: 'Coordinate all the vendors so they know where they need to be and when',
                        numPeopleNeeded: 10, startDate: new Date(nowTime), endDate: new Date(nowTime + (60 * 60 * 1000)),
                        location: 'Somewhere over the rainbow', event: testEvent1, pointsType: 'Flat', points: 100,
                        featured: true).save(failOnError: true)
            def activity2 = new Activity(title: 'Stuff Bags', description: 'Stuff the bags for all the runners', numPeopleNeeded: 10, startDate: new Date(nowTime),
                        endDate: new Date(nowTime + (60 * 60 * 1000)), location: 'Somewhere over the rainbow',
                        event: testEvent2, pointsType: 'Flat', points: 100, featured: true).save(failOnError: true)

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
				chapter: mnChapter,
                currPoints: 1000
			).save(failOnError:true)

            new UserRole(
                    secUser: adminUser,
                    secRole: superAdminRole
            ).save(failOnError: true)


            User volunteerUser = new User(
                    firstName: 'Volunteer',
                    lastName: 'Tester',
                    email: 'enlistappg48@gmail.com',  // test email reminder. must use valid address.
                    username: 'guest',
                    password:  'test123',
                    enabled: true,
                    status: activeStatus,
					phone: '651-555-1234',
					chapter: mnChapter,
                    currPoints: 5000
            ).save(failOnError:true)

            new UserRole(
                    secUser: volunteerUser,
                    secRole: volunteerRole
            ).save(failOnError: true)

            // ActivitySignUps
            new ActivitySignUp(user: adminUser, activity: activity1, signUpTime: new Date(), reminderAt: new Date()).save(failOnError: true)
            new ActivitySignUp(user: volunteerUser, activity: activity2, signUpTime: new Date(), reminderAt: new Date()).save(failOnError: true)

            //test display PointTransaction
            buildTestDataPointTxn()

            User volunteerUser2 = new User(
                    firstName: 'Volunteer2',
                    lastName: 'Tester',
                    email: 'volunteer2@example.com',
                    username: 'guest2',
                    password:  'test123',
                    enabled: true,
                    status: activeStatus,
                    phone: '651-555-1234',
                    chapter: mnChapter
            ).save(failOnError:true)

            new UserRole(
                    secUser: volunteerUser2,
                    secRole: volunteerRole
            ).save(failOnError: true)
		}
    }
    def buildTestDataPointTxn() {
        for(User user : User.list()) {
            (1..5).each { int count ->
                PointTransaction txn = new PointTransaction( acctOwner: user, txnDate: new Date().minus(count),
                        txnType: PointTransaction.VOLUNTEER, amount: count * (Math.random() * 10),
                        description: "Dummy testing ${count}")
                if(count % 2 == 0) txn.amount *= -1
                txn.save(failOnError: true, validate: false)
                user.currPoints = (user.currPoints ?:0) + txn.amount
            }

            user.save(validate: false)
        }
    }
    def destroy = {
    }
}
