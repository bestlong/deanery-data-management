package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import decanat.grails.Plan
import org.apache.poi.hssf.util.CellRangeAddress
import java.text.SimpleDateFormat
import java.text.DateFormat
import decanat.grails.University

/**
 * author: evgeniy
 * date: 14.02.12
 */
class FooterPrinter {

    private ExcelComponent excelComponent

    public void print(Plan plan, int sRow, Sheet sheet, Date date){
        def university = University.findAll().get(0)

        DateFormat dateFormat = new SimpleDateFormat("MM. dd. yyyy");
        def row = sheet.createRow(sRow)
        def cell = row.createCell(1)
        cell.setCellValue(dateFormat.format(date))
        cell.setCellStyle(excelComponent.leftCellStyle)

        cell = row.createCell(4)
        cell.setCellValue("Декан")
        cell.setCellStyle(excelComponent.leftCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(sRow, sRow, 5, 9));
        cell = row.createCell(5)
        cell.setCellValue(university.dean)
        cell.setCellStyle(excelComponent.leftCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(sRow, sRow, 30, 38));
        cell = row.createCell(30)
        cell.setCellValue("зав. кафедрою")
        cell.setCellStyle(excelComponent.leftCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(sRow, sRow, 43, 52));
        cell = row.createCell(43)
        cell.setCellValue(plan.chair.head)
        cell.setCellStyle(excelComponent.leftCellStyle)
    }

    void setExcelComponent(ExcelComponent excelComponent) {
        this.excelComponent = excelComponent
    }
}
