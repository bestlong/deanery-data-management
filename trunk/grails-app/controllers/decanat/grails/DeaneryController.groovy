package decanat.grails

import org.springframework.dao.DataIntegrityViolationException

class DeaneryController {

    def deaneryService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [deaneryList: Deanery.list(params), selectedMenu: 5, searchConfig: getSearchDeaneryConfig()]
    }

    def create = {
        if (chainModel != null) {
            [deaneryInstance: chainModel['deaneryInstance']]
        }
    }

    def save = {
        try {
            def deaneryInstance = new Deanery(params)
            if (deaneryInstance.save(flush: true)) {
                flash.message = message(code: "msg.deanery.add", args: [deaneryInstance.name, deaneryInstance.shortName ?: "<не указано>"])
                redirect(action: "list", params: params)
            } else {
                chain(action: 'create', model: [deaneryInstance: deaneryInstance], params: params)
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }

    def update = {
        try {
            if (params.id) {
                Deanery deanery = Deanery.findById(params.id)
                deanery.properties = params
                if (deanery?.save(flush: true)) {
                    flash.message = message(code: "msg.deanery.edit", args: [deanery.name, deanery.shortName ?: "<не указано>"])
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
            Deanery deanery = Deanery.findById(params.id)
            if (!deanery) {
                flash.error = message(code: "msg.chair.edit.gotoError")
                redirect(action: 'list', params: params)
            }
            [curDeanery: deanery]
        } else {
            flash.error = message(code: "msg.chair.edit.gotoError")
            redirect(action: 'list', params: params)
        }
    }

    def search = {
        def res = deaneryService.findDeaneries(params.name, params.shortName)
        render template: "/template/deanery/deaneryList", model: [deaneryCollection: res]
    }

    def getSearchDeaneryConfig(){
        return [action: 'search', controller: 'deanery']
    }
}
