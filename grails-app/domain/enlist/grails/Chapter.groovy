
package enlist.grails

class Chapter {
	
	String name
	Address address
	Date created = new Date()
	Status status

	static hasMany = [users: User]
	static embedded = ['address']

    static constraints = {
		name(blank:false)
		importFrom Status
		created(blank:false)
		importFrom Address
		users(display:false)
    }

    String toString() {
    	return "${name}"
    }
	
}