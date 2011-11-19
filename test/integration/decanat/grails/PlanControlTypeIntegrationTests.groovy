package decanat.grails

import grails.test.*

class PlanControlTypeIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreation() {
        def pContType = new PlanControlType()

        assertFalse pContType.validate()

        def sem = new Semestr(
                number: 1,
                weekCount: 10
        ).save()

        assertNotNull sem.id

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

        def pSubj = new PlanSubject(
                plan: plan,
                subject: subj
        ).save()

        assertNotNull pSubj.id

        pContType.planSubject = pSubj
        pContType.semestr = sem

        assert pContType.validate()

        pContType.save()

        assertNotNull pContType.id
    }
}
