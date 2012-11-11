package enlist.grails

import spock.lang.Specification
import grails.plugin.spock.IntegrationSpec
import spock.lang.Shared
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.apache.commons.lang.StringUtils

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 11/11/12
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
class TransferPointSpec extends IntegrationSpec {
    @Shared PointTransactionController controller

    @Shared User bob
    @Shared User jack
    @Shared def pointTransactionService
    @Shared def daoAuthenticationProvider
    @Shared def springSecurityService



    def setupSpec() {
        controller = new PointTransactionController()
        def superAdminRole = Role.build(name: 'Organization Administrator', authority: Role.ADMIN)
        def volunteerRole = Role.build(name: 'Volunteer', authority: Role.VOLUNTEER)
        def activeStatus = Status.build(status: 'Active').save(failOnError: true)
        def mnAddress= Address.build(address1: '123 Main Street', city: 'Minneapolis', state: 'MN', zip: '54321')
        def mnChapter = Chapter.build(name: 'Minneapolis', address: mnAddress, status: activeStatus)

        User adminUser = User.build(firstName: 'Joe', lastName: 'Tester', email: 'joe@example.com', username: 'admin',
                password:  'test123', enabled: true, status: activeStatus, phone: '612-555-6789', chapter: mnChapter)
        UserRole.build(secUser: adminUser, secRole: superAdminRole)
        bob = User.build(firstName: 'Bob', lastName: 'Tester', email: 'joe2@example.com', username: 'Bob',
                password:  'test123', enabled: true, status: activeStatus, phone: '612-555-6789', chapter: mnChapter)
        UserRole.build(secUser: bob, secRole: volunteerRole)
        jack = User.build(firstName: 'Jack', lastName: 'Tester', email: 'joe3@example.com', username: 'Jack',
                password:  'test123', enabled: true, status: activeStatus, phone: '612-555-6789', chapter: mnChapter)
        UserRole.build(secUser: jack, secRole: volunteerRole)
    }
    def cleanupSpec() { println 'base cleanupSpec()' }
    def loginAs(User user) {
        def auth = new UsernamePasswordAuthenticationToken(user.username, 'test123' )
        def authtoken = daoAuthenticationProvider.authenticate(auth)
        SecurityContextHolder.getContext().setAuthentication(authtoken)
    }

    def "Volunteer should be able to transfer his points to another volunteer"() {
        given: "A volunteer, Bob, has 50 points in his account."
        pointTransactionService.saveTxn(new PointTransaction(acctOwner: bob, txnDate: new Date(),
                txnType: PointTransaction.VOLUNTEER, amount: 50, description: "initial point"))
        and: "Jack is another volunteer with 0 point"
        when: "Bob logs in"
        loginAs(bob)
        and: "Bob transfers 5 points to Jack"
        controller.params['recipient.id'] = jack.id
        controller.params['point'] = 5
        controller.transfer()
        then: "Bob's now having 45 points left in his account"
        bob.currPoints == 45
        and : "Transfer is recorded in Bob's point transaction list"
        def bobsTxn = PointTransaction.findAllByAcctOwnerAndTxnType(bob, PointTransaction.TRANSFER)
        bobsTxn.size() == 1
        bobsTxn.each {
            PointTransaction it ->
            it.amount == -5
        }
        and : "Jack's now having 5 points in his account"
        jack.currPoints == 5
        and : "Transfer is recorded in Jack's point transaction list"
        def jacksTxn = PointTransaction.findAllByAcctOwnerAndTxnType(jack, PointTransaction.TRANSFER)
        jacksTxn.size() == 1
        jacksTxn.each {
            PointTransaction it ->
            it.amount == 5
        }
    }
}
