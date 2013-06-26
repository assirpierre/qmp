dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    dialect = net.sf.hibernate.dialect.PostgreSQLDialect
}
hibernate {
    cache.use_second_level_cache=false
    cache.use_query_cache=false
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://localhost:5433/qmw"
            username = "sicon"
            password = "sicon"
//			url = "jdbc:postgresql://qmenu.com.br:5433/qmenu"
//     		password = "batata00"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://127.0.0.1:5432/qmw"
            username = "sicon"
            password = "sicon"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://127.0.0.1:5433/qmenu"
            username = "sicon"
            password = "batata00"
        }
    }
}