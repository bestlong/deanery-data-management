package decanat.grails

class Deanery {
    String name;
    String shortName;


    static constraints = {
        name(blank: false, unique: true)
        shortName(unique: true, nullable: true)
    }
}
