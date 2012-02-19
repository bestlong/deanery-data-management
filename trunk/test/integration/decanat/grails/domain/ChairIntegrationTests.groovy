package decanat.grails.domain

import grails.test.*
import decanat.grails.Chair

class ChairIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateChair() {
        def chair = new Chair()
        assertFalse chair.validate()

        chair.setName("Test chair")
        assertTrue chair.validate()

        chair.setShortName("TC")

        assertTrue chair.validate()
        assertNotNull chair.save()
        assertNotNull chair.id

        def dbChair = Chair.findById(chair.id)

        assertNotNull dbChair
        assertEquals("Test chair", dbChair.name)
        assertEquals("TC", dbChair.shortName)

        def anotherChair = new Chair(name: chair.name)

        assertFalse anotherChair.validate()

        anotherChair.setName("Another test chair")

        assertTrue anotherChair.validate()
        assertNotNull anotherChair.save()
        assertNotNull anotherChair.id

        dbChair = Chair.findById(anotherChair.id)

        assertNotNull dbChair
        assertEquals(dbChair.name, anotherChair.name)
    }
}
