package stu.cn.ua.dbf.dto

import java.text.MessageFormat

/**
 * @author evgeniy
 * date: 10.03.12
 */
class SpecilaityPlanDTO {

    private String codsp
    private String codspec
    private String napram
    private String baza
    private String forma
    private String name
    private String qualRiv
    private String qualif
    private String srok
    private String namedip
    private String codname

    String getCodsp() {
        return codsp
    }

    void setCodsp(String codsp) {
        this.codsp = codsp
    }

    String getCodspec() {
        return codspec
    }

    void setCodspec(String codspec) {
        this.codspec = codspec
    }

    String getNapram() {
        return napram
    }

    void setNapram(String napram) {
        this.napram = napram
    }

    String getBaza() {
        return baza
    }

    void setBaza(String baza) {
        this.baza = baza
    }

    String getForma() {
        return forma
    }

    void setForma(String forma) {
        this.forma = forma
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getQualRiv() {
        return qualRiv
    }

    void setQualRiv(String qualRiv) {
        this.qualRiv = qualRiv
    }

    String getQualif() {
        return qualif
    }

    void setQualif(String qualif) {
        this.qualif = qualif
    }

    String getSrok() {
        return srok
    }

    void setSrok(String srok) {
        this.srok = srok
    }

    String getNamedip() {
        return namedip
    }

    void setNamedip(String namedip) {
        this.namedip = namedip
    }

    String getCodname() {
        return codname
    }

    void setCodname(String codname) {
        this.codname = codname
    }

    @Override
    String toString() {
        return MessageFormat.format("CODSP: {0}, CODSPEC: {1}, NAPRAM: {2}, BAZA: {3}, FORMA: {4}, NAME: {5}, QUAL_RIV: {6}, QUALIF: {7}, SROK: {8}, NAMEDIP: {9}, CODNAME: {10}",
                codsp, codspec, napram, baza, forma, name, qualRiv, qualif, srok, namedip, codname)
    }
}
