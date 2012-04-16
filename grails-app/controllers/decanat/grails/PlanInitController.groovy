package decanat.grails

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.converters.JSON

class PlanInitController {
    def planInitService
    def specialityService
    def semestrService
    def chairService
    def sessionParamsService


    def index = {
        Plan plan;
        if (params.id) {
            plan = Plan.findById(params.id)
        }
        else {
            plan = new Plan()
        }
        params.clear()
        sessionParamsService.saveParams(params)
        [res: [], plan: plan, chairs: [], searchConfig: getSearchChairConfig(), searchSpecialityConfig: getSearchSpecialityConfig()]
    }

    def getPropertiesToRenderSpeciality() {
        ['code', 'name', 'shortName', 'specialityCode', 'id']
    }

    def specialityTable = {
        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []

        def list = specialityService.findSpeciality(params, getPropertiesToRenderSpeciality())
        dataToRender.iTotalRecords = list.totalCount
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords
        list.each { speciality ->
            def record = []
            record << speciality.code
            record << speciality.name
            record << speciality.shortName
            record << speciality.specialityCode
            record << speciality.id
            dataToRender.aaData << record
        }
        render dataToRender as JSON
    }

    def getPropertiesToRender() {
        ["codeChair", "name", "shortName", "id"]
    }

    def chairTable = {
        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []

        def list = chairService.findChairs(params, getPropertiesToRender())
        dataToRender.iTotalRecords = list.totalCount
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords
        list.each { subject ->
            def record = []
            record << subject.codeChair
            record << subject.name
            record << subject.shortName
            record << subject.id
            dataToRender.aaData << record
        }
        render dataToRender as JSON
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
        sessionParamsService.saveParams(params)
        render(template: "/template/speciality/selectSpeciality", model: [res: []]);
    }

    def searchChair = {
        sessionParamsService.saveParams(params)
        render(template: "/template/chair/selectChair", model: [res: []]);
    }


    def getSearchChairConfig(){
        return [action: 'searchChair', controller: 'planInit', successFunction: 'initTableChair()']
    }

    def getSearchSpecialityConfig(){
        return [action: 'search', controller: 'planInit', successFunction: 'initTableSpeciality()']
    }
}
