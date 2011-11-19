package decanat.grails

class UniversityController {

    def index = {
        University univer;
        if (!University.list().size()) {
            univer = new University();
        }
        else {
            univer = University.findAll().get(0);
        }
        [university: univer, selectedMenu: 4]
    }


    def update = {
        try {
            University university;
            if (!University.list().size()) {
                university = new University();
            }
            else
                university = University.findAll().get(0);
            university.properties = params
            if (university?.save()) {
                flash.message = message(code: "msg.university.edit")
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
