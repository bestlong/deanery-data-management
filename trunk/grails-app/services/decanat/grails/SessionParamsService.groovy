package decanat.grails

class SessionParamsService {

    static scope = "session"

    private def copyParams

    def saveParams(params) {
        copyParams = [:]
        params.each {
            copyParams."${it.key}" = it.value
        }
        copyParams
    }

    def loadParams(){
        copyParams
    }
}
