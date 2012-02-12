package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.hssf.util.CellRangeAddress
import decanat.grails.Plan
import stu.cn.ua.enums.WorkTypeEnum

/**
 * author: evgeniy
 * date: 12.02.12
 */
class SubjectFooterPrinter {

    private ExcelComponent excelComponent

    def printFooter(Sheet sheet, int startRow, Plan plan){
        def row1 = sheet.createRow(startRow)
        def row2 = sheet.createRow(startRow+1)
        def cCol = 1
        def cell = row1.createCell(1)
        cell.setCellValue("Усього")
        cell.setCellStyle(excelComponent.rightCellStyle)
        
        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 2, 3));
        cell = row1.createCell(2)
        cell.setCellValue(plan.getCreditCountTotal())
        cell.setCellStyle(excelComponent.leftCellStyle)

        cell = row2.createCell(2)
        cell.setCellValue(plan.getTotal())
        cell.setCellStyle(excelComponent.rightCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 4, 5));
        cell = row1.createCell(4)
        cell.setCellValue(plan.getTotalLecturesCount())
        cell.setCellStyle(excelComponent.leftCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, 4, 5));
        cell = row2.createCell(4)
        cell.setCellValue(plan.getTotalSeminarCount())
        cell.setCellStyle(excelComponent.rightCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 6, 7));
        cell = row1.createCell(6)
        cell.setCellValue(plan.getTotalPractiseCount())
        cell.setCellStyle(excelComponent.leftCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, 6, 7));
        cell = row2.createCell(6)
        cell.setCellValue(plan.getTotalLabCount())
        cell.setCellStyle(excelComponent.rightCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 8, 9));
        cell = row1.createCell(8)
        cell.setCellValue(plan.getTotalSamCount())
        cell.setCellStyle(excelComponent.leftCellStyle)

        cCol = 15

        for (int i:1..plan.semestrCount){
            cell = row1.createCell(cCol)
            cell.setCellValue(plan.getTotalSemestr(i))
            cell.setCellStyle(excelComponent.leftCellStyle)

            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, cCol+1, cCol+2));
            cell = row1.createCell(cCol+1)
            cell.setCellValue(plan.getTotalSemestr(i, WorkTypeEnum.LECTURE))
            cell.setCellStyle(excelComponent.leftCellStyle)

            sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, cCol+1, cCol+2));
            cell = row2.createCell(cCol+1)
            cell.setCellValue(plan.getTotalSemestr(i, WorkTypeEnum.SEMINAR))
            cell.setCellStyle(excelComponent.rightCellStyle)

            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, cCol+3, cCol+4));
            cell = row1.createCell(cCol+3)
            cell.setCellValue(plan.getTotalSemestr(i, WorkTypeEnum.PRACTISE))
            cell.setCellStyle(excelComponent.leftCellStyle)

            sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, cCol+3, cCol+4));
            cell = row2.createCell(cCol+3)
            cell.setCellValue(plan.getTotalSemestr(i, WorkTypeEnum.LAB))
            cell.setCellStyle(excelComponent.rightCellStyle)

            cCol+=5
        }
    }

    void setExcelComponent(ExcelComponent excelComponent) {
        this.excelComponent = excelComponent
    }
}
