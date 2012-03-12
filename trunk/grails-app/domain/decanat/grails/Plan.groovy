package decanat.grails

import stu.cn.ua.enums.ControlTypeEnum
import stu.cn.ua.enums.WorkTypeEnum
import stu.cn.ua.enums.PlanClass
import stu.cn.ua.dbf.dto.ValidationResult
import stu.cn.ua.dbf.dto.SpecialityPlanDTO
import stu.cn.ua.dbf.dto.ErrorInfo

class Plan {

    Speciality speciality;
    Chair chair;
    String direction;
    String form;
    String level;
    String termin
    String qualification;
    Integer semestrCount;
    Date lastUpdated;
    Integer startYear
    Integer endYear

    def beforeInsert = {
        lastUpdated = new Date()
    }
    def beforeUpdate = {
        lastUpdated = new Date()
    }

    static hasMany = [practiseList: PlanPractice, subjects: PlanSubject, semesterList: Semestr, workPlans: WorkPlan]
    static hasOne = [stateExam: PlanStateExam]

    static constraints = {
        speciality(nullable: true)
        chair(nullable: true)
        direction(nullable: true)
        form(nullable: true)
        level(nullable: true)
        practiseList(nullable: true)
        subjects(nullable: true)
        stateExam(nullable: true)
        semesterList(nullable: true)
        semestrCount(range: 1..20, nullable: true)
        lastUpdated(nullable: true)
        termin(nullable: true)
        qualification(nullable: true)
        startYear(nullable: true)
        endYear(nullable: true)
    }

    static mapping = {
        discriminator value: PlanClass.STUDY
    }

    public static Plan savePlan(SpecialityPlanDTO specialityPlanDTO, Speciality spec){
        Chair chair = Chair.findByCodeChair(specialityPlanDTO.specKaf)
        Plan plan = new Plan(direction: specialityPlanDTO.napram, form: specialityPlanDTO.forma, speciality: spec, chair: chair,
                level: specialityPlanDTO.qualRiv, termin: specialityPlanDTO.srok, qualification: specialityPlanDTO.qualif, semestrCount: 8)
        plan.save()
    }

    public static ValidationResult validate(SpecialityPlanDTO specialityPlanDTO){
        List<ErrorInfo> validationErrors = new ArrayList<ErrorInfo>()
        Chair chair = Chair.findByCodeChair(specialityPlanDTO.specKaf)
        if (null == chair){
            validationErrors.add(new ErrorInfo(specialityPlanDTO.toString(), "SPEC_KAF", specialityPlanDTO.specKaf))
        }
        if (validationErrors.size() != 0){
            return new ValidationResult(false, validationErrors)
        }
        return new ValidationResult(true)
    }

    /**
     *
     * @return количество всех кредитов по всем предметам
     */
    def getCreditCountTotal(){
        Double sum = 0
        subjects.each {
            sum += it.creditCount
        }
        sum
    }

    /**
     * @return сумарное количество часов по всем предметам
     */
    def getTotal(){
        Double sum = 0
        subjects.each {
            sum += it.getTotal()
        }
        sum
    }

    /**
     * @return сумарное количество часов по всем предметам, по лекциям
     */
    def getTotalLecturesCount(){
        Double sum = 0
        subjects.each {
            sum += it.lectureCount
        }
        sum
    }

/**
 * @return сумарное количество часов по всем предметам, по семинарам
 */
    def getTotalSeminarCount(){
        Double sum = 0
        subjects.each {
            sum += it.seminarCount
        }
        sum
    }

    /**
     * @return сумарное количество часов по всем предметам, по практикам
     */
    def getTotalPractiseCount(){
        Double sum = 0
        subjects.each {
            sum += it.practiceCount
        }
        sum
    }

    /**
     * @return сумарное количество часов по всем предметам, по лабораторным работам
     */
    def getTotalLabCount(){
        Double sum = 0
        subjects.each {
            sum += it.labCount
        }
        sum
    }

/**
 * @return сумарное количество часов по всем предметам, по самостоятельной работе
 */
    def getTotalSamCount(){
        Double sum = 0
        subjects.each {
            sum += it.samCount
        }
        sum
    }

/**
 * @return сумарное количество часов по всем предметам, по определённому семестру
 */
    def getTotalSemestr(int semestr){
        Double sum = 0
        subjects.each {
            sum += it.getHourCount(semestr)
        }
        sum
    }

    /**
     * @return сумарное количество часов по предмету ${WorkTypeEnum}, по  определённому семестру
     */
    def getTotalSemestr(int semestr, WorkTypeEnum workTypeEnum){
        Double sum = 0
        subjects.each {
            sum += it.getHourCount(semestr, workTypeEnum)
        }
        sum
    }

    /**
     * @return количество типа контроля по всем предметам
     */
    def getTotal(ControlTypeEnum controlTypeEnum){
        def sum = 0
        subjects.each {
            sum +=it.getControlTypeCount(controlTypeEnum)
        }
        sum
    }

    /**
     * @return количество типа контроля по всем предметам
     */
    public int getControlTypeCount(ControlTypeEnum cType, int semestr) {
        def sum = 0
        subjects.each {
            sum += it.getControlTypeCount(cType, semestr)
        }
        sum
    }
}
