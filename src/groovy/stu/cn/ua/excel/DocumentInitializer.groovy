package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet

/**
 * author: evgeniy
 * date: 09.02.12
 */
class DocumentInitializer {

    ExcelComponent excelComponent

    def initColumnsWidth(Sheet sheet) {
        (0..3).each {int i ->
            sheet.setColumnWidth(i, (excelComponent.FIRST_COLUMN_WIDTH/4).intValue())
        }
        sheet.setColumnWidth(4, excelComponent.SECOND_COLUMN_WIDTH)

        int pos = 5
        int lastPos = pos + excelComponent.THIRD_BLOCK_CELL_COUNT-1
        for (int i: pos..lastPos) {
            sheet.setColumnWidth(i, excelComponent.THIRD_BLOCK_CELL_WIDTH.intValue())
        }

        pos += excelComponent.THIRD_BLOCK_CELL_COUNT
        lastPos = pos + excelComponent.FOURTH_BLOCK_CELL_COUNT-1
        for (int i: pos..lastPos) {
            sheet.setColumnWidth(i, excelComponent.FOURTH_BLOCK_CELL_WIDTH.intValue())
        }

        pos += excelComponent.FOURTH_BLOCK_CELL_COUNT
        lastPos = excelComponent.COLUMN_COUNT-1
        for (int i: pos..lastPos) {
            if (i%5 == 3){
                sheet.setColumnWidth(i, excelComponent.LAST_TOTAL_BLOCK_CELL_WIDTH)
            } else {
                sheet.setColumnWidth(i, excelComponent.LAST_BLOCK_CELL_WIDTH)
            }
        }
    }

    void setExcelComponent(ExcelComponent excelComponent) {
        this.excelComponent = excelComponent
    }
}
