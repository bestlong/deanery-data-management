package decanat.grails

import decanat.grails.domain.User

class DecanatTagLib {
    def springSecurityService

    def userName = {
        out << (springSecurityService.getPrincipal() ? springSecurityService.getPrincipal().username : 'Guest')
    }

    def deanFacultyFullName = {
        User user = User.findByUsername(springSecurityService.getPrincipal().username)
        if (user && user.deanery) {
            out << user.deanery.name
        }
        else
            out << ('')
    }

    def deanFacultyShortName = {
        User user = User.findByUsername(springSecurityService.getPrincipal().username)
        if (user && user.deanery) {
            out << user.deanery.shortName
        }
        else
            out << ('')
    }

    def deanFacultyName = {
        User user = User.findByUsername(springSecurityService.getPrincipal().username)
        if (user && user.deanery ) {
            out << (user.deanery.shortName ? user.deanery.shortName : user.deanery.name)
        }
        else
            out << ('')
    }

    def hourBySubjAndSem(def subjId, def semester) {
        def subj = PlanSubject.get(subjId as int)
        return PlanHours.findByPlanSubjectAndSemestr(subj, semester as int) ? 1 : 0
    }
}
