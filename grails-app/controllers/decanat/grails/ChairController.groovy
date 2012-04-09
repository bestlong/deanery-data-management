package decanat.grails

import java.util.concurrent.ExecutionException
import decanat.grails.domain.User

class ChairController {

    def springSecurityService
    def chairService

    def index = {
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
        def res = chairService.findChairs(params.code, params.name, params.shortName)
        render template: "/template/chair/chairList", model: [chairCollection: res]
    }

    def getSearchChairConfig(){
        return [action: 'search', controller: 'chair']
    }
}
