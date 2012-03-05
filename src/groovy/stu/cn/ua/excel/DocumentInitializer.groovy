package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet
import decanat.grails.Plan

/**
 * author: evgeniy
 * date: 09.02.12
 */
class DocumentInitializer {

    def initColumnsWidth(Sheet sheet, Plan plan, ExcelComponent excelComponent) {
        (0..9).each {int i ->
            sheet.setColumnWidth(i, (excelComponent.firstColumnWidth/10).intValue())
        }
        (10..19).each {int i ->
            sheet.setColumnWidth(i, (excelComponent.secondColumnWidth/10).intValue())
        }

        int pos = 20
        int lastPos = pos + excelComponent.THIRD_BLOCK_CELL_COUNT-1
        for (int i: pos..lastPos) {
            sheet.setColumnWidth(i, excelComponent.thirdBlockCellWidth.intValue())
        }

        pos += excelComponent.THIRD_BLOCK_CELL_COUNT
        lastPos = pos + excelComponent.FOURTH_BLOCK_CELL_COUNT-1
        for (int i: pos..lastPos) {
            sheet.setColumnWidth(i, excelComponent.fourthBlockCellWidth.intValue())
        }

        pos += excelComponent.FOURTH_BLOCK_CELL_COUNT
        lastPos = excelComponent.columnCount-1
        for (int i: pos..lastPos) {
            if (i%5 == 3){
                sheet.setColumnWidth(i, excelComponent.lastTotalBlockCellWidth)
            } else {
                sheet.setColumnWidth(i, excelComponent.lastBlockCellWidth)
            }
        }

    }
}
