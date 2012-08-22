package enlist.grails

class User {
	
	String firstName
	String lastName
	String email
	String twitterUsername
	String identicaUsername
	String phone
	String address1
	String address2
	String city
	String state
	String zip
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
