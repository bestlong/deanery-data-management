package decanat.grails

import stu.cn.ua.CommonUtils

class SubjectController {

    def subjectService
    def index = {
        [res: Subject.list(), selectedMenu: 2]
    }

    def add = {}

    def update = {
        try {
            if (params.id) {
                Subject subject = Subject.findById(params.id)
                subject.properties = params
                subject.chair = Chair.findById(params.subject.chair);
                subject.name = CommonUtils.prepareString(subject.name)
                if (subject?.save()) {
                    flash.message = message(code:"msg.subject.edit", args:[subject.name])
                } else {
                    flash.error = message(code:"msg.edit.error")
                }
            } else {
                flash.error = message(code:"msg.edit.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code:"msg.edit.error")
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
            if (subject.save()) {
                flash.message = message(code:"msg.subject.add", args:[subject.name])
            }
        }
        catch (Exception e) {
            flash.error = message(code:"msg.subject.add.error")
            log.error(e.getMessage(), e)

        }
        redirect(action: index, params: params)
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
                    subj.delete();
                    flash.message = message(code:"msg.subject.remove", args:[subj.name])
                }
            }
        }
        catch (Exception e) {
            flash.error = message(code:"msg.remove.error")
            log.error(e.getMessage(), e)
        }
        redirect(action: index, params: params)
    }
}
