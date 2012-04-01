package plan

import decanat.grails.CSVExportService

class CSVExportController {

    def index() { }



    def exportPlanToCSV() {

        OutputStreamWriter outFile;

        try {
            response.contentType = "application/vnd.ms-excel"
            response.setHeader("Content-disposition", "attachment;filename=plan.csv")

            outFile = new OutputStreamWriter(response.outputStream);


            CSVExportService imprt=new CSVExportService(outFile);

            imprt.exportToCSVPlan(params.id);

            List<?> list1=imprt.exportToCSVPlanSubject(params.id);

            imprt.exportToCSVPlanControlType(list1);

            imprt.exportToCSVPlanHours(list1);

            imprt.exportToCSVPlanPractice(params.id);

            imprt.exportToCSVPlanStateExam(params.id);

            imprt.exportToCSVSemestr(params.id);

            outFile.close();
        } catch (Exception e) {

            e.printStackTrace();

        }

        redirect(url: "/#")


    }

    def  exportWorkPlanToCSV() {

        OutputStreamWriter outFile;

        try {
            response.contentType = "application/vnd.ms-excel"
            response.setHeader("Content-disposition", "attachment;filename=workplan.csv")

            outFile = new OutputStreamWriter(response.outputStream);

            CSVExportService imprt=new CSVExportService(outFile);

            imprt.exportToCSVWorkPlan(params.id);

            List<?> list1=imprt.exportToCSVPlanSubject(params.id);

            imprt.exportToCSVPlanControlType(list1);

            imprt.exportToCSVPlanHours(list1);

            imprt.exportToCSVPlanPractice(params.id);

            imprt.exportToCSVPlanStateExam(params.id);

            imprt.exportToCSVSemestr(params.id);

            outFile.close();
        } catch (Exception e) {

            e.printStackTrace();

        }

        redirect(url: "/#")


    }






    def speciality() {

        FileWriter outFile;

        try {


        } catch (Exception e) {

            e.printStackTrace();

        }

        redirect(url: "/#")


    }


}
