package decanat.grails

import stu.cn.ua.dbf.reader.ChairDTOReader
import stu.cn.ua.dbf.enums.AllowedFiles

class DBFImportController {

    def index() {
        def errors = chainModel?.validationErrors
        def count = chainModel?.count

        if (errors){
            [validationErrors: errors]
        }
        else {
            [count: count]
        }
    }

    def upload = {
        def filename = request.getFile("dbf").fileItem.fileName.toLowerCase()
        def reader = null

        switch (filename){
            case AllowedFiles.KAFEDRA.filename:
                reader =  new ChairDTOReader()
                break
            default:
                flash.error = "Файл ${filename} не найден в списке допустимых файлов"
                redirect(action: 'index')
                return
        }

        def is = request.getFile("dbf").inputStream
        reader.read(is)
        def errors = reader.validate()
        if (!errors){
            Integer count = reader.save()
            chain(action: 'index', model: [count: count])
        } else {
            chain(action: 'index', model: [validationErrors: errors])
        }
    }
}
