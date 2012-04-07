package decanat.grails

class SessionParamsService {

    static scope = "session"

    private def params

    def saveParams(params) {
        this.params = params
    }

    def loadParams(){
        params
    }
}
