public class Main {

    public static void main(String[] args){
        Student.EducationForm educationForm = Student.EducationForm.BUDGET;
        Student.StudentStatus status = Student.StudentStatus.STUDYING;

        Student student = new Student("3545", "Flomboim", "Oleksandra",
                "Oleksiyvna", "29.07.2008", "sflomboim@gmail.com",
                "+380955941312", "28590285", 1, "1", 2025,
                educationForm, status);
        System.out.println(student);

        Teacher.AcademicDegree academicDegree = Teacher.AcademicDegree.DOCTOR;
        Teacher.AcademicTitle academicTitle = Teacher.AcademicTitle.PROFESSOR;

        Teacher teacher = new Teacher("3545", "Flomboim", "Oleksandra",
                "Oleksiyvna", "29.07.2008", "sflomboim@gmail.com",
                "+380955941312", "Something", academicDegree, academicTitle,
                "25.05.2000", 300.0);
        System.out.println(teacher);
    }

}
