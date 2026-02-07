package domain;

import repository.Repository;

import java.util.List;
import java.util.Scanner;

public class Teacher extends Person {

    private static Scanner scanner = new Scanner(System.in);

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
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getRole() {
        return "Викладач";
    }

    @Override
    public String toString() {
        return String.format("Викладач: %s, Посада: %s, Науковий ступінь: %s, Вчене звання: %s, Дата прийняття: %s, Ставка: %s",
                        super.toString(), position, academicDegree.getDisplayName(), academicTitle.getDisplayName(),
                        hireDate, workload);
    }

    public static void selectOperation(){
        while(true){
            System.out.println("Виберіть, що хочете зробити: " +
                    "\n1 - створити новий" +
                    "\n2 - побачити всіх викладачів" +
                    "\n3 - редагувати існуючий" +
                    "\n4 - видалити існуючий" +
                    "\n0 - вийти на рівень вище");
            int whatToDo;
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            whatToDo = scanner.nextInt();
            while(whatToDo<0 || whatToDo>4){
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                }
                whatToDo = scanner.nextInt();
            }

            // create a new one was chosen
            if(whatToDo==1){
                createNew();
                // see existing one was chosen
            }else if (whatToDo==2){
                System.out.println("Список наявних викладачів:");
                Repository.getInstance().showAllTeachers();
                // change existing one was chosen
            }else if(whatToDo==3){
                Repository.getInstance().changeTeacher();
                // delete existing one was chosen
            }else if (whatToDo==4){
                Repository.getInstance().deleteTeacher();
                // exit was chosen
            }else if (whatToDo==0){
                break;
            }
        }
    }

    private static void createNew(){
        System.out.println("Введіть 1, щоби розпочати, або 0, щоби повернутися на крок назад: ");
        int makingSure;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        makingSure = scanner.nextInt();
        while(makingSure!=0 && makingSure!=1){
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            makingSure = scanner.nextInt();
        }

        if(makingSure==1){
            Repository.getInstance().addTeacher(new Teacher(idValidation(), lastNameValidation(),
                    firstNameValidation(), patronymicValidation(), birthDateValidation(),
                    emailValidation(), phoneNumberValidation(), positionValidation(),
                    academicDegreeValidation(), academicTitleValidation(), hireDateValidation(),
                    workloadValidation()));
            System.out.println("Ви успішно додали викладача: \n" + Repository.getInstance().findLastAddedTeacher());
        }
    }

    private static String idValidation(){
        scanner.nextLine();
        System.out.println("Введіть унікальний ідентифікатор з 5 знаків: ");
        String id = scanner.nextLine();
        while(id.length() != 5){
            System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
            id = scanner.nextLine();
        }
        List<Teacher> teachers = Repository.getInstance().getTeachers();
        if(teachers.size() != 0){
            for(int i=0; i<teachers.size(); i++){
                while(teachers.get(i).getId().equals(id)){
                    System.out.println("Викладач з таким ідентифікатором уже існує, введіть інший:");
                    id = scanner.nextLine();
                    while(id.length() != 5) {
                        System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
                        id = scanner.nextLine();
                    }
                }
            }
        }
        return id;
    }

    public static String lastNameValidation(){
        scanner.nextLine();
        System.out.println("Введіть прізвище: ");
        String lastName = scanner.nextLine();
        while(lastName.equals("") || lastName.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть прізвище: ");
            lastName = scanner.nextLine();
        }
        return lastName;
    }

    public static String firstNameValidation(){
        scanner.nextLine();
        System.out.println("Введіть ім'я: ");
        String firstName = scanner.nextLine();
        while(firstName.equals("") || firstName.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть ім'я: ");
            firstName = scanner.nextLine();
        }
        return firstName;
    }

    public static String patronymicValidation(){
        scanner.nextLine();
        System.out.println("Введіть побатькові: ");
        String patronymic = scanner.nextLine();
        while(patronymic.equals("") || patronymic.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть побатькові: ");
            patronymic = scanner.nextLine();
        }
        return patronymic;
    }

    public static String birthDateValidation(){
        scanner.nextLine();
        System.out.println("Введіть рік народження: ");
        int bdYear;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий рік народження, введіть інший: ");
        }
        bdYear = scanner.nextInt();
        while(bdYear<1900 || bdYear>2026){
            System.out.println("Не може бути такий рік народження, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий рік народження, введіть інший: ");
            }
            bdYear = scanner.nextInt();
        }

        System.out.println("Введіть місяць народження цифрою: ");
        int bdMonth;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий місяць народження, введіть інший: ");
        }
        bdMonth = scanner.nextInt();
        while(bdMonth<1 || bdMonth>12){
            System.out.println("Не може бути такий місяць народження, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий місяць народження, введіть інший: ");
            }
            bdMonth = scanner.nextInt();
        }

        System.out.println("Введіть день народження цифрою: ");
        int bdDay;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий день народження, введіть інший: ");
        }
        bdDay = scanner.nextInt();
        while(bdDay<1 || bdDay>31){
            System.out.println("Не може бути такий день народження, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий день народження, введіть інший: ");
            }
            bdDay = scanner.nextInt();
        }
        String birthDate = bdDay + "." + bdMonth + "." + bdYear;
        return birthDate;
    }

    public static String emailValidation(){
        scanner.nextLine();
        scanner.nextLine();
        System.out.println("Введіть email: ");
        String email = scanner.nextLine();
        while(email.equals("") || email.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть email: ");
            email = scanner.nextLine();
        }
        return email;
    }

    public static String phoneNumberValidation(){
        scanner.nextLine();
        System.out.println("Введіть номер телефону: ");
        String phoneNumber = null;
        boolean everythingIsFine = false;
        while(!everythingIsFine){
            phoneNumber = scanner.nextLine();
            while(phoneNumber.length()!=13 && phoneNumber.length()!=12 && phoneNumber.length()!=10){
                System.out.println("Не може бути такий номер телефону, введіть інший: ");
                phoneNumber = scanner.nextLine();
            }
            int index;
            if(phoneNumber.charAt(0) == '+'){
                index = 1;
            }else{
                index = 0;
            }
            for(int i = index; i<phoneNumber.length(); i++){
                if(phoneNumber.charAt(i)<'0' || phoneNumber.charAt(i)>'9'){
                    System.out.println("Не може бути такий номер телефону, введіть інший: ");
                    break;
                }
                if (i==phoneNumber.length()-1){
                    everythingIsFine = true;
                }
            }
        }
        return phoneNumber;
    }

    public static String positionValidation(){
        scanner.nextLine();
        System.out.println("Введіть посаду: ");
        String pos = scanner.nextLine();
        while(pos.equals("") || pos.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть посаду: ");
            pos = scanner.nextLine();
        }
        return pos;
    }

    public static AcademicDegree academicDegreeValidation(){
        System.out.println("Виберіть науковий ступунь:" +
                "\n0 - Без ступеня" +
                "\n1 - Кандидат наук" +
                "\n2 - Доктор наук");
        int indexOfAcademicDegree;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        indexOfAcademicDegree = scanner.nextInt();
        while(indexOfAcademicDegree<0 || indexOfAcademicDegree>2){
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            indexOfAcademicDegree = scanner.nextInt();
        }
        AcademicDegree acDeg = AcademicDegree.values()[indexOfAcademicDegree];
        return acDeg;
    }

    public static AcademicTitle academicTitleValidation(){
        System.out.println("Виберіть вчене звання:" +
                "\n0 - Без звання" +
                "\n1 - Доцент" +
                "\n2 - Професор");
        int indexOfAcademicTitle;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        indexOfAcademicTitle = scanner.nextInt();
        while(indexOfAcademicTitle<0 || indexOfAcademicTitle>2){
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            indexOfAcademicTitle = scanner.nextInt();
        }
        AcademicTitle acTit = AcademicTitle.values()[indexOfAcademicTitle];
        return acTit;
    }

    public static String hireDateValidation(){
        scanner.nextLine();
        System.out.println("Введіть рік прийняття на роботу: ");
        int emYear;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий рік прийняття на роботу, введіть інший: ");
        }
        emYear = scanner.nextInt();
        while(emYear<1900 || emYear>2026){
            System.out.println("Не може бути такий рік прийняття на роботу, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий рік прийняття на роботу, введіть інший: ");
            }
            emYear = scanner.nextInt();
        }

        System.out.println("Введіть місяць прийняття на роботу цифрою: ");
        int emMonth;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий місяць прийняття на роботу, введіть інший: ");
        }
        emMonth = scanner.nextInt();
        while(emMonth<1 || emMonth>12){
            System.out.println("Не може бути такий місяць прийняття на роботу, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий місяць прийняття на роботу, введіть інший: ");
            }
            emMonth = scanner.nextInt();
        }

        System.out.println("Введіть день прийняття на роботу цифрою: ");
        int emDay;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий день прийняття на роботу, введіть інший: ");
        }
        emDay = scanner.nextInt();
        while(emDay<1 || emDay>31){
            System.out.println("Не може бути такий день прийняття на роботу, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий день прийняття на роботу, введіть інший: ");
            }
            emDay = scanner.nextInt();
        }
        String employmentDate = emDay + "." + emMonth + "." + emYear;
        return employmentDate;
    }

    public static double workloadValidation(){
        scanner.nextLine();
        System.out.println("Введіть ставку: ");
        double load;
        while (!scanner.hasNextDouble()) {
            String input = scanner.next();
            System.out.println("Не може бути така ставка, введіть іншу: ");
        }
        load = scanner.nextDouble();
        while(load<0){
            System.out.println("Не може бути така ставка, введіть іншу: ");
            while (!scanner.hasNextDouble()) {
                String input = scanner.next();
                System.out.println("Не може бути така ставка, введіть іншу: ");
            }
            load = scanner.nextDouble();
        }
        return load;
    }

   }


