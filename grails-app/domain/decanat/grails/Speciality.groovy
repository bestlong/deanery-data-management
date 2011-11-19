package decanat.grails

class Speciality {

    String kod;
    String name;
    String shortName;

    static constraints = {
        kod(blank: false, unique: true)
        name(blank: false, unique: true)
        shortName(unique: true, nullable: true)
    }
}
