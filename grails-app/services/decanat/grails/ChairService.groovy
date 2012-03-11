package decanat.grails

class ChairService {

    static transactional = true

    List<Chair> findChairs(String code, String name, String shortName) {
        def chairs
        chairs = Chair.findAll("from Chair c where c.codeChair like :code and c.name like :name and c.shortName like :shortName", [code: "%${code}%", name: "%${name}%", shortName: "%${shortName}%"])
        return chairs
    }

}
