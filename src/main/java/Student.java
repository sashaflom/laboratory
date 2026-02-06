public class Student extends Person {
    private String studentId;
    private int course;
    private String group;
    private int enrollmentYear;
    private EducationForm educationForm;  // enum
    private StudentStatus status;         // enum
    public Student(String lastName, String firstName, String patronymic,
                   String birthDate, String email, String phone,
                   String studentId, int course, String group,
                   int enrollmentYear, EducationForm educationForm,
                   StudentStatus status) {
        super(lastName, firstName, patronymic, birthDate, email, phone);
        this.studentId = studentId;
        this.course = course;
        this.group = group;
        this.enrollmentYear = enrollmentYear;
        this.educationForm = educationForm;
        this.status = status;
    }
    public String getStudentId() { return studentId; }
    public int getCourse() { return course; }
    public String getGroup() { return group; }
    public int getEnrollmentYear() { return enrollmentYear; }
    public EducationForm getEducationForm() { return educationForm; }
    public StudentStatus getStatus() { return status; }

    public void setCourse(int course) { this.course = course; }
    public void setGroup(String group) { this.group = group; }
    public void setStatus(StudentStatus status) { this.status = status; }


    @Override
    public String getRole() {
        return "Студент";
    }
    @Override
    public String toString() {
        return super.toString() +
                String.format(" Студент: %s, Курс: %d, Група: %s, Статус: %s",
                        studentId, course, group, status);
    }

    public enum EducationForm {
        BUDGET("Бюджет"),
        CONTRACT("Контракт");

        private final String displayName;

        EducationForm(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum StudentStatus {
        STUDYING("Навчається"),
        ACADEMIC_LEAVE("Академічна відпустка"),
        EXPELLED("Відрахований");
        private final String displayName;

        StudentStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

}