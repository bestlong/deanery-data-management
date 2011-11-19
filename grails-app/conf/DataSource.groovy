hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
		dataSource {
			username = "decanat"
			password = "decanat"
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:postgresql://localhost:5432/dec-plan"
            pooled = true
	        driverClassName = "org.postgresql.Driver"
		}
    }
    test {
        dataSource {
            username = "decanat"
			password = "decanat"
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:postgresql://localhost:5432/dec-plan"
            pooled = true
	        driverClassName = "org.postgresql.Driver"
        }
    }
    production {
        dataSource {
			username = "decanat"
			password = "decanat"
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:postgresql://localhost:5432/dec-plan"
            pooled = true
	        driverClassName = "org.postgresql.Driver"
        }
    }
}
