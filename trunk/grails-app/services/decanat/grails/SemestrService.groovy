package decanat.grails

class SemestrService {

    static transactional = true

    def cleanSemestrs(int semestr) {
        Semestr.executeUpdate("delete Semestr s where s.number > :number", [number: semestr])
    }
}
