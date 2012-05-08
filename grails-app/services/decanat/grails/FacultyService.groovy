package decanat.grails

class FacultyService {
    static transactional = true

    List<Faculty> findFaculties(String name, String shortName,String prorektor,String dean) {
        def faculties
        faculties = Faculty.findAll("from Faculty c where c.name like :name and c.shortName like :shortName and c.prorektor like :prorektor and c.dean like :dean", [name: "%${name}%", shortName: "%${shortName}%", prorektor: "%${prorektor}",dean: "%${dean}"])
        return faculties
    }
}
