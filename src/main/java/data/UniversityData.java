package data;

import domain.*;
import repositories.*;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniversityData implements Serializable {

    private Map<String, Faculty> facultyMap = new LinkedHashMap<>();
    private Map<String, Department> departmentMap = new LinkedHashMap<>();
    private Map<String, Student> studentMap = new LinkedHashMap<>();
    private Map<String, Teacher> teacherMap = new LinkedHashMap<>();
    private Map<String, User> userMap = new LinkedHashMap<>();

    public UniversityData(){}

    public Map<String, Faculty> getFacultyMap(){ return Map.copyOf(facultyMap);}
    public Map<String, Department> getDepartmentMap(){ return Map.copyOf(departmentMap);}
    public Map<String, Student> getStudentMap(){ return Map.copyOf(studentMap);}
    public Map<String, Teacher> getTeacherMap(){ return Map.copyOf(teacherMap);}
    public Map<String, User> getUserMap(){ return Map.copyOf(userMap);}

    public void setFacultyMap(Map<String, Faculty> facultyMap){this.facultyMap = Map.copyOf(facultyMap);}
    public void setDepartmentMap(Map<String, Department> departmentMap){this.departmentMap = Map.copyOf(departmentMap);}
    public void setStudentMap(Map<String, Student> studentMap){this.studentMap = Map.copyOf(studentMap);}
    public void setTeacherMap(Map<String, Teacher> teacherMap){this.teacherMap = Map.copyOf(teacherMap);}
    public void setUserMap(Map<String, User> userMap){this.userMap = Map.copyOf(userMap);}

    public void collectData(){
        facultyMap = Map.copyOf(FacultyRepository.getInstance().getMap());
        departmentMap = Map.copyOf(DepartmentRepository.getInstance().getMap());
        studentMap = Map.copyOf(StudentRepository.getInstance().getMap());
        teacherMap = Map.copyOf(TeacherRepository.getInstance().getMap());
        userMap = Map.copyOf(UserRepository.getInstance().getMap());
    }

    public void putData(){
        FacultyRepository.getInstance().setMap(facultyMap);
        DepartmentRepository.getInstance().setMap(departmentMap);
        StudentRepository.getInstance().setMap(studentMap);
        TeacherRepository.getInstance().setMap(teacherMap);
        UserRepository.getInstance().setMap(userMap);
    }
}
