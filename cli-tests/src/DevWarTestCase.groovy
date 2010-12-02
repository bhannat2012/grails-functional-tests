import java.util.zip.ZipFile

class DevWarTestCase extends AbstractCliTestCase {
    File eventsScript

    protected void setUp() {
        workDir = new File(baseWorkDir, "app1")

        // We add an events script that prints out the value of the grails.env
        // system property so that we can check it is set correctly.
        eventsScript = new File(workDir, "scripts/_Events.groovy")
        assert !eventsScript.exists()

        eventsScript << '''
                eventWarStart = {
                    println "grails.env = ${System.getProperty("grails.env")}"
                }
                '''.stripIndent()
    }

    protected void tearDown() {
        eventsScript.delete()
    }

    void testNoArgs() {
        execute([ "dev", "war" ])

        assertEquals 0, waitForProcess()
        verifyHeader()
        assertTrue output.contains("Environment set to development")
        assertTrue output.contains("grails.env = development")
        assertTrue output.contains("[gspc] Compiling")
        assertTrue((output =~ /\[mkdir\] Created dir: \S+\/stage/) as Boolean)
        
        // Check that the WAR file exists.
        def warFile = new File(workDir, "target/app1-0.1.war")
        assertTrue "WAR file does not exist, or it has the wrong path and/or filename.",
                   warFile.exists()

        // Check that the WAR includes some of the dependencies we expect.
        def warEntries = [] as Set
        def warZip = new ZipFile(warFile)
        for (entry in warZip.entries()) {
            if (!entry.directory) {
                warEntries << entry.name
            }
        }

        assertTrue "aspectjweaver-1.6.8.jar file is missing from the WAR",
                   warEntries.contains("WEB-INF/lib/aspectjweaver-1.6.8.jar")
        assertTrue "log4j-1.2.15.jar file is missing from the WAR",
                   warEntries.contains("WEB-INF/lib/log4j-1.2.16.jar")
        assertTrue "grails-bootstrap-${grailsVersion}.jar file is missing from the WAR",
                   warEntries.contains("WEB-INF/lib/grails-bootstrap-${grailsVersion}.jar".toString())
        assertTrue "commons-io-1.4.jar file is missing from the WAR",
                   warEntries.contains("WEB-INF/lib/commons-io-1.4.jar")
    }
}
