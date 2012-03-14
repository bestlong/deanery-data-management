class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
			}
		}
		"/"(controller: 'index', action: 'index')
        "404"(controller: 'error', action: 'not_found')
		"500"(controller: 'error', action: 'unexpected_error')
	}
}
