package decanat.grails

class PlanPractiseIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreation() {
        def pPractise = new PlanPractice()

        assertFalse pPractise.validate()

        pPractise.name = "practise"
        pPractise.kafedraName = "kaf"
        pPractise.term = 1
        pPractise.weaks = 2

        assertFalse pPractise.validate()

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

        pPractise.plan = plan

        assert pPractise.validate()

        pPractise.save()

        assertNotNull pPractise.id
    }
}
