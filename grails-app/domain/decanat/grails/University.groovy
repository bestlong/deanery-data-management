package decanat.grails

class University {

    String prorektor;
    String dean;

    static constraints = {
        prorektor(blank: false)
        dean(blank: false)
    }
}
