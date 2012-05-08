package decanat.grails

class Faculty {
    String name;
    String shortName;
    String prorektor;
    String dean;


    static constraints = {
        name(blank: false, unique: true)
        shortName(unique: true, nullable: true)
        prorektor(blank: false)
        dean(blank: false)
    }
}
