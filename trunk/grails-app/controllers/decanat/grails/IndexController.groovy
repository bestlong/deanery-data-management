package decanat.grails

import stu.cn.ua.enums.PlanClass

class IndexController {

    def planService
    def excelService

    def chairPlans = {
        def chair = Chair.get(params.id)
        def plans = planService.findStudyPlansByChair(chair)
        plans.each {
            def useless = it.speciality.code
        }
        chain(action: 'index', model: [res: plans, msg: "Список учебных планов кафедры '${chair.name}", sizePerPage: plans.size()])
    }


    def index = {
        def planList
        def msg = chainModel?.msg ?: ""
        def totalPlans = planService.findByDiscriminatorCount(PlanClass.STUDY)
        if (chainModel?.res == null)
            if (params.offset == null) {
                planList = planService.findByDiscriminatorForPaginating(PlanClass.STUDY, 10, 0)
            }
            else {
                planList = planService.findByDiscriminatorForPaginating(PlanClass.STUDY, params.max as int, params.offset as int)
            }
        else{
            planList = chainModel.res
            totalPlans = planList.size()
        }
        [res: planList, totalPlans: totalPlans, univer: University.list(), active: 1, msg: msg, sizePerPage: chainModel?.sizePerPage]
    }

    def delete = {
        try {
            if (params.id) {
                Plan plan = Plan.get(params.id);
                if (plan) {
                    plan.delete(flush: true);
                    flash.message = message(code: "msg.plan.remove.success")
                }
            }
            else {
                flash.error = message(code: "msg.plan.remove.error")
            }
        }
        catch (Exception e) {
            flash.error = message(code: "msg.plan.remove.error")
            log.error(e.getMessage(), e)
        }
        redirect(controller: 'index', action: 'index')
    }

    def showWorkPlans = {
        def plans = planService.findWorkPlansByStudyPlan(Plan.get(params.id))
        render(template: "/template/workPlans", model: ["plans": plans, univer: University.list()])
    }

    def print = {
        try {
            log.error "111111111111111111111111111111111111"
            response.contentType = "application/vnd.ms-excel"
            log.error "2222222222222222222222222222222222222"
            response.setHeader("Content-disposition", "attachment;filename=work-plan.xls")
            log.error "33333333333333333333333333333333333333333"
            excelService.exportToExcel(Plan.get(params.id), new Date(), response.outputStream)
            log.error "4444444444444444444444444444444444444444444"
        } catch(Exception e) {
            log.error("error while exporting into excel: ", e)
        }
    }
}
