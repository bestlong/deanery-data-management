

import grails.test.*
import org.junit.Test

class ExcelServiceTests extends GrailsUnitTestCase {

    def excelService

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
