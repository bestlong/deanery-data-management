package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.hssf.util.CellRangeAddress
import decanat.grails.Plan

/**
 * author: evgeniy
 * date: 09.02.12
 */
class HeadPrinter {

    /**
     *
     * @param sheet страница Excel в которую писать
     * @return номер строки на котрой закончилась запись
     */
    public int printHeader(Sheet sheet, Date date, Plan plan, ExcelComponent excelComponent) {
        int currentRow = 0;
        //ROW0
        Row row = sheet.createRow(currentRow)
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 9));
        Cell cell = row.createCell(0)
        cell.setCellValue("ЗАТВЕРДЖУЮ")
        cell.setCellStyle(excelComponent.centerCellStyle)

        int cCell = excelComponent.mergeCellsByCoordinates(sheet, currentRow, 10000, 20000)
        cell = row.createCell(cCell)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("Р О Б О Ч И Й   Н А В Ч А Л Ь Н Ы Й   П Л А Н")

        cCell = excelComponent.mergeCellsByCoordinates(sheet, currentRow, 20000, excelComponent.PAGE_POINTS_WIDTH)
        cell = row.createCell(cCell)
        cell.setCellStyle(excelComponent.rightCellStyle)
        cell.setCellValue("ЧЕРНІГІВСЬКИЙ ДЕРЖАВНИЙ ТЕХНОЛОГІЧНИЙ УНІВЕРСИТЕТ")

        currentRow++;
        //ROW1
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 9));
        row = sheet.createRow(currentRow)
        cCell = excelComponent.mergeCellsByCoordinates(sheet, currentRow, 10000, 20000)

        cell = row.createCell(cCell)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("на ${plan.startYear} - ${plan.endYear} навчальній рік за напрямом підготовки ${plan.direction}")

        cCell = excelComponent.mergeCellsByCoordinates(sheet, currentRow, 20000, excelComponent.PAGE_POINTS_WIDTH)
        cell = row.createCell(cCell)
        cell.setCellStyle(excelComponent.rightCellStyle)
        cell.setCellValue("ФАКУЛЬТЕТ ЕЛЕКТРОННИХ ТА ІНФОРМАЦІЙНИХ ТЕХНОЛОГІЙ")

        currentRow++;

        //ROW2
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 9));
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 10, 19));
        row = sheet.createRow(currentRow)
        cell = row.createCell(0)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("Перший проректор")
        cell = row.createCell(10)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue(plan.chair.faculty.prorektor)

        currentRow++;

        //ROW3
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 9));
        row = sheet.createRow(currentRow)
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 10, excelComponent.columnCount - 1));
        cell = row.createCell(10)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("""Спеціальність ${plan.speciality.specialityCode} - ${plan.speciality.name}, освітньо-кваліфікаційний рівень - ${plan.level},
кваліфікація ${plan.qualification}, термін навчання ${plan.termin}""")

        currentRow++;

//ROW4
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 9));
        row = sheet.createRow(currentRow)
        cCell = excelComponent.mergeCellsByCoordinates(sheet, currentRow, 12000, 20000)
        cell = row.createCell(cCell)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("форма навчання - ${plan.form}")

        cell = row.createCell(0)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("<___>____________________${date.year+1900}")

        return ++currentRow
    }
}
