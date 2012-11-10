package enlist.grails

class EnlistTagLib {

    static namespace = "enlist"
	
	def springSecurityService

    List adminAreas = new ArrayList<NavItem>([
        new NavItem(name: "Home",       controller: "/"),
        new NavItem(name: "Chapters",   controller: "chapter"),
        new NavItem(name: "Events",     controller: "event"),
        new NavItem(name: "Activites",  controller: "activity"),
        new NavItem(name: "Users",      controller: "user")
    ])

	List volunteerAreas = new ArrayList<NavItem>([
		new NavItem(name: "Home",       controller: "/"),
		new NavItem(name: "Events",     controller: "event"),
		new NavItem(name: "Activites",  controller: "activity")
	])

	def nav = { attrs, body ->
		def user = springSecurityService.getCurrentUser()
		
		if (user?.checkAdmin()) {
			out << g.render(template: "/nav", model: [areas: adminAreas])
		} else {
			out << g.render(template: "/nav", model: [areas: volunteerAreas])
		}
	}
	
	def isAdmin = { attrs, body -> 
		def user = springSecurityService.getCurrentUser()
		
		if (user?.checkAdmin()) {
			out << body()
		}
    }
}	

class NavItem {
    String name;
    String controller;
}
