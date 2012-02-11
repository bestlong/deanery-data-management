package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.CellStyle
import static junit.framework.Assert.assertNotNull
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.hssf.util.CellRangeAddress
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell

/**
 * author: evgeniy
 * date: 11.02.12
 */
class ExcelClient {

    def excelComponent
    def documentInitializer
    def headPrinter

    static transactional = true

    def exportToExcel() {

        assertNotNull(documentInitializer)

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("new sheet");

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
        cell.setCellStyle(centerCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(
                startRow, //first row (0-based)
                startRow, //last row  (0-based)
                9, //first column (0-based)
                14  //last column  (0-based)
        ));
        cell = row.createCell(9)
        cell.setCellValue("Розподіл між семестрами")
        cell.setCellStyle(centerCellStyle)
    }


}
