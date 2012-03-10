package decanat.grails

class SubjectService {

    static transactional = true

    def serviceMethod() {

    }

    List<Subject> findSubjects(Integer chairId, String name, String shortName) {
        def c = Subject.createCriteria()
        def subjects = c.listDistinct {
            ilike("name", "%" + name + "%");
            order("name", "asc")
            if (!shortName.equals(""))
                ilike("shortName", "%" + shortName + "%");
            if (chairId != 0) {
                chair {
                    if (chairId == -1) {
                        eq("id", null)
                    }
                    else {
                        eq("id", chairId.longValue())
                    }
                }
            }
        }
        return subjects;
    }
}
