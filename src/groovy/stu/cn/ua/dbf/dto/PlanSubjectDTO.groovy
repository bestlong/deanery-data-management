package stu.cn.ua.dbf.dto

import java.text.MessageFormat

/**
 * @author evgeniy
 * date: 03.05.12
 */
class PlanSubjectDTO {
    private String coddis
    private String codkaf
    private Double sem
    private Double lk
    private Double sm
    private Double pr
    private Double lb
    private Double kpr
    private Double krb
    private Double rgr
    private Double knr
    private Double z
    private Double e

    @Override
    String toString() {
        return MessageFormat.format("CODKAF: {0}, CODDIS: {1}",
                codkaf, coddis)
    }

    String getCoddis() {
        return coddis
    }

    void setCoddis(String coddis) {
        this.coddis = coddis
    }

    String getCodkaf() {
        return codkaf
    }

    void setCodkaf(String codkaf) {
        this.codkaf = codkaf
    }

    Double getSem() {
        return sem
    }

    void setSem(Double sem) {
        this.sem = sem
    }

    Double getLk() {
        return lk
    }

    void setLk(Double lk) {
        this.lk = lk
    }

    Double getSm() {
        return sm
    }

    void setSm(Double sm) {
        this.sm = sm
    }

    Double getPr() {
        return pr
    }

    void setPr(Double pr) {
        this.pr = pr
    }

    Double getLb() {
        return lb
    }

    void setLb(Double lb) {
        this.lb = lb
    }

    Double getKpr() {
        return kpr
    }

    void setKpr(Double kpr) {
        this.kpr = kpr
    }

    Double getKrb() {
        return krb
    }

    void setKrb(Double krb) {
        this.krb = krb
    }

    Double getRgr() {
        return rgr
    }

    void setRgr(Double rgr) {
        this.rgr = rgr
    }

    Double getKnr() {
        return knr
    }

    void setKnr(Double knr) {
        this.knr = knr
    }

    Double getZ() {
        return z
    }

    void setZ(Double z) {
        this.z = z
    }

    Double getE() {
        return e
    }

    void setE(Double e) {
        this.e = e
    }
}