package stu.cn.ua.enums

/**
 * author: evgeniy
 * date: 12.02.12
 */
enum ControlTypeEnum {
    EXAM(5, "іспитів"), ZACHET(4, "заліків"), C_PROJECT(3, "курсових проектів"), C_WORK(2 ,"курсових робіт"), RGR(1, "РГР"), CONTR_WORK(0, "контрольних робіт")

    int bitNum
    String caption

    ControlTypeEnum(int bitNum, String caption) {
        this.bitNum = bitNum
        this.caption = caption
    }


}
