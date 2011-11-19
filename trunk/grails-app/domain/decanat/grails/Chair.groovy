package decanat.grails

class Chair {

    String name;
    String shortName;
    String head

    static constraints = {
        name (blank: false, unique: true)
        shortName(unique: true, nullable: true)
        head(blank: false)
    }
}
