package decanat.grails

class DeaneryService {
    static transactional = true

    List<Deanery> findDeaneries(String name, String shortName,String prorektor,String dean) {
        def deaneries
        deaneries = Deanery.findAll("from Deanery c where c.name like :name and c.shortName like :shortName and c.prorektor like :prorektor and c.dean like :dean", [name: "%${name}%", shortName: "%${shortName}%", prorektor: "%${prorektor}",dean: "%${dean}"])
        return deaneries
    }
}
