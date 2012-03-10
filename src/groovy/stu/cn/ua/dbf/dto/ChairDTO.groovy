package stu.cn.ua.dbf.dto

import java.text.MessageFormat

/**
 * author: evgeniy
 * date: 10.03.12
 */
class ChairDTO {
    private String codkaf
    private String shortkaf
    private String famzav
    private String namekaf

    String getCodkaf() {
        return codkaf
    }

    void setCodkaf(String codkaf) {
        this.codkaf = codkaf
    }

    String getShortkaf() {
        return shortkaf
    }

    void setShortkaf(String shortkaf) {
        this.shortkaf = shortkaf
    }

    String getFamzav() {
        return famzav
    }

    void setFamzav(String famzav) {
        this.famzav = famzav
    }

    String getNamekaf() {
        return namekaf
    }

    void setNamekaf(String namekaf) {
        this.namekaf = namekaf
    }

    @Override
    String toString() {
        return MessageFormat.format("CODKAF: {0}, SHORTKAF: {1}, FAMZAV: {2}, NAMEKAF: {3}",
                                                    codkaf, shortkaf, famzav, namekaf)
    }
}
