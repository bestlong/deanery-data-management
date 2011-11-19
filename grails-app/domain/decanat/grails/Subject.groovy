package decanat.grails

class Subject {

    String name;
    Chair chair;
    String shortName;

    static constraints = {
        name(blank: false)
        chair(nullable: false)
        shortName(nullable: true)
    }
}
