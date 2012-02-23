package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.hssf.util.CellRangeAddress
import decanat.grails.Plan
import stu.cn.ua.enums.WorkTypeEnum
import stu.cn.ua.enums.ControlTypeEnum

/**
 * author: evgeniy
 * date: 12.02.12
 */
class SubjectFooterPrinter {

    private ExcelComponent excelComponent

    def printFooter(Sheet sheet, int startRow, Plan plan){
        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 0, 3));
        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, 0, 3));
        def row1 = sheet.createRow(startRow)
        def row2 = sheet.createRow(startRow+1)
        def cCol = 4
        def cell = row1.createCell(cCol)
        cell.setCellValue("Усього")
        cell.setCellStyle(excelComponent.rightCellStyle)
        
        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, 5, 6));
        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 5, 6));
        cell = row1.createCell(5)
        cell.setCellValue(plan.getCreditCountTotal())
        cell.setCellStyle(excelComponent.leftCellStyle)

        cell = row2.createCell(5)
        cell.setCellValue(plan.getTotal())
        cell.setCellStyle(excelComponent.rightCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 7, 8));
        cell = row1.createCell(7)
        cell.setCellValue(plan.getTotalLecturesCount())
        cell.setCellStyle(excelComponent.leftCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, 7, 8));
        cell = row2.createCell(7)
        cell.setCellValue(plan.getTotalSeminarCount())
        cell.setCellStyle(excelComponent.rightCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 9, 10));
        cell = row1.createCell(9)
        cell.setCellValue(plan.getTotalPractiseCount())
        cell.setCellStyle(excelComponent.leftCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, 9, 10));
        cell = row2.createCell(9)
        cell.setCellValue(plan.getTotalLabCount())
        cell.setCellStyle(excelComponent.rightCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 11, 12));
        cell = row1.createCell(11)
        cell.setCellValue(plan.getTotalSamCount())
        cell.setCellStyle(excelComponent.leftCellStyle)

        cCol = 18

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

        startRow +=2
        
        def col = 12
        
        ControlTypeEnum.values().each {
            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 0, 3));
            def row = sheet.createRow(startRow)
            cell = row.createCell(4)
            cell.setCellValue(it.caption)
            cell.setCellStyle(excelComponent.leftCellStyle)

            cell = row.createCell(col)
            cell.setCellValue(plan.getTotal(it))
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cCol = 18

            for (int j:1..plan.semestrCount){
                cell = row.createCell(cCol)
                cell.setCellValue(plan.getControlTypeCount(it, j))
                cell.setCellStyle(excelComponent.centerBottomCellStyle)
                cCol+=5
            }

            col++
            startRow++
        }


    }

    void setExcelComponent(ExcelComponent excelComponent) {
        this.excelComponent = excelComponent
    }
}