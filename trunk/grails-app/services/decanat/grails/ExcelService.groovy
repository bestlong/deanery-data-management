package decanat.grails

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.DataFormat
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.hssf.usermodel.HSSFDataFormat
import org.apache.poi.hssf.util.CellRangeAddress

class ExcelService {

    private FIRST_COLUMN_WIDTH = 7000
    private SECOND_COLUMN_WIDTH = 4000
    private OTHER_COLUMNS_WIDTH = 370

    private COLUMN_COUNT = 56

    private Workbook workbook
    private CellStyle centerCellStyle
    private CellStyle rightCellStyle
    private CellStyle leftCellStyle

    static transactional = true

    def exportToExcel() {
        workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("new sheet");

        centerCellStyle = workbook.createCellStyle()
        centerCellStyle.setAlignment(CellStyle.ALIGN_CENTER)

        rightCellStyle = workbook.createCellStyle()
        rightCellStyle.setAlignment(CellStyle.ALIGN_RIGHT)

        leftCellStyle = workbook.createCellStyle()
        leftCellStyle.setAlignment(CellStyle.ALIGN_LEFT)

        // Create a new font and alter it.
        Font font = workbook.createFont();
        font.setFontHeightInPoints((Short)4);
        font.setFontName("Courier New");

        initColumnsWidth(sheet)
        printHeader(sheet)

        rightCellStyle.setFont(font)
        centerCellStyle.setFont(font)
        leftCellStyle.setFont(font)

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();
    }

    private void initColumnsWidth(Sheet sheet){
        sheet.setColumnWidth(0, FIRST_COLUMN_WIDTH)
        sheet.setColumnWidth(1, SECOND_COLUMN_WIDTH)

        for (int i:2..COLUMN_COUNT-1){
            sheet.setColumnWidth(i, OTHER_COLUMNS_WIDTH)
        }
    }

    private void printHeader(Sheet sheet){

        Row row = sheet.createRow(0)
        Cell cell = row.createCell(0)
        cell.setCellValue("ЗАТВЕРДЖУЮ")
        cell.setCellStyle(centerCellStyle)

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row  (0-based)
                2, //first column (0-based)
                26  //last column  (0-based)
        ));

        cell = row.createCell(2)
        cell.setCellStyle(centerCellStyle)
        cell.setCellValue("Р О Б О Ч И Й   Н А В Ч А Л Ь Н Ы Й   П Л А Н")

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row  (0-based)
                27, //first column (0-based)
                COLUMN_COUNT-1  //last column  (0-based)
        ));
        cell = row.createCell(27)
        cell.setCellStyle(rightCellStyle)
        cell.setCellValue("ЧЕРНІГІВСЬКИЙ ДЕРЖАВНИЙ ТЕХНОЛОГІЧНИЙ УНІВЕРСИТЕТ")

        row = sheet.createRow(1)
        sheet.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                1, //last row  (0-based)
                2, //first column (0-based)
                26  //last column  (0-based)
        ));

        cell = row.createCell(2)
        cell.setCellStyle(centerCellStyle)
        cell.setCellValue("на 2010 - 2011 навчальній рік за напрямом підготовки компютерна інженерія")

        sheet.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                1, //last row  (0-based)
                27, //first column (0-based)
                COLUMN_COUNT-1  //last column  (0-based)
        ));
        cell = row.createCell(27)
        cell.setCellStyle(rightCellStyle)
        cell.setCellValue("ФАКУЛЬТЕТ ЕЛЕКТРОННИХ ТА ІНФОРМАЦІЙНИХ ТЕХНОЛОГІЙ")




        //ROW3

        row = sheet.createRow(2)

        cell = row.createCell(0)
        cell.setCellStyle(leftCellStyle)
        cell.setCellValue("Перший проректор")
        cell = row.createCell(1)
        cell.setCellStyle(leftCellStyle)
        cell.setCellValue("В. І. Скоробогатова")

        //ROW3
        row = sheet.createRow(3)
        sheet.addMergedRegion(new CellRangeAddress(
                3, //first row (0-based)
                3, //last row  (0-based)
                1, //first column (0-based)
                COLUMN_COUNT-1  //last column  (0-based)
        ));
        cell = row.createCell(1)
        cell.setCellStyle(leftCellStyle)
        cell.setCellValue("Спеціальність 6.050102 - Компютерна Інженерія, освітньо-кваліфікаційний рівень - бакалавр, кваліфікація бакалавр компютерної інженерії, термін навчання 3 роки 10 місяців")


//ROW4
        row = sheet.createRow(4)
        sheet.addMergedRegion(new CellRangeAddress(
                4, //first row (0-based)
                4, //last row  (0-based)
                7, //first column (0-based)
                13  //last column  (0-based)
        ));
        cell = row.createCell(7)
        cell.setCellStyle(centerCellStyle)
        cell.setCellValue("форма навчання - денна")

        cell = row.createCell(0)
        cell.setCellStyle(leftCellStyle)
        cell.setCellValue("<___>____________________2010p")
    }
}
