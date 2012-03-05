package stu.cn.ua.excel

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import decanat.grails.Plan
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.hssf.util.CellRangeAddress
import org.apache.poi.ss.usermodel.Sheet

/**
 * author: evgeniy
 * date: 11.02.12
 */
class ExcelComponent {

    private final int FIRST_COLUMN_WIDTH = 7000
    private final int SECOND_COLUMN_WIDTH = 4000

    public final int THIRD_BLOCK_CELL_COUNT = 7
    private final int THIRD_BLOCK_CELL_WIDTH = 10 * 330 / THIRD_BLOCK_CELL_COUNT

    public final int FOURTH_BLOCK_CELL_COUNT = 6
    private final int FOURTH_BLOCK_CELL_WIDTH = 15 * 330 / FOURTH_BLOCK_CELL_COUNT

    private final int LAST_BLOCK_CELL_WIDTH = 310
    private final int LAST_TOTAL_BLOCK_CELL_WIDTH = 400
    
    public static final int PAGE_POINTS_WIDTH = 32600


    public final int FONT_SIZE = 4

    public CellStyle centerCellStyle
    public CellStyle centerBottomCellStyle
    public CellStyle rightCellStyle
    public CellStyle leftCellStyle

    public CellStyle centerVerticalStyle

    private int columnCount
    private int firstColumnWidth
    private int secondColumnWidth
    private int thirdBlockCellWidth
    private int fourthBlockCellWidth
    private int lastBlockCellWidth
    private int lastTotalBlockCellWidth

    private Plan plan
    private Workbook workbook
    def cellWidthList = []

    ExcelComponent(Plan plan, Workbook workbook) {
        this.plan = plan
        this.workbook = workbook
        init()
    }

    private void init(){
        this.columnCount = 10 + 10 + THIRD_BLOCK_CELL_COUNT + FOURTH_BLOCK_CELL_COUNT + plan.semestrCount * 5
        double factor = getSemesterFactor()
        firstColumnWidth = FIRST_COLUMN_WIDTH/factor
        secondColumnWidth = SECOND_COLUMN_WIDTH/factor
        thirdBlockCellWidth = THIRD_BLOCK_CELL_WIDTH/factor
        fourthBlockCellWidth = FOURTH_BLOCK_CELL_WIDTH/factor
        lastBlockCellWidth = LAST_BLOCK_CELL_WIDTH/factor
        lastTotalBlockCellWidth = LAST_TOTAL_BLOCK_CELL_WIDTH/factor

        (1..10).each {
            cellWidthList.add(firstColumnWidth/10)
        }
        (1..10).each {
            cellWidthList.add(secondColumnWidth/10)
        }
        (1..7).each {
            cellWidthList.add(thirdBlockCellWidth)
        }
        (1..6).each {
            cellWidthList.add(fourthBlockCellWidth)
        }
        (1..plan.semestrCount).each {
            (1..4).each {
                cellWidthList.add(lastBlockCellWidth)
            }
            cellWidthList.add(lastTotalBlockCellWidth)
        }

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

    public int mergeCellsByCoordinates(Sheet sheet, int row, int from, int to){
        int cellWidthSum = 0
        int cellFromPosition = -1
        int cellToPosition = -1
        if (to >= PAGE_POINTS_WIDTH){
            cellToPosition = columnCount-1
        }
        if (from == 0){
            cellFromPosition = 0
        }
        for (int i=0; i<cellWidthList.size(); i++){
            if (from <= cellWidthSum && -1 == cellFromPosition){
                cellFromPosition = i
            }
            if (cellWidthSum > to && -1 == cellToPosition){
                cellToPosition = i-1
            }
            if (-1 != cellFromPosition && -1 != cellToPosition){
                break
            }
            cellWidthSum += cellWidthList.get(i)
        }
        sheet.addMergedRegion(new CellRangeAddress(row, row, cellFromPosition, cellToPosition));
        return cellFromPosition
    }

    int getColumnCount() {
        return columnCount
    }

    int getFirstColumnWidth() {
        return firstColumnWidth
    }

    int getSecondColumnWidth() {
        return secondColumnWidth
    }

    int getThirdBlockCellWidth() {
        return thirdBlockCellWidth
    }

    int getFourthBlockCellWidth() {
        return fourthBlockCellWidth
    }

    int getLastBlockCellWidth() {
        return lastBlockCellWidth
    }

    int getLastTotalBlockCellWidth() {
        return lastTotalBlockCellWidth
    }

    private double getSemesterFactor(){
        switch (plan.semestrCount){
            case 2:
                return 0.698
                break

            case 3:
                return 0.748
                break

            case 4:
                return 0.799
                break

            case 8:
                return 1
                break
        }
        throw new IllegalArgumentException("wrong semester count in plan: ${plan}, semesterCount: ${plan.semestrCount}")
    }
}
