package stu.cn.ua.excel

import decanat.grails.Plan
import org.apache.poi.hssf.util.CellRangeAddress
import org.apache.poi.ss.usermodel.Sheet

/**
 * author: evgeniy
 * date: 12.02.12
 */
class PractisePrinter {

    ExcelComponent excelComponent


    public void print(Plan plan, int startRow, Sheet sheet){
        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 0, 3));
        def row = sheet.createRow(startRow)
        def cCol = 1
        def cell = row.createCell(0)
        cell.setCellValue("2. Практики")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 15, excelComponent.COLUMN_COUNT-1));
        cell = row.createCell(15)
        cell.setCellValue("2. Державна атестація")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)
        startRow++


        row = sheet.createRow(startRow)
        cell = row.createCell(0)
        cell.setCellValue("№")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 1, 4));
        cell = row.createCell(1)
        cell.setCellValue("Назва практики")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 5, 7));
        cell = row.createCell(5)
        cell.setCellValue("семестр")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 8, 11));
        cell = row.createCell(8)
        cell.setCellValue("тижнів")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 20, 22));
        cell = row.createCell(20)
        cell.setCellValue("№")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 23, 26));
        cell = row.createCell(23)
        cell.setCellValue("семестр")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 27, 34));
        cell = row.createCell(27)
        cell.setCellValue("строки проведення")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 35, excelComponent.COLUMN_COUNT-1));
        cell = row.createCell(35)
        cell.setCellValue("форма державної атестації")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        def curRow = null

        plan.practise.eachWithIndex {it, idx ->
            startRow++;
            
            row = sheet.createRow(startRow)
            if (null == curRow){
                curRow =row
            }
            cell = row.createCell(0)
            cell.setCellValue(idx+1)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 1, 4));
            cell = row.createCell(1)
            cell.setCellValue(it.name)
            cell.setCellStyle(excelComponent.leftCellStyle)

            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 5, 7));
            cell = row.createCell(5)
            cell.setCellValue(it.semestr)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 8, 11));
            cell = row.createCell(8)
            cell.setCellValue(it.weeks)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)            
        }
        if (curRow != null){
            row = curRow
        } else {
            startRow++
            row = sheet.createRow(startRow)
        }

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 20, 22));
        cell = row.createCell(20)
        cell.setCellValue(1)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 23, 26));
        cell = row.createCell(23)
        cell.setCellValue(plan.stateExam.semestr)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 27, 34));
        cell = row.createCell(27)
        cell.setCellValue(plan.stateExam.date)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 35, excelComponent.COLUMN_COUNT-1));
        cell = row.createCell(35)
        cell.setCellValue(plan.stateExam.forma)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)        
    }

    void setExcelComponent(ExcelComponent excelComponent) {
        this.excelComponent = excelComponent
    }
}
