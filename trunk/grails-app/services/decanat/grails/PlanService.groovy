package decanat.grails

import stu.cn.ua.enums.PlanClass
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User
import stu.cn.ua.enums.PlanForm

class PlanService {

    def springSecurityService
    def authorityService

    static transactional = true

    def findByDiscriminatorForPaginating(PlanClass planClass, int max, int offset) {
        def  faculty = getFaculty()
        def c = Plan.createCriteria()
        c.list{
            maxResults (max)
            firstResult(offset)
            order("lastUpdated", "desc")
            eq("class", planClass.name())
            if (null != faculty) {
                speciality{
                    eq("faculty", faculty)
                }
            }
        }
    }

    def findByDiscriminatorCount(PlanClass planClass) {
        def  faculty = getFaculty()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", planClass.name())
            order("lastUpdated", "desc")
            if (null != faculty) {
                speciality{
                    eq("faculty", faculty)
                }
            }
        }
        list.size()
    }


    def findByDiscriminatorForPaginating(PlanClass planClass, int max, int offset, def sessionParamsService) {
        def  faculty = getFaculty()
        def c = Plan.createCriteria()
        c.list{
            maxResults (max)
            firstResult(offset)
            order("lastUpdated", "desc")
            eq("class", planClass.name())
            if (null != faculty) {
                speciality{
                    eq("faculty", faculty)
                }
            }
        }
    }


    def findStudyPlansByParams(def params){
        def faculty  = authorityService.getCurrentFaculty(params)
        if (authorityService.isProrektor()){
            def did=authorityService.getCurrentUser().getFacultyId()
            if (did!=null)
                faculty=Faculty.findById(did)
        }
        def chair=params.chair;
        def form=params.form;
        def startYear=params.startYear;
        def endYear=params.endYear;
        def spec=params.speciality;
        def direction=params.direction;
        def level=params.level;
        def semestrCount=params.semestrCount;
        def termin=params.termin;
        def qualification=params.qualification;

        if (!endYear.toString().isInteger()){
            endYear=-1;
        }

        if (!startYear.toString().isInteger()){
            startYear=-1;
        }
        String query;
        if (null != faculty) {
            query="from Plan as b where ((b.class='${PlanClass.STUDY.name()}') and (('${semestrCount}'='0') or (b.semestrCount='${semestrCount}')) and (('${form}'='0') or (b.form='${form}')) and (b.speciality.name like '%${spec}%' )   and (b.direction like '%${direction}%' ) and (b.chair.name like '%${chair}%' ) and (b.qualification like '%${qualification}%' )  and (b.termin like '%${termin}%' )  and (b.level like '%${level}%' ) and (('${startYear}'='-1') or (b.startYear='${startYear}'))  and (('${endYear}'='-1') or (b.endYear='${endYear}')) and (b.speciality.faculty.id='${faculty.id}'))   order by b.lastUpdated desc ";
        } else{
            query="from Plan as b where ((b.class='${PlanClass.STUDY.name()}') and (('${semestrCount}'='0') or (b.semestrCount='${semestrCount}')) and (('${form}'='0') or (b.form='${form}')) and (b.speciality.name like '%${spec}%' )   and (b.direction like '%${direction}%' ) and (b.chair.name like '%${chair}%' ) and (b.qualification like '%${qualification}%' )  and (b.termin like '%${termin}%' )  and (b.level like '%${level}%' ) and (('${startYear}'='-1') or (b.startYear='${startYear}'))  and (('${endYear}'='-1') or (b.endYear='${endYear}')))  order by b.lastUpdated desc ";
        }
        def qlist=Plan.findAll(query)
        qlist
    }

    def findWorkPlansByStudyPlan(Plan plan){
        def  faculty = getFaculty()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", PlanClass.WORK.name())
            eq("plan", plan)
            order("lastUpdated", "desc")
//            if (null != faculty) {
//                speciality{
//                    eq("faculty", faculty)
//                }
//            }
        }
        list
    }

    def findStudyPlansByChair(Chair chair){
        def  faculty = getFaculty()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", PlanClass.STUDY.name())
            eq("chair", chair)
            order("lastUpdated", "desc")
            if (null != faculty) {
                speciality{
                    eq("faculty", faculty)
                }
            }
        }
        list
    }




    private def getFaculty(){
        User user = User.get(springSecurityService.principal.id)
        Faculty dean = null
        if (!authorityService.isProrektor()) {
            dean = user.faculty
        }else{
            def did=authorityService.getCurrentUser().getFacultyId()
            if (did!=null)
                dean=Faculty.findById(did)
        }
        dean
    }
}
