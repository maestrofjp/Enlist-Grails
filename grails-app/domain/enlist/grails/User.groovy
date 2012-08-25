package enlist.grails

class User {
	
	String firstName
	String lastName
	String email
	String twitterUsername
	String identicaUsername
	String phone
	Address address
	Chapter chapter
	Status status
	Role role

    static constraints = {
    	firstName(blank:false)
    	lastName(blank:false)
    	email(email:true, unique:true)
    }

    String toString() {
    	return "${lastName}, ${firstName} (${email})"
    }
}
