package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell

/**
 * author: evgeniy
 * date: 11.02.12
 */
class SubjectPrinter {

    def excelComponent

    def printSubjects(Sheet sheet, int sRow){
        def startRow = sRow
        for (int i:1..50){
            Row row = sheet.createRow(startRow);

            Cell cell = row.createCell(0)
            cell.setCellValue("Автоматизована підготовка технічної документаціі")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(1)
            cell.setCellValue("основ конструювання машин")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(2)
            cell.setCellValue("9.5")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(3)
            cell.setCellValue("342")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(4)
            cell.setCellValue("105")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(5)
            cell.setCellValue("___")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(6)
            cell.setCellValue("___")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(7)
            cell.setCellValue("___")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(8)
            cell.setCellValue("___")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(9)
            cell.setCellValue("12345")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(10)
            cell.setCellValue("12345")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(11)
            cell.setCellValue("12345")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(12)
            cell.setCellValue("123456")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(13)
            cell.setCellValue("1234567")
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(14)
            cell.setCellValue("12")
            cell.setCellStyle(excelComponent.leftCellStyle)

            def cCol = 15

            for (int j:1..8){
                cell = row.createCell(cCol)
                cell.setCellValue("6")
                cell.setCellStyle(excelComponent.leftCellStyle)

                cell = row.createCell(cCol+1)
                cell.setCellValue("6")
                cell.setCellStyle(excelComponent.leftCellStyle)

                cell = row.createCell(cCol+2)
                cell.setCellValue("6")
                cell.setCellStyle(excelComponent.leftCellStyle)

                cell = row.createCell(cCol+3)
                cell.setCellValue("6")
                cell.setCellStyle(excelComponent.leftCellStyle)

                cell = row.createCell(cCol+4)
                cell.setCellValue("7")
                cell.setCellStyle(excelComponent.leftCellStyle)

                cCol+=5
            }

            startRow++
        }
    }
}
