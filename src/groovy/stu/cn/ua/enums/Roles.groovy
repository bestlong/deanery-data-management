package stu.cn.ua.enums

/**
 * @author evgeniy
 * date: 09.04.12
 */
enum Roles {
    SECRETARY("Секретарь"),
    PROREKTOR("Проректор"),
    DEAN("Декан")

    def text

    Roles(text) {
        this.text = text
    }
}