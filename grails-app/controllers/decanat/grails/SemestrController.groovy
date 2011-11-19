package decanat.grails

class SemestrController {

    def index = {
        def plan = Plan.findById(params.id)
        def semestr = Semestr.listOrderById()
        [plan: plan, semestr: semestr]
    }

    def update = {
        try {
            def plan = Plan.findById(params.planId)
            for (int i = 1; i <= plan.semestrCount; i++) {
                def semestr = Semestr.findByPlanAndNumber(plan, i)
                if (!semestr) {
                    semestr = new Semestr()
                }
                semestr.number = i;
                semestr.weekCount = params."sem${i}" as Integer
                semestr.plan = plan
                if (semestr.save(flush: true)) {
                    flash.message = message(code: "msg.semestr.save")
                } else {
                    flash.error = message(code: "msg.edit.error")
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: "index", controller: "index")
    }
}
