package validators;

import domain.*;
import exceptions.*;
import repositories.*;
import java.util.*;

public class DepartmentValidator {

    private static DepartmentRepository repository = DepartmentRepository.getInstance();

    public static void isIdValid(String id) throws DuplicateIdException {
        Optional<Department> maybeDepartment = repository.findById(id);
        if(maybeDepartment.isPresent()){
            throw new DuplicateIdException("Помилка! Кафедра з унікальним ідентифікатором " + id + " уже існує");
        }
    }

    public static void isNameValid(String name) throws DuplicateNameException {
        Optional<Department> maybeDepartment = repository.findByName(name);
        if(maybeDepartment.isPresent()){
            throw new DuplicateNameException("Помилка! Кафедра з назвою " + name + " уже існує");
        }
    }

}
