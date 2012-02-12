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

    def excelComponent

    /**
     *
     * @param sheet страница Excel в которую писать
     * @return номер строки на котрой закончилась запись
     */
    def printHeader(Sheet sheet, Date date, Plan plan) {
        int currentRow = 0;
        //ROW0
        Row row = sheet.createRow(currentRow)
        Cell cell = row.createCell(0)
        cell.setCellValue("ЗАТВЕРДЖУЮ")
        cell.setCellStyle(excelComponent.centerCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 2, 26));

        cell = row.createCell(2)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("Р О Б О Ч И Й   Н А В Ч А Л Ь Н Ы Й   П Л А Н")

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 27, excelComponent.COLUMN_COUNT - 1));
        cell = row.createCell(27)
        cell.setCellStyle(excelComponent.rightCellStyle)
        cell.setCellValue("ЧЕРНІГІВСЬКИЙ ДЕРЖАВНИЙ ТЕХНОЛОГІЧНИЙ УНІВЕРСИТЕТ")

        currentRow++;
        //ROW1
        row = sheet.createRow(currentRow)
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 2, 26));

        cell = row.createCell(2)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("на ${date.year} - ${date.year+1} навчальній рік за напрямом підготовки ${plan.direction}")

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 27, excelComponent.COLUMN_COUNT - 1));
        cell = row.createCell(27)
        cell.setCellStyle(excelComponent.rightCellStyle)
        cell.setCellValue("ФАКУЛЬТЕТ ЕЛЕКТРОННИХ ТА ІНФОРМАЦІЙНИХ ТЕХНОЛОГІЙ")

        currentRow++;

        //ROW2
        row = sheet.createRow(currentRow)
        cell = row.createCell(0)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("Перший проректор")
        cell = row.createCell(1)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("В. І. Скоробогатова")

        currentRow++;

        //ROW3
        row = sheet.createRow(currentRow)
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 1, excelComponent.COLUMN_COUNT - 1));
        cell = row.createCell(1)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("""Спеціальність ${plan.speciality.kod} - ${plan.speciality.name}, освітньо-кваліфікаційний рівень - ${plan.level},
кваліфікація ${plan.qualification}, термін навчання ${plan.termin}""")

        currentRow++;

//ROW4
        row = sheet.createRow(currentRow)
        sheet.addMergedRegion(new CellRangeAddress(
                currentRow, //first row (0-based)
                currentRow, //last row  (0-based)
                7, //first column (0-based)
                13  //last column  (0-based)
        ));
        cell = row.createCell(7)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("форма навчання - ${plan.form}")

        cell = row.createCell(0)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("<___>____________________2010p")

        return ++currentRow
    }
}