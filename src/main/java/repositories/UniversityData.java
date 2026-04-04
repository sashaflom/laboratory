package repositories;

import domain.*;

import java.util.Map;

public class UniversityData {

    private Map<String, Faculty> facultyMap;
    private Map<String, Department> departmentMap;
    private Map<String, Student> studentMap;
    private Map<String, Teacher> teacherMap;

    public UniversityData(){}

    public UniversityData(Map<String, Faculty> facultyMap, Map<String, Department> departmentMap,
                          Map<String, Student> studentMap, Map<String, Teacher> teacherMap){
        this.facultyMap = Map.copyOf(facultyMap);
        this.departmentMap = Map.copyOf(departmentMap);
        this.studentMap = Map.copyOf(studentMap);
        this.teacherMap = Map.copyOf(teacherMap);
    }

    public Map<String, Faculty> getFacultyMap(){ return Map.copyOf(facultyMap);}
    public Map<String, Department> getDepartmentMap(){ return Map.copyOf(departmentMap);}
    public Map<String, Student> getStudentMap(){ return Map.copyOf(studentMap);}
    public Map<String, Teacher> getTeacherMap(){ return Map.copyOf(teacherMap);}

    public void setFacultyMap(Map<String, Faculty> facultyMap){this.facultyMap = Map.copyOf(facultyMap);}
    public void setDepartmentMap(Map<String, Department> departmentMap){this.departmentMap = Map.copyOf(departmentMap);}
    public void setStudentMap(Map<String, Student> studentMap){this.studentMap = Map.copyOf(studentMap);}
    public void setTeacherMap(Map<String, Teacher> teacherMap){this.teacherMap = Map.copyOf(teacherMap);}
}
