dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
//    production {
//        dataSource {
//            dbCreate = "update"
//            driverClassName = "com.mysql.jdbc.Driver"
//        }
//    }
    production {
        def envVar = System.getenv("VCAP_SERVICES")
        def credentials = envVar?grails.converters.JSON.parse(envVar)["mysql-5.1"][0]["credentials"]:null
        println "envVar ${credentials}"
        // add the following to system environment to simulate AppFog
        //VCAP_SERVICES="{'mysql-5.1':[{'name':'mysql-4f700','label':'mysql-5.1', 'plan':'free', 'tags':['mysql','mysql-5.1','relational'], 'credentials':{ 'name':'enlist', 'hostname':'localhost', 'host':'localhost', 'port':3306, 'user':'root', 'username':'root', 'password':'root' } } ]}"
        dataSource {
            pooled = true
            dbCreate = "update"
            driverClassName = "com.mysql.jdbc.Driver"
            url =  credentials?"jdbc:mysql://${credentials.hostname}:${credentials.port}/${credentials.name}?useUnicode=yes&characterEncoding=UTF-8":""
            username = credentials?credentials.username:""
            password = credentials?credentials.password:""
        }
    }
}
