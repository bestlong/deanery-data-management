package plan

import decanat.grails.CSVExportService
import stu.cn.ua.dbf.enums.AllowedFiles
import stu.cn.ua.dbf.reader.ChairDTOReader
import stu.cn.ua.dbf.reader.SubjectDTOReader
import stu.cn.ua.dbf.reader.SpecialityPlanDTOReader

//TODO не надо ставить ентер после каждой строки, в начале методов и тд
class CSVExportController {

    def index() {
        def errors = chainModel?.validationErrors
        if (errors){
            [validationErrors: errors, active: 5]
        }
        [active: 5]
    }

    def exportPlanToCSV() {
        OutputStreamWriter outFile;
        try {
            response.contentType = "application/vnd.ms-excel"
            response.setHeader("Content-disposition", "attachment;filename=plan.csv")
            outFile = new OutputStreamWriter(response.outputStream,'CP1251');
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
            log.error(e.getMessage(), e)
        }
    }

    def  exportWorkPlanToCSV() {
        OutputStreamWriter outFile;
        try {
            response.contentType = "application/vnd.ms-excel"
            response.setHeader("Content-disposition", "attachment;filename=workplan.csv")
            outFile = new OutputStreamWriter(response.outputStream,'CP1251');
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
            log.error(e.getMessage(), e)
        }
    }


    def export(){
        if (params.myGroup=='directori'){
            exportDirectoriToCSV();
        }
        if (params.myGroup=='all'){
            exportAllToCSV();
        }
    }

    def exportAllToCSV() {
        OutputStreamWriter outFile;
        try {
            response.contentType = "application/vnd.ms-excel"
            response.setHeader("Content-disposition", "attachment;filename=allbase.csv")
            outFile = new OutputStreamWriter(response.outputStream,'CP1251');
            CSVExportService imprt=new CSVExportService(outFile);
            imprt.exportToCSVAllChair();
            imprt.exportToCSVAllPlan();
            imprt.exportToCSVAllPlanControlType();
            imprt.exportToCSVAllPlanHours();
            imprt.exportToCSVAllPlanPractice();
            imprt.exportToCSVAllPlanStateExam();
            imprt.exportToCSVAllPlanSubject();
            imprt.exportToCSVAllSemestr();
            imprt.exportToCSVAllSpeciality();
            imprt.exportToCSVAllSubject();
            imprt.exportToCSVAllUniversity();
            imprt.exportToCSVAllWorkPlan();
            outFile.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }


    def exportDirectoriToCSV() {
        OutputStreamWriter outFile;
        try {
            response.contentType = "application/vnd.ms-excel"
            response.setHeader("Content-disposition", "attachment;filename=direcroti.csv")
            outFile = new OutputStreamWriter(response.outputStream,'CP1251');
            CSVExportService imprt=new CSVExportService(outFile);
            imprt.exportToCSVAllChair();
            imprt.exportToCSVAllSpeciality();
            imprt.exportToCSVAllSubject();
            outFile.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }
}
