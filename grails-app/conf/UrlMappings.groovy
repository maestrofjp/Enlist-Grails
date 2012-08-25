class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"404"(view:'/errors/404')
		"500"(controller:'errors', action:'serverError')
	}
}
