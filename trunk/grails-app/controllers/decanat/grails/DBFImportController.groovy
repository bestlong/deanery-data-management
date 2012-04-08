package decanat.grails

import stu.cn.ua.dbf.reader.ChairDTOReader
import stu.cn.ua.dbf.enums.AllowedFiles
import stu.cn.ua.dbf.reader.SubjectDTOReader
import stu.cn.ua.dbf.reader.SpecialityPlanDTOReader
import decanat.grails.domain.User

class DBFImportController {

    def springSecurityService

    def index() {
        def errors = chainModel?.validationErrors
        if (errors){
            [validationErrors: errors, active: 4]
        }
        [active: 4]
    }

    def upload = {
        def filename = request.getFile("dbf").fileItem.fileName.toLowerCase()
        def reader = null

        switch (filename){
            case AllowedFiles.KAFEDRA.filename:
                reader =  new ChairDTOReader()
                break
            case AllowedFiles.DIS.filename:
                reader = new SubjectDTOReader()
                break
            case AllowedFiles.SPEC.filename:
                reader = new SpecialityPlanDTOReader()
                break
            default:
                flash.error = "Файл ${filename} не найден в списке допустимых файлов"
                redirect(action: 'index')
                return
        }
        reader.currentUser = User.get(springSecurityService.principal.id)
        def is = request.getFile("dbf").inputStream
        reader.read(is)
        def errors = reader.validate()
        if (!errors){
            Integer count = reader.save()
            flash.message = "В результате импорта удачно сохранено ${count} записей"
            redirect(action: 'index')
        } else {
            chain(action: 'index', model: [validationErrors: errors])
        }
    }
}
