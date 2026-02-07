public class Teacher extends Person {

    private String position; //посада
   private AcademicDegree academicDegree; //науковий ступінь
   private AcademicTitle academicTitle; // enum, вчене звання
   private String hireDate; //дата прийняття на роботу
   private double workload; //ставка/навантаження

    public Teacher(String id, String lastName, String firstName,String patronymic,
                  String birthDate, String email, String phoneNumber, String position,
                  AcademicDegree academicDegree, AcademicTitle academicTitle,
                  String hireDate, double workload ) {
       super(id, lastName, firstName, patronymic, birthDate, email, phoneNumber);
       this.position = position;
       this.academicDegree = academicDegree;
       this.academicTitle = academicTitle;
       this.hireDate = hireDate;
       this.workload = workload;
   }

   public String getPosition() {return position;}
    public AcademicDegree getAcademicDegree() {return academicDegree;}
    public AcademicTitle getAcademicTitle() {return academicTitle;}
    public String getHireDate() {return hireDate;}
    public double getWorkload() {return workload;}

    public void setPosition(String position) {this.position = position;}
    public void setWorkload(double workload) {this.workload = workload;}
    public void setAcademicDegree(AcademicDegree academicDegree) { this.academicDegree = academicDegree;}
    public void setAcademicTitle(AcademicTitle academicTitle) { this.academicTitle = academicTitle;}

    public String getRole() {
        return "Викладач";
    }

    @Override
    public String toString() {
        return String.format("Викладач: %s, Посада: %s, Науковий ступінь: %s, Вчене звання: %s, Дата прийняття: %s, Ставка: %s",
                        super.toString(), position, academicDegree.getDisplayName(), academicTitle.getDisplayName(),
                        hireDate, workload);
    }

    public enum AcademicTitle {
        NONE("Без звання"),
        DOCENT("Доцент"),
        PROFESSOR("Професор");

        private final String displayName;

        AcademicTitle(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    public enum AcademicDegree {
        NONE("Без ступеня"),
        CANDIDATE("Кандидат наук"),
        DOCTOR("Доктор наук");

        private final String displayName;

        AcademicDegree(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
   }


