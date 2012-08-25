package enlist.grails

class User {
	
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
    	status()
		role()
		firstName(blank:false)
    	lastName(blank:false)
    	email(blank:false, email:true, unique:true)
		phone(nullable: true)
		address(nullable: true)
		chapter(nullable: true)
    }

    String toString() {
    	return "${lastName}, ${firstName} (${email})"
    }
}