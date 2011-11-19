package decanat.grails

class Semestr implements Comparable{
    Plan plan;
    int number;
    int weekCount;

    static belongsTo = [plan: Plan]
    def beforeInsert = {
        plan.lastUpdated = new Date()
    }
    def beforeUpdate = {
        plan.lastUpdated = new Date()

    }
       def beforeDelete = {
        plan.lastUpdated = new Date()

    }
    static constraints = {
        number(min: 0, blank: false)
        weekCount(min: 0, blank: false)
        plan(nullable: false)
    }

   int compareTo(obj) {
       number.compareTo(obj.number)
   }
}
