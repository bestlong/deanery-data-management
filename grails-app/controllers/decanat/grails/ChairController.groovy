package decanat.grails

import java.util.concurrent.ExecutionException

class ChairController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def chairService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        [chairList: Chair.list(params), selectedMenu: 3, searchConfig: getSearchChairConfig()]
    }

    def create = {
        if (chainModel != null) {
            [chairInstance: chainModel['chairInstance']]
        }
    }

    def save = {
        try {
            def chairInstance = new Chair(params)
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
