package enlist.grails

class EnlistTagLib {

    static namespace = "enlist"

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

	
    def adminNav = {attrs ->
        out << g.render(template: "/nav", model: [areas: adminAreas])
    }

	def volunteerNav = {attrs ->
		out << g.render(template: "/nav", model: [areas: volunteerAreas])
	}
	
}	

class NavItem {
    String name;
    String controller;
}
