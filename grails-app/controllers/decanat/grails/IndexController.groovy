package decanat.grails


import stu.cn.ua.enums.PlanClass

import stu.cn.ua.dbf.reader.PlanSubjectDTOReader

class IndexController {

    def planService
    def excelService
    def authorityService


    def findPlan = {
        def plans = planService.findStudyPlansByParams(params)
        for (Plan p: plans) {
            p.chair.getName();
            p.speciality.getSpecialityCode()
        }
        Faculty faculty
        if (authorityService.isProrektor()) {
            faculty = authorityService.getCurrentUser().getFaculty();
        }
        Plan parametrs = params
        parametrs.speciality = new Speciality();
        parametrs.chair = new Chair();
        parametrs.speciality.name = params.speciality
        parametrs.chair.name = params.chair
        chain(action: 'index', model: [res: plans, msg: "Найдено планов ${plans.size()}", sizePerPage: plans.size(), param: parametrs, faculty: faculty])
    }

    def index = {
        def planList
        def param = chainModel?.param
        def faculty = chainModel?.faculty
        def msg = chainModel?.msg ?: ""
        def totalPlans = planService.findByDiscriminatorCount(PlanClass.STUDY)
        if (chainModel?.res == null)
            if (params.offset == null) {
                planList = planService.findByDiscriminatorForPaginating(PlanClass.STUDY, 10, 0)
            }
            else {
                planList = planService.findByDiscriminatorForPaginating(PlanClass.STUDY, params.max as int, params.offset as int)
            }
        else {
            planList = chainModel.res
            totalPlans = planList.size()

            if (!param) {
                Plan plan = new Plan();
                plan.chair = new Chair();
                plan.speciality = new Speciality();
                plan.form = "0";
                plan.semestrCount = 0;
                param = plan;
            }
        }

        if (null == faculty) {
            def did = authorityService.getCurrentUser().getFacultyId()
            if (did != null)
                faculty = Faculty.findById(did)
            if (null == faculty) {
                Faculty dec = new Faculty()
                faculty = dec;
                //"['0': '-Все деканаты-']"
                faculty.id = 0
                faculty.name = "-Все деканаты-";
            }
        }

        [res: planList, param: param, faculty: faculty, totalPlans: totalPlans, active: 1, msg: msg, sizePerPage: chainModel?.sizePerPage]
    }


    def chairPlans = {
        def chair = Chair.get(params.id)
        def plans = planService.findStudyPlansByChair(chair)
        plans.each {
            def useless = it.speciality.code
        }

        chain(action: 'index', model: [res: plans, msg: "Список учебных планов кафедры '${chair.name}", sizePerPage: plans.size()])
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
        render(template: "/template/workPlans", model: ["plans": plans, "faculty": planService.getFaculty()])
    }

    def print = {
        try {
            response.contentType = "application/vnd.ms-excel"
            response.setHeader("Content-disposition", "attachment;filename=work-plan.xls")
            excelService.exportToExcel(Plan.get(params.id), new Date(), response.outputStream)
            response.outputStream.flush()
        } catch (Exception e) {
            log.error("error while exporting into excel: ", e)
        }
    }

    def dbfImport = {
        try {
            def plan = Plan.get(params.importPlanId)
            def reader = new PlanSubjectDTOReader(plan)
            def is = request.getFile("dbf").inputStream
            reader.read(is)
            def errors = reader.validate()
            if (!errors) {
                Integer count = reader.save()
                flash.message = "В результате импорта удачно сохранено ${count} записей"
                redirect(action: 'index')
            } else {
                chain(controller: 'DBFImport', action: 'index', model: [validationErrors: errors])
            }
        } catch (Exception e) {
            log.error("error while importing dbf: ", e)
        }
    }
}
