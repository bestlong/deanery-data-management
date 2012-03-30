package decanat.grails

import stu.cn.ua.CommonUtils

class University {

    String prorektor;
    String dean;

    static constraints = {
        prorektor(blank: false)
        dean(blank: false)
    }

    public String toCSV(){

        String srt = new String();


        def  nodes=["id" , "dean", "prorektor" ];

        for(String obj: nodes){
            def nod=this."${obj}";
            srt = srt + CommonUtils.wordToCSV(nod);
        }
        srt=srt+"\n"
        return srt;
    }
}
