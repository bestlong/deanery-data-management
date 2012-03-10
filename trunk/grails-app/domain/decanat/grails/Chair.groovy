package decanat.grails

import stu.cn.ua.dbf.dto.ValidationResult
import stu.cn.ua.dbf.dto.ChairDTO
import stu.cn.ua.dbf.dto.ErrorInfo

class Chair {

    String name;
    String shortName;
    String head
    String codeChair

    private static final fieldMap = [name: 'NAMEKAF', shortName: 'SHORTKAF', head: 'FAMZAV', codeChair: 'CODKAF']

    static constraints = {
        name (blank: false, unique: true)
        shortName(unique: true, nullable: true)
        head(nullable: true)
        codeChair(nullable: true)
    }

    public static Chair saveChair(ChairDTO chairDTO){
        Chair chair = Chair.findByCodeChair(chairDTO.codkaf)
        if (!chair || "".equals(chairDTO.codkaf)){
            chair = new Chair(name:  chairDTO.namekaf, shortName: chairDTO.shortkaf, head: chairDTO.famzav, codeChair: chairDTO.codkaf)
        }
        else {
            chair.name =chairDTO.namekaf
            chair.shortName = chairDTO.shortkaf
            chair.head = chairDTO.famzav
        }
        chair.save()
    }

    public static ValidationResult validate(ChairDTO chairDTO){
        Chair chair = Chair.findByCodeChair(chairDTO.codkaf)
        if (!chair || "".equals(chairDTO.codkaf)){
            chair = new Chair(name:  chairDTO.namekaf, shortName: chairDTO.shortkaf, head: chairDTO.famzav, codeChair: chairDTO.codkaf)
        }
        else {
            chair.name =chairDTO.namekaf
            chair.shortName = chairDTO.shortkaf
            chair.head = chairDTO.famzav
        }
        if (!chair.validate()){
            List<ErrorInfo> validationErrors = new ArrayList<ErrorInfo>()
            chair.errors.allErrors.each {
               validationErrors.add(new ErrorInfo(chairDTO.toString(), fieldMap.get(it.field), it.rejectedValue))
            }
            return new ValidationResult(false, validationErrors)
        } else {
            return new ValidationResult(true)
        }
    }
}
