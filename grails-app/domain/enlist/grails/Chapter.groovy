
package enlist.grails

class Chapter {
	
	String name
	Address address
	Status status

	static hasMany = [users: User]
	static embedded = ['address']

    static constraints = {
		name(blank:false)
		importFrom Status
		importFrom Address
		users(display:false)
    }

    String toString() {
    	return "${name}"
    }
	
}