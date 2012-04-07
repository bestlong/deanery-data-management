package decanat.grails

import stu.cn.ua.CommonUtils

class SubjectService {

    static transactional = true

    def sessionParamsService

    def findSubjects(params, propertiesToRender){
        int maxCount = params.iDisplayLength as Integer  ?: 25
        int offsetPos = params.iDisplayStart as Integer  ?: 0
        def sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        def orderField = propertiesToRender[params.iSortCol_0 as Integer ?: 0]
        def criteria = Subject.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName
        def name = params?.name
        long chairId = 0
        try {
            chairId = params?.chair as long
        } catch (Exception e){}

        def list = criteria.list(max: maxCount, offset:offsetPos) {
            if (!orderField.equals("") && !sort.equals("")){
                order(orderField, sort)
            }
            if (params){
                if (chairId != 0) {
                    chair {
                        if (chairId == -1) {
                            eq("id", null)
                        }
                        else {
                            eq("id", chairId as long)
                        }
                    }
                }
                if (name && !name.equals("")){
                    ilike("shortName", "%" + name + "%");
                }
                if (shortName && !shortName.equals("")){
                    ilike("name", "%" + shortName + "%");
                }
            }
        }
        list
    }

    def findSubjectsCount (params, propertiesToRender){
        def sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        def orderField = propertiesToRender[params.iSortCol_0 as int]
        def criteria = Subject.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName
        def name = params?.name
        long chairId = 0
        try {
            chairId = params?.chair as long
        } catch (Exception e){}

        def count = criteria.list{
            if (!orderField.equals("") && !sort.equals("")){
                order(orderField, sort)
            }
            if (params){
                println params?.chair && params?.chair != 0
                println params?.chair
                if (chairId != 0) {
                    chair {
                        if (chairId == -1) {
                            eq("id", null)
                        }
                        else {
                            eq("id", chairId as long)
                        }
                    }
                }
                if (name && !name.equals("")){
                    ilike("shortName", "%" + name + "%");
                }
                if (shortName && !shortName.equals("")){
                    ilike("name", "%" + shortName + "%");
                }
                projections {
                    countDistinct("id")
                }
            }
        }
        count
    }

    def updateSubject(Subject subject, def params) {
        subject.properties = params
        if (null != subject.chair) {
            subject.chair?.referenceCount--
        }
        subject.chair = Chair.findById(params.subject.chair);
        if (null != subject.chair) {
            subject.chair?.referenceCount++
        }
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
