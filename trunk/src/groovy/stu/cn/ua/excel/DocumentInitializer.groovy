package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.Sheet

/**
 * author: evgeniy
 * date: 09.02.12
 */
class DocumentInitializer {

    def excelComponent

    def initColumnsWidth(Sheet sheet) {
        sheet.setColumnWidth(0, excelComponent.FIRST_COLUMN_WIDTH)
        sheet.setColumnWidth(1, excelComponent.SECOND_COLUMN_WIDTH)

        int pos = 2
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
            sheet.setColumnWidth(i, excelComponent.LAST_BLOCK_CELL_WIDTH)
        }
    }
}
