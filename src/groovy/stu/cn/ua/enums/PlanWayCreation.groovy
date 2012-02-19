package stu.cn.ua.enums

/**
 * author: evgeniy
 * date: 18.02.12
 */
public enum PlanWayCreation {
    FROM_WORK_PLAN("На основе рабочего плана"),
    FROM_STUDY_PLAN("На основе учебного плана"),
    STANDARD_CONSTRUCTOR("Из стандартного конструктора")
    
    String caption

    PlanWayCreation(String caption) {
        this.caption = caption
    }
}