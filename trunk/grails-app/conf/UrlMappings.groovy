class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'login')
        "404"(controller:'error', view:'index', action:'not_found')
		"500"(controller:'error', view:'index', action:'unexpected_error')
	}
}
