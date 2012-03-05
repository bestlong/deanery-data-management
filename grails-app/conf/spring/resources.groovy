import stu.cn.ua.excel.DocumentInitializer
import stu.cn.ua.excel.HeadPrinter
import stu.cn.ua.excel.ExcelClient
import stu.cn.ua.excel.ExcelComponent
import decanat.grails.ExcelService
import stu.cn.ua.excel.SubjectHeaderPrinter
import stu.cn.ua.excel.SubjectPrinter
import stu.cn.ua.excel.SubjectFooterPrinter
import stu.cn.ua.excel.PractisePrinter
import stu.cn.ua.excel.FooterPrinter

// Place your Spring DSL code here
beans = {

    subjectFooterPrinter(SubjectFooterPrinter){
    }

    footerPrinter(FooterPrinter){
    }

    practisePrinter(PractisePrinter){
    }

    subjectHeadPrinter(SubjectHeaderPrinter){
    }

    subjectPrinter(SubjectPrinter){
    }

    headPrinter(HeadPrinter) {
    }

    documentInitializer(DocumentInitializer) {
    }

    excelClient(ExcelClient){
        documentInitializer = ref("documentInitializer")
        headPrinter = ref("headPrinter")
    }

    excelService(ExcelService){
        footerPrinter = ref("footerPrinter")
        subjectHeadPrinter = ref("subjectHeadPrinter")
        documentInitializer = ref("documentInitializer")
        headPrinter = ref("headPrinter")
        subjectPrinter = ref("subjectPrinter")
        subjectFooterPrinter = ref("subjectFooterPrinter")
        practisePrinter = ref("practisePrinter")
    }
}
