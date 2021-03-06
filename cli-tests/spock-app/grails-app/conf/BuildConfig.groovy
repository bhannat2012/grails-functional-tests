grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.work.dir = "target/$grailsVersion"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

/*
grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]
*/

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        mavenRepo "http://repo.grails.org/grails/core"
    }

    dependencies {
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.40.1"

        // plugins for the compile step
        compile ":scaffolding:2.0.0.BUILD-SNAPSHOT"
        compile ':cache:1.0.1'

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.M3" // or ":hibernate4:4.1.11.BUILD-SNAPSHOT"
        runtime ":database-migration:1.3.3"
        runtime ":jquery:1.10.0"
        runtime ":resources:1.2"

        test ":spock:0.7", {
            excludes "spock-grails-support"
        }
    }
}
