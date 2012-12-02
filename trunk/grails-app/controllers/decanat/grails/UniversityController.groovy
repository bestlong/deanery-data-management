package decanat.grails

class UniversityController {

    def planService

    def index = {
        Faculty faculty = planService.getFaculty()
        [faculty: faculty, selectedMenu: 4]
    }


    def update = {
        try {
            Faculty faculty = planService.getFaculty()
            faculty.properties = params
            if (faculty?.save()) {
                flash.message = message(code: "msg.faculty.edit", args: [faculty.name, faculty.shortName])
            } else {
                flash.error = message(code: "msg.edit.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: index, controller: "index", params: params)

    }
}
