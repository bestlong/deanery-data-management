package decanat.grails

import stu.cn.ua.CommonUtils

import grails.converters.JSON

class SubjectController {

    def subjectService
    def index = {
        [res: Subject.list(sort: 'name', order: "asc"), selectedMenu: 2]
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


    def specialitiesSubjects = {
        def chair = Chair.findById(params.id as int)
        def res = Subject.findAllByChair(chair)
        render(view: "index", model: [res: res])
    }

    def save = {
        try {
            def subject = new Subject(params);
            subject.chair = Chair.findById(params.subject.chair);
            subject.name = CommonUtils.prepareString(subject.name)
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
        def propertiesToRender = ['id', 'code', 'chair?.name', 'name', 'shortName', 'id']


        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData=[]
        dataToRender.iTotalRecords = Subject.count()
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords

        def sortProperty = propertiesToRender[params.iSortCol_0 as int]
        def sortDir = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'

        Subject.list(max: params.iDisplayLength as int, offset: params.iDisplayStart as int, sort: sortProperty, order: sortDir).each { subject ->
            def record = []
            record << subject.id
            record << subject.code
            record << subject.chair?.name
            record << subject.name
            record << subject.shortName
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
                    item.delete(flush:true)
                    deletedCount++
                }
            }
            flash.message= message(code: "message.multiple.delete.success", args: [deletedCount])
        } catch (Exception e) {
            flash.error= message(code: "error.multiple.delete.records")
        }
        redirect(action: 'index')
    }

    def search = {
        def res = subjectService.findSubjects(params.chair as int, params.name, params.shortName);
        render(template: "/template/subject/subjectList", model: [res: res]);
    }

    def edit = {
        Subject subject = Subject.findById(params.id);
        [subject: subject]
    }

    def delete = {
        try {
            if (params.id) {
                Subject subj = Subject.findById(params.id);
                if (subj) {
                    subj.delete(flush: true);
                    flash.message = message(code: "msg.subject.remove", args: [subj.name])
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
        redirect(action: 'index', controller: 'subject', params: params)
    }
}

