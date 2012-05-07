package decanat.grails

import org.springframework.dao.DataIntegrityViolationException

class DeaneryController {

    def deaneryService
    def authorityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    def removeFilterToDeanery(){
        authorityService.getCurrentUser().setDeanery(null)
        params.clear()
        redirect(action: 'list', params: params)
    }

    def setFilterToDeanery(){
        def dec=Deanery.findById(params.id)
        authorityService.getCurrentUser().setDeanery(dec)
        params.clear()
        redirect(action: 'list', params: params)
    }

    def list() {
        def idDeanery = 0
        if (authorityService.isProrektor()){
            idDeanery=authorityService.getCurrentUser().getDeaneryId()
           }
        def deanery
        if (idDeanery!=null){
            deanery= Deanery.findById(idDeanery);
        }
        [deaneryList: Deanery.list(params), deanery : deanery, idDeanery: idDeanery, selectedMenu: 5, searchConfig: getSearchDeaneryConfig()]
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
                //TODO ты используешь несуществующие месседжы, добавь нужные строки в messages.properties, ну и наверно месседжы не должны быть связаны с chair
                flash.error = message(code: "msg.edit.error")
                redirect(action: 'list', params: params)
            }
            [curDeanery: deanery]
        } else {
            flash.error = message(code: "msg.edit.error")
            redirect(action: 'list', params: params)
        }
    }

    def search = {
        def res = deaneryService.findDeaneries(params.name, params.shortName, params.prorektor, params.dean)
        render template: "/template/deanery/deaneryList", model: [deaneryCollection: res]
    }

    def getSearchDeaneryConfig(){
        return [action: 'search', controller: 'deanery']
    }
}
