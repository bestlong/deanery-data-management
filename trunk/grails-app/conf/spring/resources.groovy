import stu.cn.ua.excel.DocumentInitializer
import stu.cn.ua.excel.HeadPrinter
import stu.cn.ua.excel.ExcelClient
import stu.cn.ua.excel.ExcelComponent
import decanat.grails.ExcelService
import stu.cn.ua.excel.SubjectHeaderPrinter
import stu.cn.ua.excel.SubjectPrinter
import stu.cn.ua.excel.SubjectFooterPrinter
import stu.cn.ua.excel.PractisePrinter

// Place your Spring DSL code here
beans = {

    excelComponent(ExcelComponent){}

    subjectFooterPrinter(SubjectFooterPrinter){
        excelComponent = ref("excelComponent")
    }

    practisePrinter(PractisePrinter){
        excelComponent = ref("excelComponent")
    }

    subjectHeadPrinter(SubjectHeaderPrinter){
        excelComponent = ref("excelComponent")
    }

    subjectPrinter(SubjectPrinter){
        excelComponent = ref("excelComponent")
    }

    headPrinter(HeadPrinter) {
        excelComponent = ref("excelComponent")
    }

    documentInitializer(DocumentInitializer) {
        excelComponent = ref("excelComponent")
    }

    excelClient(ExcelClient){
        documentInitializer = ref("documentInitializer")
        headPrinter = ref("headPrinter")
        excelComponent = ref("excelComponent")
    }

    excelService(ExcelService){
        subjectHeadPrinter = ref("subjectHeadPrinter")
        documentInitializer = ref("documentInitializer")
        headPrinter = ref("headPrinter")
        excelComponent = ref("excelComponent")
        subjectPrinter = ref("subjectPrinter")
        subjectFooterPrinter = ref("subjectFooterPrinter")
        practisePrinter = ref("practisePrinter")
    }
}
