class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/"(controller:'dashboard', action:'index')
		"404"(view:'/404')
		"500"(controller:'errors', action:'serverError')
	}
}
