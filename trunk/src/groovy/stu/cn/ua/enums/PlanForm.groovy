package stu.cn.ua.enums

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 13.08.11
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
enum PlanForm {
    DAILY(1, "денна"),
    EXTRAMURAL(2, "заочна"),
    EVENING(3, "вечірня")

    public int number;
    public String name;

    PlanForm(int number, String name) {
        this.number = number
        this.name = name;
    }


}
