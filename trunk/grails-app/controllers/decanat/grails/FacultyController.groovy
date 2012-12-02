package decanat.grails

class FacultyController {

    def facultyService
    def authorityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    def removeFilterToFaculty(){
        authorityService.getCurrentUser().setFaculty(null)
        params.clear()
        redirect(action: 'list', params: params)
    }

    def setFilterToFaculty(){
        def dec=Faculty.findById(params.id)
        authorityService.getCurrentUser().setFaculty(dec)
        params.clear()
        redirect(action: 'list', params: params)
    }

    def list() {
        def idFaculty = 0
        if (authorityService.isProrektor()){
            idFaculty=authorityService.getCurrentUser().getFacultyId()
           }
        def faculty
        if (idFaculty!=null){
            faculty= Faculty.findById(idFaculty);
        }
        [facultyList: Faculty.list(params), faculty : faculty, idFaculty: idFaculty, selectedMenu: 5, searchConfig: getSearchFacultyConfig()]
    }

    def create = {
        if (chainModel != null) {
            [facultyInstance: chainModel['facultyInstance']]
        }
    }

    def save = {
        try {
            def facultyInstance = new Faculty(params)
            if (facultyInstance.save(flush: true)) {
                flash.message = message(code: "msg.faculty.add", args: [facultyInstance.name, facultyInstance.shortName ?: "<не указано>"])
                redirect(action: "list", params: params)
            } else {
                chain(action: 'create', model: [facultyInstance: facultyInstance], params: params)
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }

    def update = {
        try {
            if (params.id) {
                Faculty faculty = Faculty.findById(params.id)
                faculty.properties = params
                if (faculty?.save(flush: true)) {
                    flash.message = message(code: "msg.faculty.edit", args: [faculty.name, faculty.shortName ?: "<не указано>"])
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
            Faculty faculty = Faculty.findById(params.id)
            if (!faculty) {
                flash.error = message(code: "msg.edit.error")
                redirect(action: 'list', params: params)
            }
            [curFaculty: faculty]
        } else {
            flash.error = message(code: "msg.edit.error")
            redirect(action: 'list', params: params)
        }
    }

    def search = {
        def res = facultyService.findFaculties(params.name, params.shortName, params.prorektor, params.dean)
        render template: "/template/faculty/facultyList", model: [facultyCollection: res]
    }

    def getSearchFacultyConfig(){
        return [action: 'search', controller: 'faculty']
    }
}
