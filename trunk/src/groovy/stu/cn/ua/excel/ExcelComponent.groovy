package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font

/**
 * author: evgeniy
 * date: 11.02.12
 */
class ExcelComponent {

    public FIRST_COLUMN_WIDTH = 7000
    public SECOND_COLUMN_WIDTH = 4000

    public THIRD_BLOCK_CELL_COUNT = 7
    public THIRD_BLOCK_CELL_WIDTH = 10 * 330 / THIRD_BLOCK_CELL_COUNT

    public FOURTH_BLOCK_CELL_COUNT = 6
    public FOURTH_BLOCK_CELL_WIDTH = 15 * 330 / FOURTH_BLOCK_CELL_COUNT

    public LAST_BLOCK_CELL_WIDTH = 330

    public COLUMN_COUNT = 55
    public FONT_SIZE = 4

    public CellStyle centerCellStyle
    public CellStyle rightCellStyle
    public CellStyle leftCellStyle

    def init(def workbook){
        centerCellStyle = workbook.createCellStyle()
        centerCellStyle.setAlignment(CellStyle.ALIGN_CENTER)

        rightCellStyle = workbook.createCellStyle()
        rightCellStyle.setAlignment(CellStyle.ALIGN_RIGHT)

        leftCellStyle = workbook.createCellStyle()
        leftCellStyle.setAlignment(CellStyle.ALIGN_LEFT)

        // Create a new font and alter it.
        Font font = workbook.createFont();
        font.setFontHeightInPoints((Short) FONT_SIZE);
        font.setFontName("Courier New");

        rightCellStyle.setFont(font)
        centerCellStyle.setFont(font)
        leftCellStyle.setFont(font)
    }
}
