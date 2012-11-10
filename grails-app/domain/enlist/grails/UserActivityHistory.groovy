package enlist.grails

import org.apache.commons.lang.builder.HashCodeBuilder

class UserActivityHistory implements Serializable {

    User user
    Activity activity
    Date recordedAt
    Integer pointGained = 0

    boolean equals(other) {
        if (!(other instanceof UserActivityHistory)) {
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

    static UserActivityHistory get(long userId, long activityId) {
        find 'from UserActivityHistory where user.id=:userId and activity.id=:activityId',
                [userId: userId, activityId: activityId]
    }
    static Set<Activity> findAllByUserId(long userId) {
        def res = findAll('from UserActivityHistory where user.id=:userId', [userId: userId])
        res.collect { it.activity } as Set
    }
    static Set<User> findAllByActivityId(long activityId) {
        def res = findAll( 'from UserActivityHistory where activity.id=:activityId', [activityId: activityId])
        res.collect {it.user} as Set
    }

    static UserActivityHistory create(User user, Activity activity, Integer pointGained,  boolean flush = false) {
        new UserActivityHistory(user: user, activity: activity, recordedAt: new Date(), pointGained: pointGained ?: 0).save(
                flush: flush, insert: true, failOnError : true)
    }

    static boolean remove(User user, Activity activity, boolean flush = false) {
        UserActivityHistory instance = UserActivityHistory.findByUserAndActivity(user, activity)
        instance ? instance.delete(flush: flush) : false
    }

    static void removeAll(User user) {
        executeUpdate 'DELETE FROM UserActivityHistory WHERE user=:user', [user: user]
    }

    static void removeAll(Activity activity) {
        executeUpdate 'DELETE FROM UserActivityHistory WHERE activity=:activity', [activity: activity]
    }

    static mapping = {
        id composite: ['activity', 'user']
        version false
        user index : 'userActivityHistoryIdx'
        activity index : 'userActivityHistoryIdx'
    }
    static constraints = {
        pointGained(min: 0)
    }
}
