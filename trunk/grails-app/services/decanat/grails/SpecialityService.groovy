package decanat.grails

class SpecialityService {

    def sessionParamsService
    def springSecurityService
    def authorityService

    static transactional = true
    static scope = "session"

//    List<Speciality> findSpecialities(String code, String specialityCode, String name, String shortName) {
//        def c = Speciality.createCriteria()
//        def specialities = c.listDistinct {
//            ilike("code", "%" + code + "%")
//            ilike("name", "%" + name + "%")
//            ilike("specialityCode", "%" + specialityCode + "%");
//            if (!shortName.equals(""))
//                ilike("shortName", "%" + shortName + "%");
//        }
//        return specialities;
//    }

    def findSpeciality(params, propertiesToRender) {
        Integer maxCount = 25
        Integer offsetPos = 0
        String sort = "desc"
        String orderField = "id"
        Integer sortCol = 0
        if (null != params.iDisplayLength) {
            maxCount = params.iDisplayLength as Integer
        }
        if (null != params.iDisplayStart) {
            offsetPos = params.iDisplayStart as Integer
        }
        if (null != params.sSortDir_0) {
            sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        }
        if (null != params.iSortCol_0) {
            sortCol = params.iSortCol_0 as Integer
        }
        orderField = propertiesToRender[sortCol]

        def criteria = Speciality.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName ?: ""
        def name = params?.name ?: ""
        def code = params?.code ?: ""
        def specialityCode = params?.specialityCode

        Faculty dean = authorityService.getCurrentFaculty(params)
       if (authorityService.isProrektor()){
            def did=authorityService.getCurrentUser().getFacultyId()
            if (did!=null)
                dean=Faculty.findById(did)
        }
        def list = criteria.list(max: maxCount, offset: offsetPos) {
            order(orderField, sort)
            if (params) {
                if (null != shortName && !shortName.equals("")) {
                    ilike("shortName", "%$shortName%");
                }
                if (null != name && !name.equals("")) {
                    ilike("name", "%$name%");
                }
                if (null != code && !code.equals("")) {
                    ilike("code", "%$code%");
                }
                if (null != specialityCode && !specialityCode.equals("")) {
                    ilike("specialityCode", "%$specialityCode%");
                }
            }
            if (null != dean) {
                eq("faculty", dean)
            }
        }
        list
    }
}
