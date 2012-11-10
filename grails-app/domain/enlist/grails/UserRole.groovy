package enlist.grails

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRole implements Serializable {

    User secUser
    Role secRole

    boolean equals(other) {
        if (!(other instanceof UserRole)) {
            return false
        }

        other.secUser?.id == secUser?.id &&
                other.secRole?.id == secRole?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (secUser) builder.append(secUser.id)
        if (secRole) builder.append(secRole.id)
        builder.toHashCode()
    }

    static UserRole get(long secUserId, long secRoleId) {
        find 'from UserRole where secUser.id=:secUserId and secRole.id=:secRoleId',
                [secUserId: secUserId, secRoleId: secRoleId]
    }

    static UserRole create(User secUser, Role secRole, boolean flush = false) {
        new UserRole(secUser: secUser, secRole: secRole).save(flush: flush, insert: true)
    }

    static Set<User> findAllByAuthority(String authority) {
        def res = findAll( 'from UserRole where secRole.authority=:authority', [authority: authority])
        res.collect {it.user} as Set
    }

    static boolean remove(User secUser, Role secRole, boolean flush = false) {
        UserRole instance = UserRole.findBySecUserAndSecRole(secUser, secRole)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(User secUser) {
        executeUpdate 'DELETE FROM UserRole WHERE secUser=:secUser', [secUser: secUser]
    }

    static void removeAll(Role secRole) {
        executeUpdate 'DELETE FROM UserRole WHERE secRole=:secRole', [secRole: secRole]
    }

}
