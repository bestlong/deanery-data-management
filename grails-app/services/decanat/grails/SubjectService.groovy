package decanat.grails

import stu.cn.ua.CommonUtils

class SubjectService {

    static transactional = true

    def updateSubject(Subject subject, def params) {
        subject.properties = params
        subject.chair?.referenceCount--
        subject.chair = Chair.findById(params.subject.chair);
        subject.chair?.referenceCount++
        subject.name = CommonUtils.prepareString(subject.name)
        return subject.save()
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
