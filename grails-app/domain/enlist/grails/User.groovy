package enlist.grails

import org.apache.commons.lang.StringUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class User {

    transient springSecurityService     //SpringSecurityService

    String firstName
	String lastName
	String email
	String phone
	Address address = null
	Status status
	UserProfile profile
	//Role role

    // Spring Security stuff
    String username
    String password
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    Integer currPoints = 0
	
	static belongsTo = [chapter: Chapter]
	
	static embedded = ['address']

    static searchable = {
        firstName index : "analyzed"
        lastName index : "analyzed"
    }

    static constraints = {
        username(blank: false
			, unique: true
			, size: 3..12
			, matches: '[0-9a-zA-Z]{3,12}'
		)
        password(blank: false, password: true, validator: { pwd, user ->
				return pwd != user.username
			}
		)
        enabled(default: true, blank: false)
        accountExpired(default: false, blank: false)
        accountLocked(default: false, blank: false)
        passwordExpired(default: false, blank: false)

		status()
		firstName(blank:false)
    	lastName(blank:false)
    	email(blank:false
			, email:true
			, unique:true
		)
		phone(nullable: true)
		address(nullable: true)
		chapter(nullable: true)
		profile(nullable: true)
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllBySecUser(this).collect { it.secRole } as Set
    }
    // might as well create similar method for other Role. if the role is dynamic, we can move this to bootstrap (meta programming)
    boolean checkVolunteer() {
		Set<Role> roles = authorities
		for(Role role : roles) if(StringUtils.equals(role.authority, Role.VOLUNTEER)) return true
        return false
    }
	
    boolean checkAdmin() {
        return SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN,ROLE_CHAPTER_ADMIN,ROLE_ACTIVITY_COORDINATOR")
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
    	return "${lastName}, ${firstName} (${username})"
    }
}
