import enlist.grails.*

class BootStrap {
    def sessionFactory

    def flushSession(){
     sessionFactory.currentSession.flush()
     sessionFactory.currentSession.clear()
    }
    def init = { servletContext ->
        User.withTransaction {
            for(Role role : Role.list()) {
                long roleId = role.id
                UserRole.removeAll(role)
                flushSession()
                role = Role.get(roleId)
                role.delete()
            }

            for(User user : User.list()) {
                long userId = user.id
                ActivitySignUp.removeAll(user)
                UserActivityHistory.removeAll(user)
                PointTransaction.removeAll(user)
                flushSession()
                user = User.get(userId)
                user.address = null
                user.profile = null
                user.chapter = null
                user.save()
                user.delete()
            }
            flushSession()

            // Roles.
            def superAdminRole = Role.findOrSaveWhere(name: 'Organization Administrator', authority: Role.ADMIN)
            def chapAdminRole = Role.findOrSaveWhere(name: 'Chapter Coordinator', authority: Role.CHAPTER_ADMIN)
            def actCoordRole = Role.findOrSaveWhere(name: 'Activity Coordinator', authority: Role.ACTIVITY_COORDINATOR)
            def volunteerRole = Role.findOrSaveWhere(name: 'Volunteer', authority: Role.VOLUNTEER)

            // Addresses
            def mnAddress = Address.findOrSaveWhere(address1: '123 Main Street', city: 'Minneapolis', state: 'MN', zip: '54321')
            def ilAddress = Address.findOrSaveWhere(address1: '456 Another Ave', city: 'Chicago', state: 'IL', zip: '65432')
            def txAddress = Address.findOrSaveWhere(address1: '789 Big Tex Drive', city: 'Dallas', state: 'TX', zip: '76543')

            // Organization
            Email email = Email.findOrSaveWhere(username:"enlistappg48@gmail.com", password:"Grails48Hack", host:"smtp.gmail.com", port:465)
            Organization.findOrSaveWhere(name: 'Cool Project', emailSender: 'enlist@example.com', address: mnAddress)

            // Statuses
            def stubStatus = Status.findOrSaveWhere(status: 'Stub')
            def activeStatus = Status.findOrSaveWhere(status: 'Active')
            def archivedStatus = Status.findOrSaveWhere(status: 'Archived')
            def pendingStatus = Status.findOrSaveWhere(status: 'Pending')

            // Chapters
            def mnChapter = Chapter.findOrSaveWhere(name: 'Minneapolis', address: mnAddress, status: activeStatus)
            def ilChapter = Chapter.findOrSaveWhere(name: 'Chicago', address: ilAddress, status: activeStatus)
            def txChapter = Chapter.findOrSaveWhere(name: 'Dallas', address: txAddress, status: activeStatus)

            // CatalogItemCategories
            CatalogItemCategory CICApparel = CatalogItemCategory.findOrSaveWhere(category: 'Apparel')
            CatalogItemCategory CICRaceDiscounts = CatalogItemCategory.findOrSaveWhere(category: 'Race Discounts')

            // Events and Activities
            def testEvent1 = Event.findOrCreateWhere(name: 'Minneapolis Marathon')
            testEvent1.properties = [location: 'Event Location', status: activeStatus, chapter: mnChapter,
                    start: new Date().clearTime(), end: new Date().clearTime() + 7]
            testEvent1.save(failOnError: true)
            def testEvent2 = Event.findOrCreateWhere(name: 'Chicago Marathon')
            testEvent2.properties = [location: 'The Location', start: new Date().clearTime(),
                    end: new Date().clearTime() + 5, status: activeStatus, chapter: ilChapter]
            testEvent2.save(failOnError: true)
            def nowTime = new Date().getTime()
            def activity1 = Activity.findOrCreateWhere(title: 'Coorindate Vendors')
            activity1.properties = [description: 'Coordinate all the vendors so they know where they need to be and when',
                    numPeopleNeeded: 10, startDate: new Date(nowTime), endDate: new Date(nowTime + (2 * 60 * 60 * 1000)),
                    location: 'Somewhere over the rainbow', event: testEvent1, pointsType: 'Flat', points: 100,
                    featured: true]
            activity1.save(failOnError: true)
            def activity2 = Activity.findOrCreateWhere(title: 'Stuff Bags')
            activity2.properties = [description: 'Stuff the bags for all the runners', numPeopleNeeded: 10,
                    startDate: new Date(nowTime+ (1 * 60 * 60 * 1000)),
                    endDate: new Date(nowTime + (3 * 60 * 60 * 1000)), location: 'Somewhere over the rainbow',
                    event: testEvent2, pointsType: 'Flat', points: 100, featured: true]
            activity2.save(failOnError: true)

            // CatalogItems
            CatalogItem mnDisc = CatalogItem.findOrCreateWhere(name: 'Minneapolis Marathon - 50% Race Discount')
            mnDisc.properties = [description: '', category: CICRaceDiscounts, available: true, points: 3000, photo: null]
            mnDisc.save(failOnError:true)
            CatalogItem dash = CatalogItem.findOrCreateWhere(name: 'Monster Dash Stocking Cap')
            dash.properties = [description: '', category: CICApparel, available: true, points: 500, photo: null]
            dash.save(failOnError:true)

            /* Users */
            User adminUser = User.findOrCreateWhere(username: 'admin')
            adminUser.properties = [firstName: 'Joe',lastName: 'Tester', email: 'joe@example.com',
                    password:  'test123', enabled: true, status: activeStatus, phone: '612-555-6789',
                    chapter: mnChapter, currPoints: 1000]
            adminUser.save(failOnError:true)

            UserRole.findOrSaveWhere(secUser: adminUser,secRole: superAdminRole)


            User volunteerUser =  User.findOrCreateWhere(username: 'guest')
            volunteerUser.properties = [firstName: 'Volunteer', lastName: 'Tester',
                    email: 'enlistappg48@gmail.com',  // test email reminder. must use valid address.
                    username: 'guest', password:  'test123', enabled: true,
                    status: activeStatus, phone: '651-555-1234', chapter: mnChapter, currPoints: 5000]
            volunteerUser.save(failOnError:true)

            UserRole.findOrSaveWhere( secUser: volunteerUser, secRole: volunteerRole)

            // ActivitySignUps
            ActivitySignUp signUp1 = ActivitySignUp.findOrCreateWhere(user: volunteerUser, activity: activity1)
            signUp1.properties = [signUpTime: new Date(), reminderAt: new Date(nowTime + (15* 60 * 1000))]
            signUp1.save(failOnError: true)  // give me reminder in 15 minutes

            ActivitySignUp signUp2 = ActivitySignUp.findOrCreateWhere(user: volunteerUser, activity: activity2)
            signUp2.properties = [signUpTime: new Date(), reminderAt: new Date(nowTime + (45* 60 * 1000))]
            signUp2.save(failOnError: true)  // give me reminder in 15 minutes

            //test display PointTransaction
    //            buildTestDataPointTxn()

            User volunteerUser2 =  User.findOrCreateWhere(username: 'guest2')
            volunteerUser2.properties = [firstName: 'Volunteer2', lastName: 'Tester',
                    email: 'imms.noreply@gmail.com',  // test email reminder. must use valid address.
                    username: 'guest2', password:  'test123', enabled: true,
                    status: activeStatus, phone: '651-555-1234', chapter: mnChapter, currPoints: 5000]
            volunteerUser2.save(failOnError:true)

            UserRole.findOrSaveWhere( secUser: volunteerUser2, secRole: volunteerRole)
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
