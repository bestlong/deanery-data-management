package decanat.grails



import org.junit.*
import grails.test.mixin.*

@TestFor(FacultyController)
@Mock(Faculty)
class FacultyControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/faculty/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.facultyInstanceList.size() == 0
        assert model.facultyInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.facultyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.facultyInstance != null
        assert view == '/faculty/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/faculty/show/1'
        assert controller.flash.message != null
        assert Faculty.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/faculty/list'


        populateValidParams(params)
        def faculty = new Faculty(params)

        assert faculty.save() != null

        params.id = faculty.id

        def model = controller.show()

        assert model.facultyInstance == faculty
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/faculty/list'


        populateValidParams(params)
        def faculty = new Faculty(params)

        assert faculty.save() != null

        params.id = faculty.id

        def model = controller.edit()

        assert model.facultyInstance == faculty
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/faculty/list'

        response.reset()


        populateValidParams(params)
        def faculty = new Faculty(params)

        assert faculty.save() != null

        // test invalid parameters in update
        params.id = faculty.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/faculty/edit"
        assert model.facultyInstance != null

        faculty.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/faculty/show/$faculty.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        faculty.clearErrors()

        populateValidParams(params)
        params.id = faculty.id
        params.version = -1
        controller.update()

        assert view == "/faculty/edit"
        assert model.facultyInstance != null
        assert model.facultyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/faculty/list'

        response.reset()

        populateValidParams(params)
        def faculty = new Faculty(params)

        assert faculty.save() != null
        assert Faculty.count() == 1

        params.id = faculty.id

        controller.delete()

        assert Faculty.count() == 0
        assert Faculty.get(faculty.id) == null
        assert response.redirectedUrl == '/faculty/list'
    }
}
