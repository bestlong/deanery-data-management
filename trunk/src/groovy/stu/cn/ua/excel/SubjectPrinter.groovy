package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import decanat.grails.Plan
import decanat.grails.PlanSubject
import stu.cn.ua.enums.ControlTypeEnum
import stu.cn.ua.enums.WorkTypeEnum
import org.apache.poi.hssf.util.CellRangeAddress

/**
 * author: evgeniy
 * date: 11.02.12
 */
class SubjectPrinter {

    def printSubjects(Sheet sheet, int sRow, Plan plan, ExcelComponent excelComponent){
        def startRow = sRow
        def subjects = plan.subjects
        for (PlanSubject planSubject: subjects){
            Row row = sheet.createRow(startRow);

            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 0, 9));
            sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 10, 19));

            Cell cell = row.createCell(0)

            cell.setCellValue(planSubject.subject.name)
            cell.setCellStyle(excelComponent.leftWrapTextCellStyle)

            cell = row.createCell(10)
            cell.setCellValue(planSubject.subject.chair.name)
            cell.setCellStyle(excelComponent.leftWrapTextCellStyle)

            cell = row.createCell(20)
            cell.setCellValue(planSubject.creditCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(21)
            cell.setCellValue(planSubject.getTotal())
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(22)
            cell.setCellValue(planSubject.lectureCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(23)
            cell.setCellValue(planSubject.seminarCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(24)
            cell.setCellValue(planSubject.practiceCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(25)
            cell.setCellValue(planSubject.labCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(26)
            cell.setCellValue(planSubject.samCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            def cCol =27

            ControlTypeEnum.values().each {
                cell = row.createCell(cCol)
                cell.setCellValue(planSubject.getControlType(it))
                cell.setCellStyle(excelComponent.centerBottomCellStyle)
                cCol++
            }

            for (int j:1..plan.semestrCount){
                cell = row.createCell(cCol)
                cell.setCellValue(planSubject.getHourCount(j) == 0 ? "_" : planSubject.getHourCount(j))
                cell.setCellStyle(excelComponent.centerBottomCellStyle)
                cCol++

                WorkTypeEnum.values().each {
                    cell = row.createCell(cCol)
                    cell.setCellValue(planSubject.getHourCount(j, it) == 0 ? "_" : planSubject.getHourCount(j, it))
                    cell.setCellStyle(excelComponent.centerBottomCellStyle)
                    cCol++
                }
            }
            startRow++
        }
        return subjects.size()
    }
}
