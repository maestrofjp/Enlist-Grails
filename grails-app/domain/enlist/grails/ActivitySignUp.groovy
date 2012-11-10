package enlist.grails

import org.apache.commons.lang.builder.HashCodeBuilder

class ActivitySignUp implements Serializable {

    User user
    Activity activity
    Date signUpTime

    boolean equals(other) {
        if (!(other instanceof ActivitySignUp)) {
            return false
        }

        other.user?.id == user?.id &&
                other.activity?.id == activity?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (activity) builder.append(activity.id)
        builder.toHashCode()
    }

    static ActivitySignUp get(long userId, long activityId) {
        find 'from ActivitySignUp where user.id=:userId and activity.id=:activityId',
                [userId: userId, activityId: activityId]
    }
    static Set<Activity> findAllByUserId(long userId) {
        def res = findAll('from ActivitySignUp where user.id=:userId', [userId: userId])
        res.collect { it.activity } as Set
    }
    static Set<User> findAllByActivityId(long activityId) {
        def res = findAll( 'from ActivitySignUp where activity.id=:activityId', [activityId: activityId])
        res.collect {it.user} as Set
    }

    static ActivitySignUp create(User user, Activity activity, boolean flush = false) {
        new ActivitySignUp(user: user, activity: activity, signUpTime: new Date()).save(
                flush: flush, insert: true, failOnError : true)
    }

    static boolean remove(User user, Activity activity, boolean flush = false) {
        ActivitySignUp instance = ActivitySignUp.findByUserAndActivity(user, activity)
        instance ? instance.delete(flush: flush) : false
    }

    static void removeAll(User user) {
        executeUpdate 'DELETE FROM ActivitySignUp WHERE user=:user', [user: user]
    }

    static void removeAll(Activity activity) {
        executeUpdate 'DELETE FROM ActivitySignUp WHERE activity=:activity', [activity: activity]
    }

    static mapping = {
        id composite: ['activity', 'user']
        version false
        user index : 'userActivityIdx'
        activity index : 'userActivityIdx'
    }
    static constraints = {
    }
}
