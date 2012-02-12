package stu.cn.ua.enums

/**
 * author: evgeniy
 * date: 12.02.12
 */
enum ControlTypeEnum {
    EXAM(5), ZACHET(4), C_PROJECT(3), C_WORK(2), RGR(1), CONTR_WORK(0)

    int bitNum

    ControlTypeEnum(int bitNum) {
        this.bitNum = bitNum
    }
}
