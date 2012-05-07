package decanat.grails

class Deanery {
    String name;
    String shortName;
    String prorektor;
    String dean;


    static constraints = {
        name(blank: false, unique: true)
        shortName(unique: true, nullable: true)
        prorektor(blank: false, nullable: true)
        dean(blank: false, nullable: true)
    }
}
