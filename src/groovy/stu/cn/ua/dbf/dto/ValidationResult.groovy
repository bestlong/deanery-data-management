package stu.cn.ua.dbf.dto

/**
 * author: evgeniy
 * date: 10.03.12
 */
class ValidationResult {

    private boolean success
    private List<ErrorInfo> messages

    ValidationResult(boolean success) {
        if (!success){
            throw new IllegalArgumentException("If validation failed you must add validation errors, use another constructor")
        }
        this.success = success
    }

    ValidationResult(boolean success, List<ErrorInfo> messages) {
        this.success = success
        this.messages = messages
    }

    boolean getSuccess() {
        return success
    }

    List<String> getMessages() {
        return messages
    }
}
