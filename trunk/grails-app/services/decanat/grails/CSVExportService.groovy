package decanat.grails

class CSVExportService {

    OutputStreamWriter outFile;

    CSVExportService(def of) {
        outFile = of;
    }

    List<?> exportToCSVWorkPlan(def idPlan) {
        String s = new String("--workplan--;\n");
        s = s + "\"id\";\"chair_id\";\"direction\";\"start_year\";\"end_year\";\"form\";\"level\";\"qualification\";\"semestr_count\";\"speciality_id\";\"termin\";\"name\";\"plan_id\";\n";
        outFile.write(s);
        List<?> list = WorkPlan.findAllById(idPlan);
        for (Object object: list) {
            outFile.write(((WorkPlan) object).toCSV());
        }
        return list;
    }


    List<?> exportToCSVAllWorkPlan() {
        String s = new String("--workplan--;\n");
        s = s + "\"id\";\"chair_id\";\"direction\";\"start_year\";\"end_year\";\"form\";\"level\";\"qualification\";\"semestr_count\";\"speciality_id\";\"termin\";\"name\";\"plan_id\";\n";
        outFile.write(s);
        List<?> list = WorkPlan.findAll();
        for (Object object: list) {
            outFile.write(((WorkPlan) object).toCSV());
        }
        return list;
    }


    List<?> exportToCSVPlanSubject(def idPlan) {
        String s1 = new String("--plan_subject--;\n");
        s1 = s1 + "\"id\";\"credit_count\";\"lab_count\";\"lecture_count\";\"plan_id\";\"practice_count\";\"sam_count\";\"seminar_count\";\"subject_id\";\n";
        outFile.write(s1);
        Plan pq = Plan.findById(idPlan);
        if (pq == null) pq = WorkPlan.findById(idPlan);
        List<?> list1 = PlanSubject.findAllByPlan(pq);
        for (Object object: list1) {
            outFile.write(((PlanSubject) object).toCSV());
        }
        return list1;
    }


    List<?> exportToCSVAllPlanSubject() {
        String s1 = new String("--plan_subject--;\n");
        s1 = s1 + "\"id\";\"credit_count\";\"lab_count\";\"lecture_count\";\"plan_id\";\"practice_count\";\"sam_count\";\"seminar_count\";\"subject_id\";\n";
        outFile.write(s1);
        List<?> list1 = PlanSubject.findAll();
        for (Object object: list1) {
            outFile.write(((PlanSubject) object).toCSV());
        }
        return list1;
    }


    def exportToCSVPlanControlType(List<?> list1) {
        String s2 = new String("--plan_control_type--;\n");
        s2 = s2 + "\"id\";\"semestr\";\"plan_subject_id\";\"mask\";\n";
        outFile.write(s2);
        for (Object obj: list1) {
            List<?> list2 = PlanControlType.findAllByPlanSubject(obj);
            for (Object object: list2) {
                outFile.write(((PlanControlType) object).toCSV());
            }
        }
    }


    def exportToCSVAllPlanControlType() {
        String s2 = new String("--plan_control_type--;\n");
        s2 = s2 + "\"id\";\"semestr\";\"plan_subject_id\";\"mask\";\n";
        outFile.write(s2);
        List<?> list2 = PlanControlType.findAll();
        for (Object object: list2) {
            outFile.write(((PlanControlType) object).toCSV());
        }
    }

    def exportToCSVPlanHours(List<?> list1) {
        String s3 = new String("--plan_hours--;\n");
        s3 = s3 + "\"id\";\"lab_count\";\"lecture_count\";\"plan_subject_id\";\"practice_count\";\"semestr\";\"seminar_count\";\n";
        outFile.write(s3);
        for (Object obj: list1) {
            List<?> list3 = PlanHours.findAllByPlanSubject(obj);
            for (Object object: list3) {
                outFile.write(((PlanHours) object).toCSV());
            }
        }
    }


    def exportToCSVAllPlanHours() {
        String s3 = new String("--plan_hours--;\n");
        s3 = s3 + "\"id\";\"lab_count\";\"lecture_count\";\"plan_subject_id\";\"practice_count\";\"semestr\";\"seminar_count\";\n";
        outFile.write(s3);
        List<?> list3 = PlanHours.findAll();
        for (Object object: list3) {
            outFile.write(((PlanHours) object).toCSV());
        }
    }

    def exportToCSVPlanPractice(def idPlan) {
        String s4 = new String("--plan_practice--;\n");
        s4 = s4 + "\"id\";\"name\";\"plan_id\";\"semestr\";\"week_count\";\n";
        outFile.write(s4);
        Plan pq = Plan.findById(idPlan);
        if (pq == null) pq = WorkPlan.findById(idPlan);
        List<?> list4 = PlanPractice.findAllByPlan(pq);
        for (Object object: list4) {
            outFile.write(((PlanPractice) object).toCSV());
        }
    }

