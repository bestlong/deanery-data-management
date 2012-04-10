package decanat.grails

class SessionParamsService {

    static scope = "session"

    private def params

    def saveParams(params) {
        this.params = params.clone()
    }

    def loadParams(){
        params
    }
}
