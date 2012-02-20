package stu.cn.ua.enums

/**
 * author: evgeniy
 * date: 17.02.12
 */
public enum PlanClass {
    STUDY("Учебный план"), WORK("Рабочий план")

    def caption

    PlanClass(caption) {
        this.caption = caption
    }
}