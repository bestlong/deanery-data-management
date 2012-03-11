package decanat.grails

import stu.cn.ua.dbf.dto.ValidationResult
import stu.cn.ua.dbf.dto.ErrorInfo
import stu.cn.ua.dbf.dto.SpecilaityPlanDTO

class Speciality {

    String code
    String specialityCode;
    String name;
    String shortName;

    private static final fieldMap = [code: 'CODSP', specialityCode: 'CODSPEC', name: 'NAME', shortName: 'CODNAME']

    static constraints = {
        specialityCode(blank: false)
        name(blank: false)
        shortName(nullable: true)
        code(nullable: true)
    }

    public static ValidationResult validate(SpecilaityPlanDTO specialityPlanDTO){
        Speciality speciality = Speciality.findByCode(specialityPlanDTO.codsp)
        if (!speciality || "".equals(specialityPlanDTO.codsp)){
            speciality = new Speciality(name:  specialityPlanDTO.name, shortName: specialityPlanDTO.codname, specialityCode: specialityPlanDTO.codspec, code: specialityPlanDTO.codsp)
        }
        else {
            speciality.name =specialityPlanDTO.name
            speciality.shortName = specialityPlanDTO.codname
            speciality.specialityCode= specialityPlanDTO.codspec
        }
        if (!speciality.validate()){
            List<ErrorInfo> validationErrors = new ArrayList<ErrorInfo>()
            speciality.errors.allErrors.each {
                validationErrors.add(new ErrorInfo(specialityPlanDTO.toString(), fieldMap.get(it.field), it.rejectedValue))
            }
            return new ValidationResult(false, validationErrors)
        } else {
            return new ValidationResult(true)
        }
    }

    public static Speciality saveSpeciality(SpecilaityPlanDTO specialityPlanDTO){
        def speciality = Speciality.findByCode(specialityPlanDTO.codsp)
        if (!speciality || "".equals(specialityPlanDTO.codsp)){
            speciality = new Speciality(name:  specialityPlanDTO.name, shortName: specialityPlanDTO.codname, specialityCode: specialityPlanDTO.codspec, code: specialityPlanDTO.codsp)
        }
        else {
            speciality.name =specialityPlanDTO.name
            speciality.shortName = specialityPlanDTO.codname
            speciality.specialityCode= specialityPlanDTO.codspec
        }
        speciality.save()
    }

    @Override
    String toString() {
        return "Специальность, код: ${kod}, имя: ${name} "
    }
}
