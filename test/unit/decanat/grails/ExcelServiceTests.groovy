package decanat.grails

import grails.test.*
import org.junit.Test

class ExcelServiceTests extends GrailsUnitTestCase {

    def excelService  = new ExcelService()

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    @Test
    void testExport() {
        excelService.exportToExcel()
    }
}
