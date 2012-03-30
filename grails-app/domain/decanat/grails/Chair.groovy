package decanat.grails

import stu.cn.ua.dbf.dto.ValidationResult
import stu.cn.ua.dbf.dto.ChairDTO
import stu.cn.ua.dbf.dto.ErrorInfo
import stu.cn.ua.CommonUtils

class Chair {

    String name;
    String shortName;
    String head
    String codeChair
    Integer referenceCount = 0

    private static final fieldMap = [name: 'NAMEKAF', shortName: 'SHORTKAF', head: 'FAMZAV', codeChair: 'CODKAF']

    static constraints = {
        name(blank: false, unique: true)
        shortName(unique: true, nullable: true)
        head(nullable: true)
        codeChair(nullable: true)
        referenceCount(nullable: false)
    }

    public static Chair saveChair(ChairDTO chairDTO) {
        Chair chair = Chair.findByCodeChair(chairDTO.codkaf)
        if (!chair || "".equals(chairDTO.codkaf)) {
            chair = new Chair(name: CommonUtils.prepareString(chairDTO.namekaf),
                    shortName: CommonUtils.prepareString(chairDTO.shortkaf),
                    head: CommonUtils.prepareString(chairDTO.famzav),
                    codeChair: CommonUtils.prepareString(chairDTO.codkaf))
        }
        else {
            chair.name = CommonUtils.prepareString(chairDTO.namekaf)
            chair.shortName = CommonUtils.prepareString(chairDTO.shortkaf)
            chair.head = CommonUtils.prepareString(chairDTO.famzav)
        }
        chair.save()
    }

    public static ValidationResult validate(ChairDTO chairDTO) {
        Chair chair = Chair.findByCodeChair(chairDTO.codkaf)
        if (!chair || "".equals(chairDTO.codkaf)) {
            chair = new Chair(name: chairDTO.namekaf, shortName: chairDTO.shortkaf, head: chairDTO.famzav, codeChair: chairDTO.codkaf)
        }
        else {
            chair.name = chairDTO.namekaf
            chair.shortName = chairDTO.shortkaf
            chair.head = chairDTO.famzav
        }
        if (!chair.validate()) {
            List<ErrorInfo> validationErrors = new ArrayList<ErrorInfo>()
            chair.errors.allErrors.each {
                validationErrors.add(new ErrorInfo(chairDTO.toString(), fieldMap.get(it.field), it.rejectedValue))
            }
            return new ValidationResult(false, validationErrors)
        } else {
            return new ValidationResult(true)
        }
    }

    public def toCSV(){

        String srt = new String();

        def  nodes=["id" ,"codeChair", "head", "name", "referenceCount", "shortName"];



        for(String obj: nodes){
            def nod=this."${obj}";
            srt = srt + CommonUtils.wordToCSV(nod);
        }
        srt=srt+"\n"
        return srt;
    }

}
