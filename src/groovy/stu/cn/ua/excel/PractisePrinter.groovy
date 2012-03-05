package stu.cn.ua.excel

import decanat.grails.Plan
import org.apache.poi.hssf.util.CellRangeAddress
import org.apache.poi.ss.usermodel.Sheet

/**
 * author: evgeniy
 * date: 12.02.12
 */
class PractisePrinter {

    public int print(Plan plan, int startRow, Sheet sheet, ExcelComponent excelComponent){
        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 0, 9));
        def row = sheet.createRow(startRow)
        def cell = row.createCell(0)
        cell.setCellValue("2. Практики")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        int cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 17000, excelComponent.PAGE_POINTS_WIDTH)
        cell = row.createCell(cCell)
        cell.setCellValue("2. Державна атестація")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)
        startRow++


        row = sheet.createRow(startRow)
        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 0, 2000)
        cell = row.createCell(cCell)
        cell.setCellValue("№")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 2000, 10000)
        cell = row.createCell(cCell)
        cell.setCellValue("Назва практики")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 10000, 12000)
        cell = row.createCell(cCell)
        cell.setCellValue("семестр")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 12000, 14000)
        cell = row.createCell(cCell)
        cell.setCellValue("тижнів")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 16500, 18000)
        cell = row.createCell(cCell)
        cell.setCellValue("№")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 18000, 20000)
        cell = row.createCell(cCell)
        cell.setCellValue("семестр")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 20000, 23000)
        cell = row.createCell(cCell)
        cell.setCellValue("строки проведення")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 23000, excelComponent.PAGE_POINTS_WIDTH)
        cell = row.createCell(cCell)
        cell.setCellValue("форма державної атестації")
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        def curRow = null

        plan.practise.eachWithIndex {it, idx ->
            startRow++;
            
            row = sheet.createRow(startRow)
            if (null == curRow){
                curRow =row
            }
            cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 0, 2000)
            cell = row.createCell(cCell)
            cell.setCellValue(idx+1)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 2000, 10000)
            cell = row.createCell(cCell)
            cell.setCellValue(it.name)
            cell.setCellStyle(excelComponent.leftCellStyle)

            cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 10000, 12000)
            cell = row.createCell(cCell)
            cell.setCellValue(it.semestr)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 12000, 14000)
            cell = row.createCell(cCell)
            cell.setCellValue(it.weeks)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)            
        }
        if (curRow != null){
            row = curRow
        } else {
            startRow++
            row = sheet.createRow(startRow)
        }

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 16500, 18000)
        cell = row.createCell(cCell)
        cell.setCellValue(1)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 18000, 20000)
        cell = row.createCell(cCell)
        cell.setCellValue(plan.stateExam.semestr)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 20000, 23000)
        cell = row.createCell(cCell)
        cell.setCellValue(plan.stateExam.date)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, startRow, 23000, excelComponent.PAGE_POINTS_WIDTH)
        cell = row.createCell(cCell)
        cell.setCellValue(plan.stateExam.forma)
        cell.setCellStyle(excelComponent.centerBottomCellStyle)

        int cnt
        if (plan.practise.size() == 0){
            cnt = 1
        } else {
            cnt = plan.practise.size()
        }

        2 + cnt
    }
}
