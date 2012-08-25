package enlist.grails

class User {

    transient springSecurityService     //SpringSecurityService

    String firstName
	String lastName
	String email
	String phone
	Address address = null
	Status status
	Role role
	
	static belongsTo = [chapter: Chapter]
	
	static embedded = ['address']

    static constraints = {
        username blank: false, unique: true
        password blank: false

		status()
		role()
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
