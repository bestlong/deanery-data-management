package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import decanat.grails.Plan
import decanat.grails.PlanSubject
import stu.cn.ua.enums.ControlTypeEnum
import stu.cn.ua.enums.WorkTypeEnum

/**
 * author: evgeniy
 * date: 11.02.12
 */
class SubjectPrinter {

    private ExcelComponent excelComponent

    def printSubjects(Sheet sheet, int sRow, Plan plan){
        def startRow = sRow
        def subjects = plan.subjects
        for (PlanSubject planSubject: subjects){
            Row row = sheet.createRow(startRow);

            Cell cell = row.createCell(0)
            cell.setCellValue(planSubject.subject.name)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(1)
            cell.setCellValue(planSubject.subject.chair.name)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(2)
            cell.setCellValue(planSubject.creditCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(3)
            cell.setCellValue(planSubject.getTotal())
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(4)
            cell.setCellValue(planSubject.lectureCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(5)
            cell.setCellValue(planSubject.seminarCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(6)
            cell.setCellValue(planSubject.practiceCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(7)
            cell.setCellValue(planSubject.labCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            cell = row.createCell(8)
            cell.setCellValue(planSubject.samCount)
            cell.setCellStyle(excelComponent.centerBottomCellStyle)

            def cCol =9

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
//
//
//                cell = row.createCell(cCol+1)
//                cell.setCellValue("6")
//                cell.setCellStyle(excelComponent.leftCellStyle)
//
//                cell = row.createCell(cCol+2)
//                cell.setCellValue("6")
//                cell.setCellStyle(excelComponent.leftCellStyle)
//
//                cell = row.createCell(cCol+3)
//                cell.setCellValue("6")
//                cell.setCellStyle(excelComponent.leftCellStyle)
//
//                cell = row.createCell(cCol+4)
//                cell.setCellValue("7")
//                cell.setCellStyle(excelComponent.leftCellStyle)
//
//                cCol+=5
            }
            startRow++
        }
    }

    void setExcelComponent(ExcelComponent excelComponent) {
        this.excelComponent = excelComponent
    }


}
