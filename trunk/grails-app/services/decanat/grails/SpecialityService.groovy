package decanat.grails

class SpecialityService {

    static transactional = true

    def serviceMethod() {

    }

    List<Speciality> findSpecialities(String code, String name, String shortName) {

        def c = Speciality.createCriteria()

        def specialities = c.listDistinct {
            ilike("name", "%" + name + "%");
            ilike("kod", "%" + code + "%");
            if (!shortName.equals(""))
                ilike("shortName", "%" + shortName + "%");
        }

        return specialities;
    }
}
