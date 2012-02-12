package decanat.grails

import stu.cn.ua.enums.ControlTypeEnum

class PlanControlType {

    static belongsTo = [planSubject: PlanSubject]

    int mask;
    int semestr;
    PlanSubject planSubject;

    private static final Integer OPTIMAL_MASK_SIZE = 6

    static constraints = {
        mask(nullable: false)
        semestr(nullable: false)
        planSubject(nullable: false)
    }

    private String getMaskAsBitString(){
        def res = new StringBuilder("")
        def strMask = Integer.toBinaryString(mask)
        println "strMask ${strMask}"
        def sizeNeed = OPTIMAL_MASK_SIZE - strMask.size()
        println "sizeNeeded ${sizeNeed}"
        for (int i=0; i<sizeNeed; i++){
            res.append("0")
        }
        res.append(strMask)
        res.toString()
    }

    public boolean isControlTypeExists(ControlTypeEnum controlType){
        def bits = getMaskAsBitString()

        return bits.charAt(controlType.bitNum).equals("1".charAt(0))

    }
}
