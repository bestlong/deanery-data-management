package decanat.grails.domain

import grails.test.*
import decanat.grails.User

class UserIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        def user = new User()
        user.email = "mail@test"

        assertFalse user.validate()

        user.email = "mail@test.com"
        user.username = "test"
        user.userRealName = "real tester"
        user.passwd = "pass"

        assert user.validate()

        user.save()

        assertNotNull user.id
    }
}
