package decanat.grails



import org.junit.*
import grails.test.mixin.*

@TestFor(DeaneryController)
@Mock(Deanery)
class DeaneryControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/deanery/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.deaneryInstanceList.size() == 0
        assert model.deaneryInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.deaneryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.deaneryInstance != null
        assert view == '/deanery/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/deanery/show/1'
        assert controller.flash.message != null
        assert Deanery.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/deanery/list'


        populateValidParams(params)
        def deanery = new Deanery(params)

        assert deanery.save() != null

        params.id = deanery.id

        def model = controller.show()

        assert model.deaneryInstance == deanery
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/deanery/list'


        populateValidParams(params)
        def deanery = new Deanery(params)

        assert deanery.save() != null

        params.id = deanery.id

        def model = controller.edit()

        assert model.deaneryInstance == deanery
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/deanery/list'

        response.reset()


        populateValidParams(params)
        def deanery = new Deanery(params)

        assert deanery.save() != null

        // test invalid parameters in update
        params.id = deanery.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/deanery/edit"
        assert model.deaneryInstance != null

        deanery.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/deanery/show/$deanery.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        deanery.clearErrors()

        populateValidParams(params)
        params.id = deanery.id
        params.version = -1
        controller.update()

        assert view == "/deanery/edit"
        assert model.deaneryInstance != null
        assert model.deaneryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/deanery/list'

        response.reset()

        populateValidParams(params)
        def deanery = new Deanery(params)

        assert deanery.save() != null
        assert Deanery.count() == 1

        params.id = deanery.id

        controller.delete()

        assert Deanery.count() == 0
        assert Deanery.get(deanery.id) == null
        assert response.redirectedUrl == '/deanery/list'
    }
}
