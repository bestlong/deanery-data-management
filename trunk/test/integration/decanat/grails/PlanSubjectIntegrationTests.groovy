package decanat.grails

import grails.test.*

class PlanSubjectIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreatePlanSubject() {
        def planSubj = new PlanSubject()

        assertFalse planSubj.validate()

        def chair = new Chair(
                name: "Test chair",
                shortName: "TC"
        ).save()

        assertNotNull chair.id

        def subj = new Subject(
                chair: chair,
                name: "Obj or. pr.",
                shortName: "OOP"
        ).save()

        assertNotNull subj.id

        def spec = new Speciality(
                kod: "00.12.k",
                name: "computer systems",
                shortName: "cs"
        ).save()

        assertNotNull spec
        assertNotNull spec.id

        def plan = new Plan(
                speciality: spec,
                direction: "dir",
                form: "from",
                level: "lvl",
                period: "pr",
                year: 1
        ).save()

        assertNotNull plan.id

        planSubj.plan = plan
        planSubj.subject = subj

        assert planSubj.validate()

        planSubj.save()

        assertNotNull planSubj.id
    }
}
