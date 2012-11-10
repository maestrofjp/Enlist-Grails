package enlist.grails

class Role {
    public static final String ADMIN = "ROLE_ADMIN"
    public static final String CHAPTER_ADMIN = "ROLE_CHAPTER_ADMIN"
    public static final String ACTIVITY_COORDINATOR = "ROLE_ACTIVITY_COORDINATOR"
    public static final String VOLUNTEER = "ROLE_VOLUNTEER"


    String name
    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
        name blank:  false
    }

	String toString() {
		return "${name} (${authority})"
	}
}