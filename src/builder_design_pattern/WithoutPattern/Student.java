package builder_design_pattern.WithoutPattern;

public class Student {

    String firstname;
    String latname;
    String rollNum;
    String age;
    String surname;
    String fathername;

    public Student() {
    }

    public Student(String firstname, String latname, String rollNum) {
        this.firstname = firstname;
        this.latname = latname;
        this.rollNum = rollNum;
    }

    // Problem: We can't have this overloaded constructor because it is same with above one. There is no change in signature so it consider same one.
//    public Student(String age, String surname, String fathername) {
//        this.age = age;
//        this.surname = surname;
//        this.fathername = fathername;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLatname() {
        return latname;
    }

    public void setLatname(String latname) {
        this.latname = latname;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }
}
