package decanat.grails

import stu.cn.ua.enums.ControlTypeEnum
import stu.cn.ua.CommonUtils

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

    static PlanControlType createPlanControlType(PlanControlType planControlType){
        PlanControlType newPlanControlType = new PlanControlType()
        newPlanControlType.mask = planControlType.mask
        newPlanControlType.semestr = planControlType.semestr

        newPlanControlType
    }

    private String getMaskAsBitString(){
        def res = new StringBuilder("")
        def strMask = Integer.toBinaryString(mask)
        def sizeNeed = OPTIMAL_MASK_SIZE - strMask.size()
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

    public String toCSV(){
        String srt = new String();
        def  nodes=["id" , "semestr", "planSubjectId" , "mask"];
        for(String obj: nodes){
            def nod=this."${obj}";
            srt = srt + CommonUtils.wordToCSV(nod);
        }
        srt=srt+"\n"
        return srt;
    }
}
