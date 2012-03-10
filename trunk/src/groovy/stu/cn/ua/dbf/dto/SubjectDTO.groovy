package stu.cn.ua.dbf.dto

import java.text.MessageFormat

/**
 * @author evgeniy
 * date: 10.03.12
 */
class SubjectDTO {

    public String coddis
    public String shortdis
    public String namedis

    String getCoddis() {
        return coddis
    }

    void setCoddis(String coddis) {
        this.coddis = coddis
    }

    String getShortdis() {
        return shortdis
    }

    void setShortdis(String shortdis) {
        this.shortdis = shortdis
    }

    String getNamedis() {
        return namedis
    }

    void setNamedis(String namedis) {
        this.namedis = namedis
    }

    @Override
    String toString() {
        return MessageFormat.format("CODDIS: {0}, SHORTDIS: {1}, NAMEDIS: {2}",
                coddis, shortdis, namedis)
    }
}
