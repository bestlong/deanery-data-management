package decanat.grails.domain

import grails.test.*
import decanat.grails.Semestr

class SemestrIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSemestrCreating() {
        def sem = new Semestr(number: -1)

        assertFalse sem.validate()

        sem.number = 1
        sem.weekCount = 10

        assert sem.validate()

        sem.save()

        assertNotNull sem.id
    }
}
