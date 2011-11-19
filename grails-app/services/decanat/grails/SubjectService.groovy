package decanat.grails

class SubjectService {

    static transactional = true

    def serviceMethod() {

    }

    List<Subject> findSubjects(Integer chairId, String name, String shortName) {

        def c = Subject.createCriteria()

        def subjects = c.listDistinct {
            ilike("name", "%" + name + "%");
            if (!shortName.equals(""))
                ilike("shortName", "%" + shortName + "%");
            chair{
                if (chairId != 0)
                    eq ("id", chairId.longValue())
            }
        }

        return subjects;
    }

}
