package decanat.grails

import grails.test.*

class SpecialityIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateSpeciality() {
        def spec = new Speciality()

        assertFalse spec.validate()

        spec.setKod("00.12.k")
        spec.setName("computer systems")

        assertTrue spec.validate()
        assertNotNull spec.save()
        assertNotNull spec.id

        def dbSpec = Speciality.findById(spec.id)

        assertNotNull dbSpec
        assertEquals(spec.name, dbSpec.name)

        def anotherSpec = new Speciality(name: spec.name, kod: "01.00", shortName: "as")

        assertFalse anotherSpec.validate()

        anotherSpec.setName("Another speciality")

        assertTrue anotherSpec.validate()
        assertNotNull anotherSpec.save()
        assertNotNull anotherSpec.id

        dbSpec = Speciality.findById(anotherSpec.id)

        assertNotNull dbSpec
        assertEquals(dbSpec.shortName, anotherSpec.shortName)
    }
}