    def exportToCSVAllPlanPractice() {
        String s4 = new String("--plan_practice--;\n");
        s4 = s4 + "\"id\";\"name\";\"plan_id\";\"semestr\";\"week_count\";\n";
        outFile.write(s4);
        List<?> list4 = PlanPractice.findAll();
        for (Object object: list4) {
            outFile.write(((PlanPractice) object).toCSV());
        }
    }

    def exportToCSVPlanStateExam(def idPlan) {
        String s5 = new String("--plan_state_exam--;\n");
        s5 = s5 + "\"id\";\"date\";\"form\";\"plan_id\";\"semestr\";\n";
        outFile.write(s5);
        Plan pq = Plan.findById(idPlan);
        if (pq == null) pq = WorkPlan.findById(idPlan);
        List<?> list5 = PlanStateExam.findAllByPlan(pq);
        for (Object object: list5) {
            outFile.write(((PlanStateExam) object).toCSV());
        }
    }

    def exportToCSVAllPlanStateExam() {
        String s5 = new String("--plan_state_exam--;\n");
        s5 = s5 + "\"id\";\"date\";\"form\";\"plan_id\";\"semestr\";\n";
        outFile.write(s5);
        List<?> list5 = PlanStateExam.findAll();
        for (Object object: list5) {
            outFile.write(((PlanStateExam) object).toCSV());
        }
    }

    def exportToCSVPlan(def idPlan) {
        String s = new String("--plan--;\n");
        s = s + "\"id\";\"chair_id\";\"direction\";\"start_year\";\"end_year\";\"form\";\"level\";\"qualification\";\"semestr_count\";\"speciality_id\";\"termin\";\n";
        outFile.write(s);
        List<?> list = Plan.findAllById(idPlan);
        for (Object object: list) {
            outFile.write(((Plan) object).toCSV());
        }
    }

    def exportToCSVAllPlan() {
        String s = new String("--plan--;\n");
        s = s + "\"id\";\"chair_id\";\"direction\";\"start_year\";\"end_year\";\"form\";\"level\";\"qualification\";\"semestr_count\";\"speciality_id\";\"termin\";\n";
        outFile.write(s);
        Plan w = new Plan();
        List<?> list = Plan.findAll();
        for (Plan object: list) {
            if (object.getClass() == w.getClass())
                outFile.write(((Plan) object).toCSV());
        }
    }

    def exportToCSVSemestr(def idPlan) {
        String s = new String("--semestr--;\n");
        s = s + "\"id\";\"number\";\"plan_id\";\"week_count\";\n";
        outFile.write(s);
        Plan pq = Plan.findById(idPlan);
        if (pq == null) pq = WorkPlan.findById(idPlan);
        List<?> list5 = Semestr.findAllByPlan(pq);
        for (Object object: list5) {
            outFile.write(((Semestr) object).toCSV());
        }
    }

    def exportToCSVAllSemestr() {
        String s = new String("--semestr--;\n");
        s = s + "\"id\";\"number\";\"plan_id\";\"week_count\";\n";
        outFile.write(s);
        List<?> list5 = Semestr.findAll();
        for (Object object: list5) {
            outFile.write(((Semestr) object).toCSV());
        }
    }

    def exportToCSVAllSpeciality() {
        String s = new String("--speciality--;\n");
        s = s + "\"id\";\"code\";\"name\";\"reference_count\";\"short_name\";\"speciality_code\";\n";
        outFile.write(s);
        List<?> list5 = Speciality.findAll();
        for (Object object: list5) {
            outFile.write(((Speciality) object).toCSV());
        }
    }

    def exportToCSVAllSubject() {
        String s = new String("--subject--;\n");
        s = s + "\"id\";\"chair_id\";\"code\";\"name\";\"reference_count\";\"short_name\";\n";
        outFile.write(s);
        List<?> list5 = Subject.findAll();
        for (Object object: list5) {
            outFile.write(((Subject) object).toCSV());
        }
    }

    def exportToCSVAllChair() {
        String s = new String("--chair--;\n");
        s = s + "\"id\";\"code_chair\";\"head\";\"name\";\"reference_count\";\"short_name\";\n";
        outFile.write(s);
        List<?> list5 = Chair.findAll();
        for (Object object: list5) {
            outFile.write(((Chair) object).toCSV());
        }
    }

    def exportToCSVAllUniversity() {
        String s = new String("--university--;\n");
        s = s + "\"id\";\"dean\";\"prorektor\";\n";
        outFile.write(s);
        List<?> list5 = University.findAll();
        for (Object object: list5) {
            outFile.write(((University) object).toCSV());
        }
    }
}