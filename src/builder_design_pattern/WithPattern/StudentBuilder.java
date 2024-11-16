package builder_design_pattern.WithPattern;

public abstract class StudentBuilder {

    String firstname;
    String latname;
    String rollNum;
    String age;
    String surname;
    String fathername;
    protected String subject;


    public StudentBuilder() {
    }

    public StudentBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public StudentBuilder setLatname(String latname) {
        this.latname = latname;
        return this;
    }

    public StudentBuilder setRollNum(String rollNum) {
        this.rollNum = rollNum;
        return this;
    }

    public StudentBuilder setAge(String age) {
        this.age = age;
        return this;
    }

    public StudentBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public StudentBuilder setFathername(String fathername) {
        this.fathername = fathername;
        return this;
    }

    abstract public StudentBuilder setSubject();

    public Student build() {
        return new Student(this);
    }


}
