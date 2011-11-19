package decanat.grails

class PrinterController {

    //TODO русский календарь

    def planSubjectService

    def index = {
        [planId: params.id]
    }

    def preview = {
        def date, plan, university
        double totalCCount = 0d
        def totalTotal
        def totalLectureCount = 0
        def totalSeminarCount = 0
        def totalPractiseCount = 0
        def totalLabCount = 0
        def totalSamCount = 0

        def totalExamCount = 0
        def totalZachCount = 0
        def totalCProjCount = 0
        def totalCWorkCount = 0
        def totalRgrCount = 0

        def totalSemestrs


        def newSubjescts
        try {
            plan = Plan.get(params.id)
            totalSemestrs = new ArrayList(plan.semestrCount)
            for (int i:1..plan.semestrCount)
                totalSemestrs.add([
                        semestr: i,
                        lectureCount: 0,
                        seminarCount: 0,
                        practiseCount: 0,
                        labCount: 0,
                        zachCount: 0,
                        examCount: 0,
                        cProjCount: 0,
                        cWorkCount: 0,
                        rgrCount: 0,
                        total: 0
                ])
            university = University.findAll().get(0)
            date = new Date().parse("MM/dd/yyyy", params.date_value)

            newSubjescts = new ArrayList();
            def subjects = plan.subjects
            subjects.each {
                def controls = planSubjectService.getControlType(it.id)
                def exam = ""
                def zach = ""
                def cProj = ""
                def cWork = ""
                def rgr = ""
                def contrWork = ""
                controls.each {
                    exam += it.exam == 1 ? it.semestr : ""
                    zach += it.zach == 1 ? it.semestr : ""
                    cProj += it.cProj == 1 ? it.semestr : ""
                    cWork += it.cWork == 1 ? it.semestr : ""
                    rgr += it.rgr == 1 ? it.semestr : ""
                    contrWork += it.contrWork == 1 ? it.semestr : ""

                    totalSemestrs.get(it.semestr-1).examCount += it.exam
                    totalSemestrs.get(it.semestr-1).zachCount += it.zach
                    totalSemestrs.get(it.semestr-1).cProjCount += it.cProj
                    totalSemestrs.get(it.semestr-1).cWorkCount += it.cWork
                    totalSemestrs.get(it.semestr-1).rgrCount += it.rgr

                    totalExamCount += it.exam
                    totalZachCount += it.zach
                    totalCProjCount += it.cProj
                    totalCWorkCount += it.cWork
                    totalRgrCount += it.rgr
                }


                it.planHours.each {
                    totalSemestrs.get(it.semestr-1).lectureCount += it.lectures
                    totalSemestrs.get(it.semestr-1).seminarCount += it.seminars
                    totalSemestrs.get(it.semestr-1).practiseCount += it.practices
                    totalSemestrs.get(it.semestr-1).labCount += it.labs
                    totalSemestrs.get(it.semestr-1).total += it.labs + it.practices + it.seminars + it.lectures
                }

                totalCCount += it.creditCount
                totalLectureCount += it.lectureCount
                totalSeminarCount += it.seminarCount
                totalPractiseCount += it.practiceCount
                totalLabCount += it.labCount
                totalSamCount += it.samCount

                def newSubj = [
                        id: it.id,
                        subject: it.subject,
                        creditCount: it.creditCount,
                        lectureCount: it.lectureCount,
                        seminarCount: it.seminarCount,
                        practiceCount: it.practiceCount,
                        labCount: it.labCount,
                        samCount: it.samCount,
                        exam: exam,
                        zach: zach,
                        cProj: cProj,
                        cWork: cWork,
                        rgr: rgr,
                        contrWork: contrWork,
                        hours: it.planHours
                ]
                newSubjescts.add(newSubj)
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
        }

        BigDecimal totalCreditCount = new BigDecimal(totalCCount);
        totalCreditCount = totalCreditCount.setScale(2, BigDecimal.ROUND_HALF_UP);

        totalTotal = totalLectureCount + totalSeminarCount + totalPractiseCount + totalLabCount + totalSamCount

        [datePrint: date, plan: plan, university: university, semestrs: plan.semestr, subjects: newSubjescts,
        practices: plan.practise, gos: plan.stateExam, totalTotal: totalTotal, totalLectureCount: totalLectureCount,
        totalSeminarCount: totalSeminarCount, totalPractiseCount: totalPractiseCount, totalLabCount:totalLabCount,
        totalSamCount: totalSamCount, totalCreditCount: totalCreditCount, totalSemestrs: totalSemestrs,
        totalExamCount:totalExamCount, totalZachCount: totalZachCount, totalCProjCount: totalCProjCount,
        totalCWorkCount: totalCWorkCount, totalRgrCount: totalRgrCount
        ]
    }
}

