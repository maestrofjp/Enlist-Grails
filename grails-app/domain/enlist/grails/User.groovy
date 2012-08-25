package enlist.grails

class User {

    transient springSecurityService     //SpringSecurityService

    String firstName
	String lastName
	String email
	//String twitterUsername
	//String identicaUsername
	String phone
	Address address
	Chapter chapter
	Status status
	//Role role

    // Spring Security Stuff
    String username
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static constraints = {
        username blank: false, unique: true
        password blank: false

        firstName(blank:false)
        lastName(blank:false)
        email(email:true, unique:true)
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllBySecUser(this).collect { it.Role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    String toString() {
    	return "${lastName}, ${firstName} (${email})"
    }
}
