package decanat.grails


import decanat.grails.domain.User
import grails.converters.JSON
import decanat.grails.ChairService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class ChairController {
    def sessionParamsService
    def springSecurityService
    def chairService
     def authorityService

    def getPropertiesToRender() {
        def propertiesToRender
        propertiesToRender = ["id", "codeChair", "name", "shortName", "head", "faculty", "id"]
        propertiesToRender
    }

    def table = {
        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []

        def list = chairService.findChairs(params, getPropertiesToRender())
        dataToRender.iTotalRecords = list.totalCount
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords
        list.each { subject ->
            def record = []
            record << subject.id
            record << subject.codeChair
            record << subject.name
            record << subject.shortName
            record << subject.head
            if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
                record << subject.faculty?.shortName
            }
            record << ' <a href="../../plan/index/chairPlans/' + subject.id.toString() + '">Показать</a> '
            record << subject.referenceCount
            dataToRender.aaData << record
        }
        render dataToRender as JSON
    }

    def index = {
        sessionParamsService.saveParams(params)
        redirect(action: "list", params: params)
    }

    def list = {
        def    faculty
        def did=authorityService.getCurrentUser().getFacultyId()
        if (did!=null)
            faculty=Faculty.findById(did)
        if (null==faculty){
            Faculty dec=new Faculty()
            faculty=dec;
            //"['0': '-Все деканаты-']"
            faculty.id=0
            faculty.name="-Все деканаты-";

        }
        [chairList: chairService.findChairsForCurrentUser(), faculty: faculty, selectedMenu: 3, searchConfig: getSearchChairConfig()]
    }

    def create = {
        if (chainModel != null) {
            [chairInstance: chainModel['chairInstance']]
        }
    }

    def save = {
        try {
            User user = User.get(springSecurityService.principal.id)
            def chairInstance = new Chair(params)
            chairInstance.faculty = user.faculty
            if (chairInstance.save(flush: true)) {
                flash.message = message(code: "msg.chair.add", args: [chairInstance.name, chairInstance.shortName ?: "<не указано>"])
                redirect(action: "list", params: params)
            } else {
                chain(action: 'create', model: [chairInstance: chairInstance], params: params)
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }

    def multipleDelete = {
        int deletedCount = 0
        try {
            def chairs = params.id
            chairs.each {
                def deleteItem = params."multipleDelete${it}"
                if (deleteItem) {
                    def item = Chair.get(it)
                    item.delete(flush: true)
                    deletedCount++
                }
            }
            flash.message = message(code: "message.multiple.delete.success", args: [deletedCount])
        } catch (Exception e) {
            flash.error = message(code: "error.multiple.delete.records")
        }
        redirect(action: 'list')
    }

    def remove = {
        try {
            if (params.id) {
                User user = User.get(springSecurityService.principal.id)
                int id = params.id as int
                Chair chair = Chair.findById(id)
                if (!SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR") && chair.facultyId != user.facultyId) {
                    flash.error = message(code: "msg.remove.error")
                }
                else {
                    chair.delete(flush: true)
                    flash.message = message(code: "msg.chair.remove", args: [chair.name])
                }
            } else {
                flash.error = message(code: "msg.remove.error")
            }
        }
        catch (Exception e) {
            flash.error = message(code: "msg.remove.error")
            log.error(e.getMessage(), e)
        }
        redirect(action: 'list', params: params)
    }

    def update = {
        try {
            if (params.id) {
                Chair chair = Chair.findById(params.id)
                chair.properties = params
                if (chair?.save(flush: true)) {
                    flash.message = message(code: "msg.chair.edit", args: [chair.name, chair.shortName ?: "<не указано>"])
                } else {
                    flash.error = message(code: "msg.edit.error")
                }
            } else {
                flash.error = message(code: "msg.edit.error")
            }
        }
        catch (Exception e) {
            flash.error = message(code: "msg.edit.error")
            log.error(e.getMessage(), e)
        }
        redirect(action: 'list', params: params)
    }

    def edit = {
        if (params.id) {
            Chair chair = Chair.findById(params.id)
            User user = User.get(springSecurityService.principal.id)
            if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
                [curChair: chair]
            } else {
                if (user.facultyId != chair.facultyId) {
                    flash.error = message(code: "msg.FacultyId.error")
                    redirect(action: 'list', params: params)
                }
                if (!chair) {
                    flash.error = message(code: "msg.chair.edit.error")
                    redirect(action: 'list', params: params)
                }
                [curChair: chair]
            }
        } else {
            flash.error = message(code: "msg.chair.edit.error")
            redirect(action: 'list', params: params)
        }
    }

    def search = {
        sessionParamsService.saveParams(params)

        render template: "/template/chair/chairList", model: [res: []]
    }

    def getSearchChairConfig() {
        return [action: 'search', controller: 'chair']
    }

}
