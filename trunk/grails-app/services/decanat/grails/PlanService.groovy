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
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        c.list{
            maxResults (max)
            firstResult(offset)
            order("lastUpdated", "desc")
            eq("class", planClass.name())
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
    }

    def findByDiscriminatorCount(PlanClass planClass) {
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", planClass.name())
            order("lastUpdated", "desc")
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
        list.size()
    }


    def findByDiscriminatorForPaginating(PlanClass planClass, int max, int offset, def sessionParamsService) {
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        c.list{
            maxResults (max)
            firstResult(offset)
            order("lastUpdated", "desc")
            eq("class", planClass.name())
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
    }


    def findStudyPlansByParams(def params){
        def  deanery = getDeanery()
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
        if (null != deanery) {
            query="from Plan as b where ((b.class='${PlanClass.STUDY.name()}') and (('${semestrCount}'='0') or (b.semestrCount='${semestrCount}')) and (('${form}'='0') or (b.form='${form}')) and (b.speciality.name like '%${spec}%' )   and (b.direction like '%${direction}%' ) and (b.chair.name like '%${chair}%' ) and (b.qualification like '%${qualification}%' )  and (b.termin like '%${termin}%' )  and (b.level like '%${level}%' ) and (('${startYear}'='-1') or (b.startYear='${startYear}'))  and (('${endYear}'='-1') or (b.endYear='${endYear}')) and ((b.speciality.deanery.name='${deanery.name}') and (b.speciality.deanery.shortName='${deanery.shortName}')))   order by b.lastUpdated desc ";
        } else{
            query="from Plan as b where ((b.class='${PlanClass.STUDY.name()}') and (('${semestrCount}'='0') or (b.semestrCount='${semestrCount}')) and (('${form}'='0') or (b.form='${form}')) and (b.speciality.name like '%${spec}%' )   and (b.direction like '%${direction}%' ) and (b.chair.name like '%${chair}%' ) and (b.qualification like '%${qualification}%' )  and (b.termin like '%${termin}%' )  and (b.level like '%${level}%' ) and (('${startYear}'='-1') or (b.startYear='${startYear}'))  and (('${endYear}'='-1') or (b.endYear='${endYear}')))  order by b.lastUpdated desc ";
        }
        def qlist=Plan.findAll(query)
        qlist
    }

    def findWorkPlansByStudyPlan(Plan plan){
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", PlanClass.WORK.name())
            eq("plan", plan)
            order("lastUpdated", "desc")
//            if (null != deanery) {
//                speciality{
//                    eq("deanery", deanery)
//                }
//            }
        }
        list
    }

    def findStudyPlansByChair(Chair chair){
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", PlanClass.STUDY.name())
            eq("chair", chair)
            order("lastUpdated", "desc")
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
        list
    }




    private def getDeanery(){
        User user = User.get(springSecurityService.principal.id)
        Deanery dean = null
        if (!authorityService.isProrektor()) {
            dean = user.deanery
        }
        dean
    }
}
