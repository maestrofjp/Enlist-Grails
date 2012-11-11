package enlist.grails



import org.junit.*
import grails.test.mixin.*
import grails.plugins.springsecurity.SpringSecurityService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@TestFor(PointTransactionController)
@Mock([PointTransaction,Status,Role,User,UserRole,SpringSecurityService])
class PointTransactionControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pointTransaction/list" == response.redirectedUrl
    }

    void testListByVolunteer() {
        def users = prepareData()
        controller.metaClass.getLoginUser = { users.volunteer }
        def model = controller.list()

        assert model.pointTransactionInstanceList.size() == 5
        assert model.pointTransactionInstanceTotal == 5
    }
//    void testListByAdmin() {
//        def users = prepareData()
//        SpringSecurityUtils.metaClass.static.ifAnyGranted = { true}
//        controller.metaClass.getLoginUser = { users.admin }
//        def model = controller.list()
//
//        assert model.pointTransactionInstanceList.size() == 10
//        assert model.pointTransactionInstanceTotal == 10
//    }

    def prepareData() {
        User.metaClass.encodePassword = {}
        User.metaClass.isDirty  = {false}
        def activeStatus = new Status(status: 'Active').save(failOnError: true)
        def superAdminRole = new Role(name: 'Organization Administrator', authority: Role.ADMIN).save(failOnError: true)
        def volunteerRole = new Role(name: 'Volunteer', authority: Role.VOLUNTEER).save(failOnError: true)

        User adminUser = new User(
                firstName: 'Joe',
                lastName: 'Tester',
                email: 'joe@example.com',
                username: 'admin',
                password:  'test123',
                enabled: true,
                status: activeStatus
        ).save(failOnError:true)

        new UserRole(
                secUser: adminUser,
                secRole: superAdminRole
        ).save(failOnError: true)
        User volunteerUser = new User(
                firstName: 'Volunteer',
                lastName: 'Tester',
                email: 'joe2@example.com',
                username: 'guest',
                password:  'test123',
                enabled: true,
                status: activeStatus
        ).save(failOnError:true)

        new UserRole(
                secUser: volunteerUser,
                secRole: volunteerRole
        ).save(failOnError: true)
        buildTestDataPointTxn()
        [volunteer : volunteerUser, admin : adminUser]
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
}
