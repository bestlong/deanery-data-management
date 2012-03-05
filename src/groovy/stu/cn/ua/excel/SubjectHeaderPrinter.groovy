package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.hssf.util.CellRangeAddress
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import decanat.grails.Plan
import decanat.grails.Semestr

/**
 * author: evgeniy
 * date: 11.02.12
 */
class SubjectHeaderPrinter {

    def work =["л\nе\nк\nц\nі\nі", "с\nе\nм\nн\nа\nр", "п\nр\nа\nк\nт\nч", "л\nа\nб\nо\nр\nт", "с\nа\nм\nо\nс\nт"]
    def check =["і\nс\nп\nи\nт\nи", "з\nа\nл\nі\nк\nи", "к\n.\nп\nр\nо\nе\nк\nт", "к\n.\nр\nо\nб\nо\nт\nи", "р\nг\nр", "к\nо\nн\nт\n.\nр\nо\nб"]
    def distribution =["у\nс\nь\nо\nг\nо", "л\nе\nк\nц\nі\nі", "с\nе\nм\nн\nа\nр", "п\nр\nа\nк\nт\nч", "л\nа\nб\nо\nр\nт"]

    def printSubjectHeader(Sheet sheet, int sRow, ExcelComponent excelComponent, Plan plan) {

        int startRow = sRow
        sheet.addMergedRegion(new CellRangeAddress(
                startRow, //first row (0-based)
                startRow, //last row  (0-based)
                21, //first column (0-based)
                26  //last column  (0-based)
        ));
        Row row1 = sheet.createRow(startRow)
        Cell cell = row1.createCell(21)
        cell.setCellValue("Годин")
        cell.setCellStyle(excelComponent.centerCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(
                startRow, //first row (0-based)
                startRow, //last row  (0-based)
                27, //first column (0-based)
                32  //last column  (0-based)
        ));
        cell = row1.createCell(27)
        cell.setCellValue("Розподіл між семестрами")
        cell.setCellStyle(excelComponent.centerCellStyle)

        int cCol = 33
        for (int i: 1..plan.semestrCount) {
            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, cCol, cCol+4));
            cell = row1.createCell(cCol)
            cell.setCellValue("${i} семестр")
            cell.setCellStyle(excelComponent.centerCellStyle)

            cCol += 5
        }
        startRow++;
        //row2

        sheet.addMergedRegion(new CellRangeAddress(
                startRow, //first row (0-based)
                startRow, //last row  (0-based)
                22, //first column (0-based)
                26  //last column  (0-based)
        ));
        def row2 = sheet.createRow(startRow)
        cell = row2.createCell(22)
        cell.setCellValue("у тому числі")
        cell.setCellStyle(excelComponent.centerCellStyle)

        cCol = 33
        for (Semestr semestr: plan.semestr) {
            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, cCol, cCol+4));
            cell = row2.createCell(cCol)
            cell.setCellValue("${semestr.weekCount} тижнів")
            cell.setCellStyle(excelComponent.centerCellStyle)

            cCol += 5
        }

        cCol=20

        //вертикальный текст
        startRow--;

        //кредитов
        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow+4, cCol, cCol));
        cell = row1.createCell(cCol)
        cell.setCellValue("к\nр\nе\nд\nи\nт\nі\nв")
        cell.setCellStyle(excelComponent.centerVerticalStyle)

        //усього
        cCol++
        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+4, cCol, cCol));
        cell = row2.createCell(cCol)
        cell.setCellValue("у\nс\nь\nо\nг\nо")
        cell.setCellStyle(excelComponent.centerVerticalStyle)

        def row3 = sheet.createRow(startRow+2)
        for (String str: work){
            cCol++
            sheet.addMergedRegion(new CellRangeAddress(startRow+2, startRow+4, cCol, cCol));
            cell = row3.createCell(cCol)
            cell.setCellValue(str.toString())
            cell.setCellStyle(excelComponent.centerVerticalStyle)
        }

        for (String str: check){
            cCol++
            sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+4, cCol, cCol));
            cell = row2.createCell(cCol)
            cell.setCellValue(str.toString())
            cell.setCellStyle(excelComponent.centerVerticalStyle)
        }

        for (int i:1..plan.semestrCount){
            for (String str: distribution){
                cCol++
                sheet.addMergedRegion(new CellRangeAddress(startRow+2, startRow+4, cCol, cCol));
                cell = row3.createCell(cCol)
                cell.setCellValue(str.toString())
                cell.setCellStyle(excelComponent.centerVerticalStyle)
            }
        }

        sheet.addMergedRegion(new CellRangeAddress(sRow, sRow+4, 0, 9));
        cell = row1.createCell(0)
        cell.setCellValue("Назва дисципліни")
        cell.setCellStyle(excelComponent.centerCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(sRow, sRow+4, 10, 19));
        cell = row1.createCell(10)
        cell.setCellValue("Кафедра")
        cell.setCellStyle(excelComponent.centerCellStyle)
    }
}
