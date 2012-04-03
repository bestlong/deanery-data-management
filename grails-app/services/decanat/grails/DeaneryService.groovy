package decanat.grails

class DeaneryService {
    static transactional = true

    List<Deanery> findDeaneries(String name, String shortName) {
        def deaneries
        deaneries = Deanery.findAll("from Deanery c where c.name like :name and c.shortName like :shortName", [name: "%${name}%", shortName: "%${shortName}%"])
        return deaneries
    }
}
