package decanat.grails

class ChairService {

    static transactional = true

    List<Chair> findChairs(String name, String shortName) {
        def chairs
        if (!shortName.equals(""))
            chairs = Chair.findAll("from Chair c where c.name like :name and c.shortName like :shortName", [name: "%${name}%", shortName: "%${shortName}%"])
        else
            chairs = Chair.findAll("from Chair c where c.name like :name and c.shortName is null", [name: "%${name}%"])
        return chairs
    }

}
