package decanat.grails

import stu.cn.ua.enums.ControlTypeEnum
import stu.cn.ua.enums.WorkTypeEnum

class Plan {

    SortedSet semestr
    Speciality speciality;
    Chair chair;
    String direction;
    String form;
    String level;
    String termin
    String qualification;
    Integer semestrCount;
    Date lastUpdated;

    def beforeInsert = {
        lastUpdated = new Date()
    }
    def beforeUpdate = {
        lastUpdated = new Date()
    }

    static hasMany = [practise: PlanPractice, subjects: PlanSubject, semestr: Semestr]
    static hasOne = [stateExam: PlanStateExam]

    static constraints = {
        speciality(nullable: false)
        chair(nullable: false)
        direction(blank: false)
        form(blank: false)
        level(blank: false)
        practise(nullable: true)
        subjects(nullable: true)
        stateExam(nullable: true)
        semestr(nullable: true)
        semestrCount(range: 1..20)
        lastUpdated(nullable: true)
        termin(nullable: false)
        qualification(nullable: false)
    }

    static mapping = {
        discriminator value: "study"
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
