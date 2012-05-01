package decanat.grails

import stu.cn.ua.dbf.dto.ValidationResult
import stu.cn.ua.dbf.dto.ErrorInfo
import stu.cn.ua.dbf.dto.SpecialityPlanDTO
import stu.cn.ua.CommonUtils
import decanat.grails.domain.User

class Speciality {

    String code
    String specialityCode;
    String name;
    String shortName;
    Integer referenceCount = 0
    Deanery deanery

    private static final fieldMap = [code: 'CODSP', specialityCode: 'CODSPEC', name: 'NAME', shortName: 'CODNAME']

    static constraints = {
        specialityCode(blank: false)
        name(blank: false)
        shortName(nullable: true)
        code(nullable: true)
        referenceCount(nullable: false)
    }

    public static ValidationResult validate(SpecialityPlanDTO specialityPlanDTO, User user) {
        Speciality speciality = Speciality.findByCodeAndDeanery(specialityPlanDTO.codsp, user.deanery)
        if (!speciality || "".equals(specialityPlanDTO.codsp)) {
            speciality = new Speciality(name: CommonUtils.prepareString(specialityPlanDTO.name),
                    shortName: CommonUtils.prepareString(specialityPlanDTO.codname),
                    specialityCode: CommonUtils.prepareString(specialityPlanDTO.codspec),
                    code: CommonUtils.prepareString(specialityPlanDTO.codsp),
                    deanery: user.deanery)
        }
        else {
            speciality.name = CommonUtils.prepareString(specialityPlanDTO.name)
            speciality.shortName = CommonUtils.prepareString(specialityPlanDTO.codname)
            speciality.specialityCode = CommonUtils.prepareString(specialityPlanDTO.codspec)
            speciality.deanery = user.deanery
        }
        if (!speciality.validate()) {
            List<ErrorInfo> validationErrors = new ArrayList<ErrorInfo>()
            speciality.errors.allErrors.each {
                validationErrors.add(new ErrorInfo(specialityPlanDTO.toString(), fieldMap.get(it.field), it.rejectedValue))
            }
            return new ValidationResult(false, validationErrors)
        } else {
            return new ValidationResult(true)
        }
    }

    public static Speciality saveSpeciality(SpecialityPlanDTO specialityPlanDTO, User user) {
        def speciality = Speciality.findByCodeAndDeanery(specialityPlanDTO.codsp, user.deanery)
        if (!speciality || "".equals(specialityPlanDTO.codsp)) {
            speciality = new Speciality(name: CommonUtils.prepareString(specialityPlanDTO.name),
                    shortName: CommonUtils.prepareString(specialityPlanDTO.codname),
                    specialityCode: CommonUtils.prepareString(specialityPlanDTO.codspec),
                    deanery: user.deanery,
                    code: CommonUtils.prepareString(specialityPlanDTO.codsp))
        }
        else {
            speciality.name = CommonUtils.prepareString(specialityPlanDTO.name)
            speciality.shortName = CommonUtils.prepareString(specialityPlanDTO.codname)
            speciality.specialityCode = CommonUtils.prepareString(specialityPlanDTO.codspec)
            speciality.deanery = user.deanery
        }
        speciality.save()
    }

    @Override
    String toString() {
        return "Специальность, код: ${code}, имя: ${name} "
    }

    public String toCSV(){
        String srt = new String();
        def  nodes=["id" , "code", "name" , "referenceCount", "shortName", "specialityCode" ];
        for(String obj: nodes){
            def nod=this."${obj}";
            srt = srt + CommonUtils.wordToCSV(nod);
        }
        srt=srt+"\n"
        return srt;
    }
}
