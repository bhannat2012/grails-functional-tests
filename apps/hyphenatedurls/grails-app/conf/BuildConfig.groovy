grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.work.dir="target/work"
grails.project.dependency.resolver="maven"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.16'
        test 'net.sourceforge.htmlunit:htmlunit:2.12', {
            // excludes 'xalan', 'xercesImpl'
        }

    }

    plugins {
        runtime ":hibernate:3.6.10.M3" // or ":hibernate4:4.1.11.BUILD-SNAPSHOT"
        build ":tomcat:7.0.40.1", {
            // exclude "tomcat-embed-logging-juli"
            // exclude "org.apache.tomcat:tomcat-catalina-ant"
        }


        compile ":jquery:1.8.3"
        compile ":resources:1.1.2"
        compile ':scaffolding:1.0.0'

        runtime ":database-migration:1.0"

        test ':functional-test:2.0.RC1', {
            excludes 'htmlunit'
        }
    }
}
