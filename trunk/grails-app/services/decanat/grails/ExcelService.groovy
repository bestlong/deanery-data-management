package decanat.grails

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.hssf.util.CellRangeAddress
import static junit.framework.Assert.assertNotNull

class ExcelService {

    static transactional = false

    def documentInitializer
    def headPrinter
    def excelComponent
    def subjectHeadPrinter
    def subjectPrinter

    def exportToExcel() {

        assertNotNull(documentInitializer)
        assertNotNull(headPrinter)
        assertNotNull(excelComponent)

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("new sheet");
        excelComponent.init(workbook)

        documentInitializer.initColumnsWidth(sheet)
        def row = headPrinter.printHeader(sheet)
        subjectHeadPrinter.printSubjectHeader(sheet, row)

        subjectPrinter.printSubjects(sheet, row+5)

         // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();
    }


}
