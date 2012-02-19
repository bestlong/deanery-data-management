package decanat.grails.domain

import grails.test.*
import decanat.grails.Subject
import decanat.grails.Chair

class SubjectIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        def subj = new Subject()
        subj.setName("OOP Programming")

        assertFalse subj.validate()

        def chair = new Chair(
                name: "Test chair",
                shortName: "TC"
        ).save()

        assertNotNull chair.id

        subj.chair = chair
        subj.shortName = "OOP"

        assert subj.validate()
        assertNull subj.id

        subj.save()

        assertNotNull subj.id

        def dbSubj = Subject.findByChair(chair)

        assertNotNull dbSubj
    }
}
