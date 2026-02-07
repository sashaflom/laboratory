package repository;

import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Repository {

    private Scanner scanner = new Scanner(System.in);

    // static class variable that will store a reference to a single instance of the class
    private static Repository instance = null;

    private List<Faculty> faculties = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private Repository(){

    }

    // a static class method that should return a reference to a single instance of the class
    public static Repository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new Repository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public Teacher findLastAddedTeacher(){
        return teachers.get(teachers.size()-1);
    }

    public void deleteTeacher(){
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
            System.out.println("Список наявних викладачів:");
            showAllTeachers();
            if(teachers.size()!=0){
                System.out.println("Введіть ID викладача, якого хочете видалити: ");
                scanner.nextLine();
                String id = scanner.nextLine();
                while(id.length() != 5){
                    System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
                    id = scanner.nextLine();
                }
                Optional<Teacher> maybeTeacher = findTeacherByID(id);
                if (maybeTeacher.isPresent()){
                    Teacher foundTeacher = maybeTeacher.get();
                    teachers.remove(foundTeacher);
                    System.out.println("Ви успішно видалили викладача. Оновлений список наявних викладачів:");
                    showAllTeachers();
                }else{
                    System.out.println("Немає викладача з таким ID.");
                }
            }else{
                System.out.println("Не створено жодного викладача, отже ви не можете нікого видалити");
            }

        }
    }

    public void showAllTeachers(){
        if (teachers.size()!=0){
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }else{
            System.out.println("Поки що немає жодного викладача");
        }
    }

    public Optional<Teacher> findTeacherByID(String id){
        Optional<Teacher> foundTeacher = Optional.empty();
        if(teachers.size()!=0){
            for (Teacher teacher : teachers) {
                if(teacher.getId().equals(id)){
                    foundTeacher = Optional.of(teacher);
                    break;
                }
            }
        }else{
            System.out.println("Не створено жодного викладача, отже ви не можете знайти");
        }

        return foundTeacher;
    }

    public void changeTeacher(){
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
        if(makingSure==1) {
            System.out.println("Список наявних викладачів:");
            showAllTeachers();
            if(teachers.size()!=0){
                System.out.println("Введіть ID викладача, якого хочете редагувати: ");
                scanner.nextLine();
                String id = scanner.nextLine();
                while (id.length() != 5) {
                    System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
                    id = scanner.nextLine();
                }
                Optional<Teacher> maybeTeacher = findTeacherByID(id);
                if (maybeTeacher.isPresent()){
                    Teacher foundTeacher = maybeTeacher.get();
                    while(true){
                        System.out.println("Виберіть, що ви хочете змінити:" +
                                "\n1 - прізвище" +
                                "\n2 - ім'я" +
                                "\n3 - побатькові" +
                                "\n4 - дату народження" +
                                "\n5 - email" +
                                "\n6 - номер телефону" +
                                "\n7 - посаду" +
                                "\n8 - науковий ступінь" +
                                "\n9 - вчене звання" +
                                "\n10 - дату прийняття на роботу" +
                                "\n11 - ставку" +
                                "\n0 - вийти на рівень вище");
                        int whatToChange;
                        while (!scanner.hasNextInt()) {
                            String input = scanner.next();
                            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                        }
                        whatToChange = scanner.nextInt();
                        while(whatToChange<0 || whatToChange>11){
                            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                            while (!scanner.hasNextInt()) {
                                String input = scanner.next();
                                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                            }
                            whatToChange = scanner.nextInt();
                        }

                        // last name to change
                        if (whatToChange==1){
                            foundTeacher.setLastname(Teacher.lastNameValidation());
                            // first name to change
                        }else if(whatToChange==2){
                            foundTeacher.setFirstname(Teacher.firstNameValidation());
                            // patronymic name to change
                        }else if (whatToChange==3){
                            foundTeacher.setPatronymic(Teacher.patronymicValidation());
                            // birth date to change
                        }else if(whatToChange==4){
                            foundTeacher.setBirthDate(Teacher.birthDateValidation());
                            // email to change
                        }else if(whatToChange==5){
                            foundTeacher.setEmail(Teacher.emailValidation());
                            // phone number to change
                        }else if (whatToChange==6){
                            foundTeacher.setPhoneNumber(Teacher.phoneNumberValidation());
                            // position to change
                        }else if(whatToChange==7){
                            foundTeacher.setPosition(Teacher.positionValidation());
                            // academic degree was chosen
                        }else if(whatToChange==8){
                            foundTeacher.setAcademicDegree(Teacher.academicDegreeValidation());
                            // academic title to change
                        }else if (whatToChange==9){
                            foundTeacher.setAcademicTitle(Teacher.academicTitleValidation());
                            // hire date to change
                        }else if(whatToChange==10){
                            foundTeacher.setHireDate(Teacher.hireDateValidation());
                            // workload to change
                        }else if(whatToChange==11){
                            foundTeacher.setWorkload(Teacher.workloadValidation());
                            // exit
                        }else if(whatToChange==0){
                            break;
                        }
                        System.out.println("Ви успішно змінили викладача: " + foundTeacher);
                    }
                }else{
                    System.out.println("Немає викладача з таким ID.");
                }
            }else{
                System.out.println("Не створено жодного викладача, отже ви не можете змінити");
            }

        }
    }

    public List<Faculty> getFaculties() {
        return new ArrayList<>(faculties);
    }

    public List<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public List<Teacher> getTeachers() {
        return new ArrayList<>(teachers);
    }
}
