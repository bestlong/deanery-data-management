package decanat.grails.domain

import grails.test.*
import decanat.grails.PlanStateExam
import decanat.grails.Speciality
import decanat.grails.Plan

class PlanStateExamIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreation() {
        def pExam = new PlanStateExam()

        assertFalse pExam.validate()

        pExam.forma = "f"

        pExam.date= "dt"
        pExam.semestr = 1

        assertFalse pExam.validate()

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

        pExam.plan = plan

        assert pExam.validate()

        pExam.save()

        assertNotNull pExam.id
    }
}
