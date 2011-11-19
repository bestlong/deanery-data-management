package decanat.grails

import grails.test.*

class UniversityIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreatingUniversity() {
        def university = new University()

        assertFalse university.validate()

        university.dean = "dean"
        university.head = "head"
        university.rektor = "rector"

        assert university.validate()

        university.save()

        assertNotNull university.id
    }
}
