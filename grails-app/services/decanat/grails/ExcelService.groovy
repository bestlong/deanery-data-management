package decanat.grails

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.hssf.util.CellRangeAddress
import static junit.framework.Assert.assertNotNull
import stu.cn.ua.excel.DocumentInitializer
import stu.cn.ua.excel.HeadPrinter
import stu.cn.ua.excel.ExcelComponent
import stu.cn.ua.excel.SubjectHeaderPrinter
import stu.cn.ua.excel.SubjectPrinter
import stu.cn.ua.excel.SubjectFooterPrinter
import stu.cn.ua.excel.PractisePrinter
import stu.cn.ua.excel.FooterPrinter

class ExcelService {

    static transactional = false

    private DocumentInitializer documentInitializer
    private HeadPrinter headPrinter
    private ExcelComponent excelComponent
    private SubjectHeaderPrinter subjectHeadPrinter
    private SubjectPrinter subjectPrinter
    private SubjectFooterPrinter subjectFooterPrinter
    private PractisePrinter practisePrinter
    private FooterPrinter footerPrinter

    private static final int SUBJECT_HEADER_SIZE = 5

    public OutputStream exportToExcel(Plan plan, Date date, OutputStream out) {

        assertNotNull(documentInitializer)
        assertNotNull(headPrinter)
        assertNotNull(excelComponent)

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("new sheet");
        excelComponent.init(workbook)

        documentInitializer.initColumnsWidth(sheet)
        def row = headPrinter.printHeader(sheet, date, plan)
        subjectHeadPrinter.printSubjectHeader(sheet, row)

        row += SUBJECT_HEADER_SIZE

        row += subjectPrinter.printSubjects(sheet, row, plan)
        subjectFooterPrinter.printFooter(sheet, row, plan)

        row +=8
        def cnt = practisePrinter.print(plan, row, sheet)
        row += cnt
        footerPrinter.print(plan, row, sheet, date)



         // Write the output to a file
        workbook.write(out);
    }

    void setDocumentInitializer(DocumentInitializer documentInitializer) {
        this.documentInitializer = documentInitializer
    }

    void setHeadPrinter(HeadPrinter headPrinter) {
        this.headPrinter = headPrinter
    }

    void setExcelComponent(ExcelComponent excelComponent) {
        this.excelComponent = excelComponent
    }

    void setSubjectHeadPrinter(SubjectHeaderPrinter subjectHeadPrinter) {
        this.subjectHeadPrinter = subjectHeadPrinter
    }

    void setSubjectPrinter(SubjectPrinter subjectPrinter) {
        this.subjectPrinter = subjectPrinter
    }

    void setSubjectFooterPrinter(SubjectFooterPrinter subjectFooterPrinter) {
        this.subjectFooterPrinter = subjectFooterPrinter
    }

    void setPractisePrinter(PractisePrinter practisePrinter) {
        this.practisePrinter = practisePrinter
    }

    void setFooterPrinter(FooterPrinter footerPrinter) {
        this.footerPrinter = footerPrinter
    }
}
