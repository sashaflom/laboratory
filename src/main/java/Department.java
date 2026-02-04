public class Department {

    private final String uniqueCode;
    private String name;
    private Faculty faculty;
    // here must be private Teacher headOfDepartment;
    private String location;

    // DON'T FORGET TO ADD HEAD OF DEPARTMENT
    public Department(String uniqueCode, String name, Faculty faculty, String location){
        this.uniqueCode = uniqueCode;
        this.name = name;
        this.faculty = faculty;
        this.location = location;
    }

    // DON'T FORGET TO ADD HEAD OF DEPARTMENT
    @Override
    public String toString(){
        return "\nКафедра: \nунікальний код: '%s', \nназва: '%s', \nфакультет: '%s', \nлокація: '%s'.".formatted(uniqueCode, name, faculty, location);
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
