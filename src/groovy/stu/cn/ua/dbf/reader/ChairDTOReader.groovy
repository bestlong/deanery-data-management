package stu.cn.ua.dbf.reader

import stu.cn.ua.dbf.dto.ChairDTO
import decanat.grails.Chair

/**
 * author: evgeniy
 * date: 10.03.12
 */
class ChairDTOReader extends DBFAbstractReader<ChairDTO> {

    private List<ChairDTO> resultList = new ArrayList<ChairDTO>();

    @Override
    protected void add(ChairDTO d) {
        resultList.add(d)
    }

    @Override
    protected ChairDTO createDomain() {
        return new ChairDTO()
    }

    @Override
    List<String> validate() {
        def errors = []
        resultList.each {
            def result = Chair.validate(it)
            if (!result.success) {
                errors.add(result.messages)
            }
        }
        errors
    }

    @Override
    int save() {
        resultList.each {
            Chair.saveChair(it)
        }
        resultList.size()
    }
}
