import stu.cn.ua.excel.DocumentInitializer
import stu.cn.ua.excel.HeadPrinter
import stu.cn.ua.excel.ExcelClient
import stu.cn.ua.excel.ExcelComponent
import decanat.grails.ExcelService
import stu.cn.ua.excel.SubjectHeaderPrinter

// Place your Spring DSL code here
beans = {

    excelComponent(ExcelComponent){}

    subjectHeadPrinter(SubjectHeaderPrinter){
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
    }


}
