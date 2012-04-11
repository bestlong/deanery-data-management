package decanat.grails


import decanat.grails.domain.User
import grails.converters.JSON
import decanat.grails.ChairService

class ChairController {
    def sessionParamsService
    def springSecurityService
    def chairService

    def getPropertiesToRender(){
        def propertiesToRender

        propertiesToRender  =["id" ,"codeChair", "name",  "shortName" , "head", "deanery.name", "id"]

        propertiesToRender
    }

    def table = {
        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData=[]

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
            record << subject.deanery.name
            record << ' <a href="../../plan/subject/specialitiesSubjects/'+subject.id.toString()+'" >Показать</a>'
            record <<  ' <a href="../../plan/index/chairPlans/'+subject.id.toString()+'">Показать</a> '

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
        [chairList: chairService.findChairsForCurrentUser(), selectedMenu: 3, searchConfig: getSearchChairConfig()]
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
            chairInstance.deanery = user.deanery
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
                    item.delete(flush:true)
                    deletedCount++
                }
            }
            flash.message= message(code: "message.multiple.delete.success", args: [deletedCount])
        } catch (Exception e) {
            flash.error= message(code: "error.multiple.delete.records")
        }
        redirect(action: 'list')
    }

    def remove = {
        try {
            if (params.id) {
                int id = params.id as int
                Chair chair = Chair.findById(id)
                if (chair) {
                    chair.delete(flush: true)
                    flash.message = message(code: "msg.chair.remove", args: [chair.name])
                }
                else {
                    flash.error = message(code: "msg.remove.error")
                }
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
            if (!chair) {
                flash.error = message(code: "msg.chair.edit.gotoError")
                redirect(action: 'list', params: params)
            }
            [curChair: chair]
        } else {
            flash.error = message(code: "msg.chair.edit.gotoError")
            redirect(action: 'list', params: params)
        }
    }

    def search = {
        sessionParamsService.saveParams(params)
        def res = chairService.findChairs(params, getPropertiesToRender())
        render template: "/template/chair/chairList", model: [res: res]
    }

    def getSearchChairConfig(){
        return [action: 'search', controller: 'chair']
    }
}
