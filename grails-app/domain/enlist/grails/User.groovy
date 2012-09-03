package enlist.grails

class User {

    transient springSecurityService     //SpringSecurityService

    String firstName
	String lastName
	String email
	String phone
	Address address = null
	Status status
	//Role role

    // Spring Security stuff
    String username
    String password
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
	
	static belongsTo = [chapter: Chapter]
	
	static embedded = ['address']

    static constraints = {
        username(blank: false, unique: true)
        password(blank: false)
        enabled(default: true, blank: false)
        accountExpired(default: false, blank: false)
        accountLocked(default: false, blank: false)
        passwordExpired(default: false, blank: false)

		status()
		firstName(blank:false)
    	lastName(blank:false)
    	email(blank:false, email:true, unique:true)
		phone(nullable: true)
		address(nullable: true)
		chapter(nullable: true)
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllBySecUser(this).collect { it.secRole } as Set
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
