package decanat.grails

class SpecialityService {

    static transactional = true

    def serviceMethod() {

    }

    List<Speciality> findSpecialities(String code, String specialityCode, String name, String shortName) {

        def c = Speciality.createCriteria()

        def specialities = c.listDistinct {
            ilike("code", "%" + code + "%")
            ilike("name", "%" + name + "%")
            ilike("specialityCode", "%" + specialityCode + "%");
            if (!shortName.equals(""))
                ilike("shortName", "%" + shortName + "%");
        }

        return specialities;
    }
}
