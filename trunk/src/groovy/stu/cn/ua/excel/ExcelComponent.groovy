package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font

/**
 * author: evgeniy
 * date: 11.02.12
 */
class ExcelComponent {

    public static final int FIRST_COLUMN_WIDTH = 7000
    public static final int SECOND_COLUMN_WIDTH = 4000

    public static final int THIRD_BLOCK_CELL_COUNT = 7
    public static final int THIRD_BLOCK_CELL_WIDTH = 10 * 330 / THIRD_BLOCK_CELL_COUNT

    public static final int FOURTH_BLOCK_CELL_COUNT = 6
    public static final int FOURTH_BLOCK_CELL_WIDTH = 15 * 330 / FOURTH_BLOCK_CELL_COUNT

    public static final int LAST_BLOCK_CELL_WIDTH = 310
    public static final int LAST_TOTAL_BLOCK_CELL_WIDTH = 400

    public static final int COLUMN_COUNT = 58
    public static final int FONT_SIZE = 4

    public CellStyle centerCellStyle
    public CellStyle centerBottomCellStyle
    public CellStyle rightCellStyle
    public CellStyle leftCellStyle

    public CellStyle centerVerticalStyle

    def init(def workbook){
        centerCellStyle = workbook.createCellStyle()
        centerCellStyle.setAlignment(CellStyle.ALIGN_CENTER)
        centerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        centerBottomCellStyle = workbook.createCellStyle()
        centerBottomCellStyle.setAlignment(CellStyle.ALIGN_CENTER)

        rightCellStyle = workbook.createCellStyle()
        rightCellStyle.setAlignment(CellStyle.ALIGN_RIGHT)

        leftCellStyle = workbook.createCellStyle()
        leftCellStyle.setAlignment(CellStyle.ALIGN_LEFT)

        centerVerticalStyle = workbook.createCellStyle()
        centerVerticalStyle.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
        centerVerticalStyle.setAlignment(CellStyle.ALIGN_CENTER)
        centerVerticalStyle.setWrapText(true)

        // Create a new font and alter it.
        Font font = workbook.createFont();
        font.setFontHeightInPoints((Short) FONT_SIZE);
        font.setFontName("Courier New");

        rightCellStyle.setFont(font)
        centerCellStyle.setFont(font)
        leftCellStyle.setFont(font)
        centerVerticalStyle.setFont(font)
        centerBottomCellStyle.setFont(font)
    }
}