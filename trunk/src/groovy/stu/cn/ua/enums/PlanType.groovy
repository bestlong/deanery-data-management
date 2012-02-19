package stu.cn.ua.enums

/**
 * author: evgeniy
 * date: 17.02.12
 */
public enum PlanType {
    STUDY("Учебный план"), WORK("Рабочий план")

    def caption

    PlanType(caption) {
        this.caption = caption
    }
}