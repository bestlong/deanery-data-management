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

    def exportToExcel() {

        assertNotNull(documentInitializer)
        assertNotNull(headPrinter)
        assertNotNull(excelComponent)

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("new sheet");
        excelComponent.init(workbook)

        documentInitializer.initColumnsWidth(sheet)
        def row = headPrinter.printHeader(sheet)
        printSubjectHeader(sheet, row)

         // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();
    }

    private void printSubjectHeader(Sheet sheet, int startRow) {
        sheet.addMergedRegion(new CellRangeAddress(
                startRow, //first row (0-based)
                startRow, //last row  (0-based)
                3, //first column (0-based)
                8  //last column  (0-based)
        ));
        Row row = sheet.createRow(startRow)
        Cell cell = row.createCell(3)
        cell.setCellValue("Годин")
        cell.setCellStyle(excelComponent.centerCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(
                startRow, //first row (0-based)
                startRow, //last row  (0-based)
                9, //first column (0-based)
                14  //last column  (0-based)
        ));
        cell = row.createCell(9)
        cell.setCellValue("Розподіл між семестрами")
        cell.setCellStyle(excelComponent.centerCellStyle)
    }


}
