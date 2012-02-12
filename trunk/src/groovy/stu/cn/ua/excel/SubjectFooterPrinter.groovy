package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.hssf.util.CellRangeAddress

/**
 * author: evgeniy
 * date: 12.02.12
 */
class SubjectFooterPrinter {

    def excelComponent

    def printFooter(Sheet sheet, int startRow){
        def row = sheet.createRow(startRow)
        def row2 = sheet.createRow(startRow+1)
        def cCol = 1
        def cell = row.createCell(cCol)
        cell.setCellValue("Усього")
        cell.setCellStyle(excelComponent.rightCellStyle)
        
        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, cCol, cCol+1));
        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, cCol, cCol+1));

    }
}
