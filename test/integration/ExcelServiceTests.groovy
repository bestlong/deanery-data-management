import grails.test.*
import org.junit.Test
import decanat.grails.Plan
import decanat.grails.Speciality

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
//        def speciality = new Speciality(kod: "sp_code", caption: "sp_name")
//        def plan = new Plan(level: "test_level", qualification: "test_qualification", termin: "test_termin", direction: "test_direction", form: "test_form", speciality: speciality)

        def plan = Plan.list().get(0)
        excelService.exportToExcel(plan, new Date())

    }
}
