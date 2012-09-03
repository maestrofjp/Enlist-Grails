package enlist.grails

class Role {

    String name
    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
        name blank:  false
    }

	String toString() {
		return "${name} (${authority})"
	}
}