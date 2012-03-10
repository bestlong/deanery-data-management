package stu.cn.ua.dbf.dto

/**
 * author: evgeniy
 * date: 10.03.12
 */
class ErrorInfo {
    def message
    def field
    def rejectedValue

    ErrorInfo(message, field, rejectedValue) {
        this.message = message
        this.field = field
        this.rejectedValue = rejectedValue
    }

    def getMessage() {
        return message
    }

    def getField() {
        return field
    }
}
