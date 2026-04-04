package validators;

import domain.Student;
import domain.Teacher;
import exceptions.*;
import repositories.StudentRepository;
import repositories.TeacherRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

public class PersonValidator {

    private static TeacherRepository repositoryT = TeacherRepository.getInstance();
    private static StudentRepository repositoryS = StudentRepository.getInstance();

    public static void isIdValid(String id) throws DuplicateIdException {
        Optional<Teacher> maybeTeacher = repositoryT.findById(id);
        if(maybeTeacher.isPresent()){
            throw new DuplicateIdException("Помилка! Уже існує викладач з унікальним ідентифікатором " + id + ".");
        }else{
            Optional<Student> maybeStudent = repositoryS.findById(id);
            if(maybeStudent.isPresent()){
                throw new DuplicateIdException("Помилка! Уже існує студент з унікальним ідентифікатором " + id + ".");
            }
        }
    }

    public static void isPhoneNumberValid(String phoneNumber) throws InvalidPhoneNumber {
        int index;
        if(phoneNumber.charAt(0) == '+'){
            index = 1;
        }else{
            index = 0;
        }
        for(int i = index; i<phoneNumber.length(); i++){
            if(phoneNumber.charAt(i)<'0' || phoneNumber.charAt(i)>'9'){
                throw new InvalidPhoneNumber("Помилка! Некоректний формат номеру телефону.");
            }
        }
    }

    public static void isEmailValid(String email) throws InvalidEmailException {
        int atSymbolIndex = -1;
        for(int i = 0; i<email.length(); i++){
            if(email.charAt(i) == '@'){
                atSymbolIndex = i;
            }
        }
        if(atSymbolIndex == -1){
            throw new InvalidEmailException("Помилка! Email має бути формату xxx@ukma.edu.ua.");
        }else{
            String afterAt = email.substring(atSymbolIndex);
            if(!afterAt.equals("@ukma.edu.ua")){
                throw new InvalidEmailException("Помилка! Email має бути формату xxx@ukma.edu.ua.");
            }
        }
    }

    public static boolean isBirthDateValid(int year, int month, int day){
        try{
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e){
            return false;
        }
    }

}
