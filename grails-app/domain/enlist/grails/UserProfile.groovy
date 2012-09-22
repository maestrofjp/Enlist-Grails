package enlist.grails

class UserProfile {

	static belongsTo = User
	
	String twitterUsername
	String identicaUsername
	String timezone
	byte[] photo
	
    static constraints = {
		twitterUsername(nullable: true)
		identicaUsername(nullable: true)
		timezone(nullable: true)
		photo(nullable: true)
    }
}