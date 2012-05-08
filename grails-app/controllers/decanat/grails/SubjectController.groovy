package decanat.grails

import stu.cn.ua.CommonUtils

import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class SubjectController {

    def authorityService
    def subjectService
    def sessionParamsService
    def springSecurityService

    def getPropertiesToRender() {
        def propertiesToRender
        if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
            propertiesToRender = ['id', 'code', 'chair?.name', 'name', 'shortName', 'id']
        } else {
            propertiesToRender = ['id', 'code', 'chair?.name', 'name', 'shortName', 'faculty', 'id']
        }
        propertiesToRender
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
        cleanParams(params)
        sessionParamsService.saveParams(params)
        [selectedMenu: 2,faculty: faculty]
    }

    def add = {}

    def update = {
        try {
            if (params.id) {
                Subject subject = Subject.findById(params.id)
                if (subjectService.updateSubject(subject, params)) {
                    flash.message = message(code: "msg.subject.edit", args: [subject.name])
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

    def save = {
        try {
            User user = User.get(springSecurityService.principal.id)
            def subject = new Subject(params);
            subject.chair = Chair.findById(params.subject.chair);
            subject.name = CommonUtils.prepareString(subject.name)
            subject.faculty = user.faculty
            if (subject.save(flush: true)) {
                flash.message = message(code: "msg.subject.add", args: [subject.name])
            }
        }
        catch (Exception e) {
            flash.error = message(code: "msg.subject.add.error")
            log.error(e.getMessage(), e)
        }
        redirect(action: index, params: params)
    }

    def table = {
        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []

        def list = subjectService.findSubjects(params, getPropertiesToRender())
        dataToRender.iTotalRecords = list.totalCount
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords
        list.each { subject ->
            def record = []
            record << subject.id
            record << subject.code
            record << subject.chair?.name
            record << subject.name
            record << subject.shortName
            if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
                record << subject.faculty?.shortName
            }
            record << subject.referenceCount
            dataToRender.aaData << record
        }
        render dataToRender as JSON
    }

    def multipleDelete = {
        int deletedCount = 0
        try {
            def subjects = params.id
            subjects.each {
                def deleteItem = params."multipleDelete${it}"
                if (deleteItem) {
                    def item = Subject.get(it)
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

    def search = {

        sessionParamsService.saveParams(params)
        render(template: "/template/subject/subjectList", model: [res: []]);
    }

    def edit = {
        Subject subject = Subject.findById(params.id);
        User user = User.get(springSecurityService.principal.id)
        if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
            [subject: subject]
        } else {
            if (user.facultyId == subject.facultyId) {
                [subject: subject]
            } else {
                flash.error = message(code: "msg.FacultyId.error")
                redirect(action: 'index')
            }
        }
    }

    def delete = {
        try {
            if (params.id) {
                Subject subj = Subject.findById(params.id);
                User user = User.get(springSecurityService.principal.id)
                if (!SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR") && user.facultyId != subj.facultyId) {
                    flash.error = message(code: "msg.FacultyId.error")
                } else {
                    if (subj) {
                        subj.delete(flush: true);
                        flash.message = message(code: "msg.subject.remove", args: [subj.name])
                    }
                    else {
                        flash.error = message(code: "msg.remove.error")
                    }
                }
            }
        }
        catch (Exception e) {
            flash.error = message(code: "msg.remove.error")
            log.error(e.getMessage(), e)
        }
        redirect(action: 'index', controller: 'subject', params: params)
    }

    private void cleanParams(params) {
        params.clear()
    }
}

