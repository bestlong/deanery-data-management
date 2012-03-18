package decanat.grails

import stu.cn.ua.dbf.dto.ValidationResult
import stu.cn.ua.dbf.dto.ErrorInfo
import stu.cn.ua.dbf.dto.SubjectDTO
import stu.cn.ua.CommonUtils

class Subject {

    String name
    Chair chair
    String shortName
    String code
    Integer referenceCount = 0

    private static final fieldMap = [name: 'NAMEDIS', shortName: 'SHORTDIS', code: 'CODDIS']

    static constraints = {
        name(blank: false)
        chair(nullable: true)
        shortName(nullable: true)
        code(nullable: true)
        referenceCount(nullable: false)
    }

    public static Subject saveSubject(SubjectDTO subjectDTO) {
        def subject = Subject.findByCode(subjectDTO.coddis)
        if (!subject || "".equals(subjectDTO.coddis)) {
            subject = new Subject(name: CommonUtils.prepareString(subjectDTO.namedis),
                    shortName: CommonUtils.prepareString(subjectDTO.shortdis),
                    code: CommonUtils.prepareString(subjectDTO.coddis))
        } else {
            subject.name = CommonUtils.prepareString(subjectDTO.namedis)
            subject.shortName = CommonUtils.prepareString(subjectDTO.shortdis)
        }
        subject.save()
    }

    public static ValidationResult validate(SubjectDTO subjectDTO) {
        def subject = Subject.findByCode(subjectDTO.coddis)
        if (!subject || "".equals(subjectDTO.coddis)) {
            subject = new Subject(name: subjectDTO.namedis, shortName: subjectDTO.shortdis, code: subjectDTO.coddis)
        } else {
            subject.name = subjectDTO.namedis
            subject.shortName = subjectDTO.shortdis
        }
        if (!subject.validate()) {
            List<ErrorInfo> validationErrors = new ArrayList<ErrorInfo>()
            subject.errors.allErrors.each {
                validationErrors.add(new ErrorInfo(subjectDTO.toString(), fieldMap.get(it.field), it.rejectedValue))
            }
            return new ValidationResult(false, validationErrors)
        } else {
            return new ValidationResult(true)
        }
    }

    def afterInsert() {
        if (null != chair) {
            chair?.referenceCount++
        }
    }

    def afterDelete() {
        if (null != chair) {
            chair?.referenceCount--
        }
    }

    def beforeDelete() {
        if (0 != referenceCount) {
            throw new IllegalStateException("Reference count = ${referenceCount}")
        }
    }
}
