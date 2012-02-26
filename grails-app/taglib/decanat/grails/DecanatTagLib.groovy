package decanat.grails

class DecanatTagLib {
    def authenticateService

    def userName = {
        out << (authenticateService.userDomain() ? authenticateService.userDomain().username : 'Guest')
    }
}
