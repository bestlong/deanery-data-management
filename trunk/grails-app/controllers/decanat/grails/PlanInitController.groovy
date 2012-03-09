package decanat.grails

class PlanInitController {
    def planInitService
    def specialityService
    def semestrService
    def chairService


    def index = {
        Plan plan;
        if (params.id) {
            plan = Plan.findById(params.id)
        }
        else {
            plan = new Plan()
        }
        def specList = Speciality.list()
        def chairList = Chair.list()
        [res: specList, plan: plan, chairs: chairList, searchConfig: getSearchChairConfig(), searchSpecialityConfig: getSearchSpecialityConfig()]
    }

    def next = {
        def plan = planInitService.savePlan(params)
        try {
            if (plan) {
                flash.message = "plan.init.done"
            }
            else {
                flash.error = "plan.init.error"
            }
            semestrService.cleanSemesters(plan.semestrCount, plan)
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = "plan.init.error"
        }
        redirect(action: index, controller: "addSubjects", id: plan.id)
    }

    def search = {
        def res = specialityService.findSpecialities(params.code, params.name, params.shortName);
        render(template: "/template/speciality/selectSpeciality", model: [res: res]);
    }

    def searchChair = {
        def res = chairService.findChairs(params.name, params.shortName);
        render(template: "/template/chair/selectChair", model: [res: res]);
    }


    def getSearchChairConfig(){
        return [action: 'searchChair', controller: 'selectSpeciality', successFunction: 'initTableChair()']
    }

    def getSearchSpecialityConfig(){
        return [action: 'search', controller: 'selectSpeciality', successFunction: 'initTableSpeciality()']
    }
}