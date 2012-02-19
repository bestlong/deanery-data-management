package decanat.grails.domain

import grails.test.*
import decanat.grails.Plan
import decanat.grails.Speciality

class PlanIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreatePlan() {
        def plan = new Plan()

        assertFalse plan.validate()

        def spec = new Speciality(
                kod: "00.12.k",
                name: "computer systems",
                shortName: "cs"
        ).save()

        assertNotNull spec
        assertNotNull spec.id

        plan.setSpeciality(spec)
        plan.setYear(-1)

        assertFalse plan.validate()

        plan.setYear(2)

        assert plan.validate()
        assertNotNull plan.save()

        plan.setDirection("dir")
        plan.setForm("form")
        plan.setLevel("lvl")
        plan.setPeriod("pr")

        assert plan.validate()
        assertNotNull plan.save()

        def dbPlan = Plan.findById(plan.id)

        assertNotNull dbPlan
        assertEquals(dbPlan.speciality, spec)
    }
}
