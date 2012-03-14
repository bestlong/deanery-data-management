package decanat.grails

import stu.cn.ua.CommonUtils

class SpecialityController {

    def specialityService

    def index = {
        [res: Speciality.list(), selectedMenu: 1]
    }

    def add = {
    }

    def save = {
        try {
            def speciality = new Speciality(params);
            speciality.name = CommonUtils.prepareString(speciality.name)
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
        def res = specialityService.findSpecialities(params.code, params.specialityCode, params.name, params.shortName);
        render(template: "/template/speciality/specialityList", model: [res: res]);
    }

    def edit = {
        Speciality speciality = Speciality.findById(params.id);
        [speciality: speciality]
    }

    def delete = {
        try {
            if (params.id) {
                int id = params.id as int
                Speciality spec = Speciality.findById(params.id);
                if (spec) {
                    flash.message = message(code: "msg.speciality.remove", args: [spec.name])
                    spec.delete();
                }
                else {
                    flash.error = message(code: "msg.remove.error")
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.remove.error")
        }
        redirect(action: index, params: params)
    }

}
