package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import decanat.grails.Plan

import java.text.SimpleDateFormat
import java.text.DateFormat
import decanat.grails.University

/**
 * author: evgeniy
 * date: 14.02.12
 */
class FooterPrinter {

    public void print(Plan plan, int sRow, Sheet sheet, Date date, ExcelComponent excelComponent){
        def university = University.findAll().get(0)

        DateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy");
        def row = sheet.createRow(sRow)
        int cCell = excelComponent.mergeCellsByCoordinates(sheet, sRow, 1000, 3000)
        def cell = row.createCell(cCell)
        cell.setCellValue(dateFormat.format(date))
        cell.setCellStyle(excelComponent.leftCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, sRow, 5000, 7000)
        cell = row.createCell(cCell)
        cell.setCellValue("Декан")
        cell.setCellStyle(excelComponent.leftCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, sRow, 9000, 12000)
        cell = row.createCell(cCell)
        cell.setCellValue(university.dean)
        cell.setCellStyle(excelComponent.leftCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, sRow, 20000, 24000)
        cell = row.createCell(cCell)
        cell.setCellValue("зав. кафедрою")
        cell.setCellStyle(excelComponent.leftCellStyle)

        cCell = excelComponent.mergeCellsByCoordinates(sheet, sRow, 27000, 30000)
        cell = row.createCell(cCell)
        cell.setCellValue(plan.chair.head)
        cell.setCellStyle(excelComponent.leftCellStyle)
    }
}
