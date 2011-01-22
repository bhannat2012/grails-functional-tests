package modifybeans

class ModifyBeansFunctionalTests extends functionaltestplugin.FunctionalTestCase {
    void testSwitchLanguages() {
        // Here call get(uri) or post(uri) to start the session
        // and then use the custom assertXXXX calls etc to check the response
        //
        // get('/something')
        // assertStatus 200
        // assertContentContains 'the expected text'
        get "/modifyBeans"
        assertContentContains "Welcome to Grails"

        get "/modifyBeans?language22=fr"
        assertContentContains "Bienvenue à Rails"
    }
}
