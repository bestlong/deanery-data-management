package decanat.grails

class PracticeService {

    static transactional = true

    def serviceMethod() {

    }

    List<PlanPractice> findPractice(Integer weeks, String name, Integer semestr) {

        def c = PlanPractice.createCriteria()

        def practice = c.listDistinct {
            ilike("name", "%" + name + "%");
            weeks {
                if (weeks != 0)
                    eq("id", weeks.longValue())
            }
            semestr {
                if (semestr != 0)
                    eq("id", semestr.longValue())
            }
        }

        return practice;
    }

}
