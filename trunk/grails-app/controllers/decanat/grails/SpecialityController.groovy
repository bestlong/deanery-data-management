package decanat.grails

import stu.cn.ua.CommonUtils
import decanat.grails.domain.User
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.converters.JSON

class SpecialityController {

    def specialityService
    def springSecurityService
    def sessionParamsService
    def authorityService

    def getPropertiesToRender() {
        ['id', 'code', 'specialityCode', 'name', 'faculty', 'shortName']
    }

    def index = {
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
        params.clear()
        sessionParamsService.saveParams(params)
        [res: Speciality.list(), selectedMenu: 1, faculty: faculty]
    }

    def add = {
    }

    def save = {
        try {
            User user = User.get(springSecurityService.principal.id)
            def speciality = new Speciality(params);
            speciality.name = CommonUtils.prepareString(speciality.name)
            speciality.faculty = user.faculty
            if (speciality.save()) {
                flash.message = message(code: "msg.speciality.edit", args: [speciality.name])
            }
            else {
                flash.error = message(code: "msg.speciality.add.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.speciality.add.error")
        }
        redirect(action: index, params: params)
    }

    def update = {
        try {
            def ids = params.id
            if (params.id) {
                Speciality speciality = Speciality.findById(params.id)
                speciality.properties = params
                speciality.name = CommonUtils.prepareString(speciality.name)
                if (speciality?.save()) {
                    flash.message = message(code: "msg.speciality.edit", args: [speciality.name])
                } else {
                    flash.error = message(code: "msg.edit.error")
                }
            } else {
                flash.error = message(code: "msg.edit.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: index, params: params)
    }

    def search = {
        sessionParamsService.saveParams(params)
        render(template: "/template/speciality/specialityList", model: [res: []]);
    }

    def table = {
        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []

        def list = specialityService.findSpeciality(params, getPropertiesToRender())
        dataToRender.iTotalRecords = list.totalCount
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords
        list.each { speciality ->
            def record = []
            record << speciality.id
            record << speciality.code
            record << speciality.specialityCode
            record << speciality.name
            record << speciality.shortName
            if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
                record << speciality.faculty?.shortName
            }
            record << speciality.referenceCount
            dataToRender.aaData << record
        }
        render dataToRender as JSON
    }

    def edit = {
        Speciality speciality = Speciality.findById(params.id);
        User user = User.get(springSecurityService.principal.id)
        if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
            [speciality: speciality]
        } else {
            if (user.facultyId == speciality.facultyId) {
                [speciality: speciality]
            } else {
                flash.error = message(code: "msg.FacultyId.error")
                redirect(action: index)
            }
        }
    }

    def delete = {
        try {
            if (params.id) {
                int id = params.id as int
                Speciality spec = Speciality.findById(params.id);
                User user = User.get(springSecurityService.principal.id)

                if (!SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR") && user.facultyId != spec.facultyId) {
                    flash.error = message(code: "msg.FacultyId.error")
                } else {
                    if (spec) {
                        flash.message = message(code: "msg.speciality.remove", args: [spec.name])
                        spec.delete();
                    } else {
                        flash.error = message(code: "msg.remove.error")
                    }
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.remove.error")
        }
        redirect(action: index, params: params)
    }

    def multipleDelete = {
        int deletedCount = 0
        try {
            def specialities = params.id
            specialities.each {
                def deleteItem = params."multipleDelete${it}"
                if (deleteItem) {
                    def item = Speciality.get(it)
                    item.delete(flush: true)
                    deletedCount++
                }
            }
            flash.message = message(code: "message.multiple.delete.success", args: [deletedCount])
        } catch (Exception e) {
            flash.error = message(code: "error.multiple.delete.records")
        }
        redirect(action: 'index')
    }
}
