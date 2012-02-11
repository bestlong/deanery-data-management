package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.hssf.util.CellRangeAddress

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
    def printHeader(Sheet sheet) {
        int currentRow = 0;
        //ROW0
        Row row = sheet.createRow(currentRow)
        Cell cell = row.createCell(0)
        cell.setCellValue("ЗАТВЕРДЖУЮ")
        cell.setCellStyle(excelComponent.centerCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(
                currentRow, //first row (0-based)
                currentRow, //last row  (0-based)
                2, //first column (0-based)
                26  //last column  (0-based)
        ));

        cell = row.createCell(2)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("Р О Б О Ч И Й   Н А В Ч А Л Ь Н Ы Й   П Л А Н")

        sheet.addMergedRegion(new CellRangeAddress(
                currentRow, //first row (0-based)
                currentRow, //last row  (0-based)
                27, //first column (0-based)
                excelComponent.COLUMN_COUNT - 1  //last column  (0-based)
        ));
        cell = row.createCell(27)
        cell.setCellStyle(excelComponent.rightCellStyle)
        cell.setCellValue("ЧЕРНІГІВСЬКИЙ ДЕРЖАВНИЙ ТЕХНОЛОГІЧНИЙ УНІВЕРСИТЕТ")

        currentRow++;
        //ROW1
        row = sheet.createRow(currentRow)
        sheet.addMergedRegion(new CellRangeAddress(
                currentRow, //first row (0-based)
                currentRow, //last row  (0-based)
                2, //first column (0-based)
                26  //last column  (0-based)
        ));

        cell = row.createCell(2)
        cell.setCellStyle(excelComponent.centerCellStyle)
        cell.setCellValue("на 2010 - 2011 навчальній рік за напрямом підготовки компютерна інженерія")

        sheet.addMergedRegion(new CellRangeAddress(
                currentRow, //first row (0-based)
                currentRow, //last row  (0-based)
                27, //first column (0-based)
                excelComponent.COLUMN_COUNT - 1  //last column  (0-based)
        ));
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
        sheet.addMergedRegion(new CellRangeAddress(
                currentRow, //first row (0-based)
                currentRow, //last row  (0-based)
                1, //first column (0-based)
                excelComponent.COLUMN_COUNT - 1  //last column  (0-based)
        ));
        cell = row.createCell(1)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("""Спеціальність 6.050102 - Компютерна Інженерія, освітньо-кваліфікаційний рівень - бакалавр,
кваліфікація бакалавр компютерної інженерії, термін навчання 3 роки 10 місяців""")

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
        cell.setCellValue("форма навчання - денна")

        cell = row.createCell(0)
        cell.setCellStyle(excelComponent.leftCellStyle)
        cell.setCellValue("<___>____________________2010p")

        return ++currentRow
    }
}
