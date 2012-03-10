package stu.cn.ua.dbf.enums

/**
 * @author evgeniy
 * date: 10.03.12
 */
public enum AllowedFiles {
    DIS("dis.dbf"), KAFEDRA("kafedra.dbf"), SPEC("spec.dbf")

    def filename

    AllowedFiles(filename) {
        this.filename = filename
    }
}