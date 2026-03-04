package repositories;

import domain.*;

import java.util.*;

public class Repository implements FacultyRepository, DepartmentRepository, TeacherRepository, StudentRepository {

    // static class variable that will store a reference to a single instance of the class
    private static Repository instance = null;

    private Map<String, Faculty> faculties = new LinkedHashMap<>();
    private Faculty lastAddedFaculty;
    private Map<String, Department> departments = new LinkedHashMap<>();
    private Department lastAddedDepartment;
    private Map<String, Student> students = new LinkedHashMap<>();
    private Map<String, Teacher> teachers = new LinkedHashMap<>();
    private Teacher lastAddedTeacher;

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private Repository(){}

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


    @Override
    public void addStudent(Student student){
        students.put(student.getId(), student);
    }

    @Override
    public void deleteStudent(Student student){
        students.remove(student.getId());
    }

    @Override
    public Optional <Student> findStudentById(String id) {
        Optional<Student> foundStudent = Optional.ofNullable(students.get(id));
        return foundStudent;
    }

    @Override
    public Optional <Student> findStudentByStudentId(String id) {
        Optional<Student> foundStudent = Optional.empty();
        if(students.size()!=0){
            for (Student student : students.values()) {
                if(student.getStudentId().equals(id)){
                    foundStudent = Optional.of(student);
                    break;
                }
            }
        }
        return foundStudent;
    }

    @Override
    public List<Student> findStudentsByFullName(String fullName) {
        List<Student> foundStudents = new ArrayList<>();
        for(Student student : students.values()){
            if(student.getFullName().equals(fullName)){
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    @Override
    public List<Student> findStudentsByCourse(int course){
        List<Student> foundStudents = new ArrayList<>();
        for(Student student : students.values()){
            if(student.getCourse()==course){
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    @Override
    public List<Student> findStudentsByGroup(String group){
        List<Student> foundStudents = new ArrayList<>();
        for(Student student : students.values()){
            if(student.getGroup().equals(group)){
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public boolean studentsIsNotEmpty(){
        return students.size()!=0;
    }




    @Override
    public void addTeacher(Teacher teacher){
        teachers.put(teacher.getId(), teacher);
        lastAddedTeacher = teacher;
    }

    @Override
    public void deleteTeacher(Teacher teacher){
        teachers.remove(teacher.getId());
    }

    @Override
    public Optional<Teacher> findTeacherById(String id){
        Optional<Teacher> foundTeacher = Optional.ofNullable(teachers.get(id));
        return foundTeacher;
    }

    @Override
    public Teacher findLastAddedTeacher(){
        return lastAddedTeacher;
    }

    @Override
    public List<Teacher> findTeachersByFullName(String fullName){
        List<Teacher> foundTeachers = new ArrayList<>();
        for(Teacher teacher : teachers.values()){
            if(teacher.getFullName().equals(fullName)){
                foundTeachers.add(teacher);
            }
        }
        return foundTeachers;
    }

    @Override
    public List<Teacher> getTeachers() {
        return new ArrayList<>(teachers.values());
    }

    @Override
    public boolean teachersIsNotEmpty(){
        return students.size()!=0;
    }




    @Override
    public void addDepartment(Department department) {
        departments.put(department.getUniqueCode(), department);
        lastAddedDepartment = department;
    }

    @Override
    public void deleteDepartment(Department department) {
        departments.remove(department.getUniqueCode());
    }

    @Override
    public Optional<Department> findDepartmentByUniqueCode(String uniqueCode ){
        Optional<Department> foundDepartament = Optional.ofNullable(departments.get(uniqueCode));
        return foundDepartament;
    }

    @Override
    public Optional<Department> findDepartmentByName(String name){
        Optional<Department> foundDepartament = Optional.empty();
        if (departments.size()!=0) {
            for (Department department : departments.values()) {
                if (department.getName().equals(name)) {
                    foundDepartament = Optional.of(department);
                    break;
                }
            }
        }
        return foundDepartament;
    }

    @Override
    public Department findLastAddedDepartment(){
        return lastAddedDepartment;
    }

    @Override
    public List<Department> getDepartments() {
        return new ArrayList<>(departments.values());
    }




    @Override
    public void addFaculty(Faculty faculty){
        faculties.put(faculty.getUniqueCode(), faculty);
        lastAddedFaculty = faculty;
    }

    @Override
    public void deleteFaculty(Faculty faculty){
        faculties.remove(faculty.getUniqueCode());
    }

    @Override
    public Optional<Faculty> findFacultyByUniqueCode(String uniqueCode){
        Optional<Faculty> foundFaculty = Optional.ofNullable(faculties.get(uniqueCode));
        return foundFaculty;
    }

    @Override
    public Optional<Faculty> findFacultyByFullName(String fullName){
        Optional<Faculty> foundFaculty = Optional.empty();
        if(faculties.size()!=0){
            for(Faculty faculty : faculties.values()){
                if (faculty.getFullName().equals(fullName)){
                    foundFaculty = Optional.of(faculty);
                    break;
                }
            }
        }
        return foundFaculty;
    }

    @Override
    public Optional<Faculty> findFacultyByShortName(String shortName){
        Optional<Faculty> foundFaculty = Optional.empty();
        if(faculties.size()!=0){
            for(Faculty faculty : faculties.values()){
                if (faculty.getShortName().equals(shortName)){
                    foundFaculty = Optional.of(faculty);
                    break;
                }
            }
        }
        return foundFaculty;
    }

    @Override
    public Faculty findLastAddedFaculty(){
        return lastAddedFaculty;
    }

    @Override
    public List<Faculty> getFaculties() {
        return new ArrayList<>(faculties.values());
    }
}
