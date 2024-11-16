package builder_design_pattern.WithPattern;

public class Student {

    String firstname;
    String latname;
    String rollNum;
    String age;
    String surname;
    String fathername;
    String subject;

    public Student(StudentBuilder studentBuilder) {
        this.firstname = studentBuilder.firstname;
        this.latname = studentBuilder.latname;
        this.age = studentBuilder.age;
        this.fathername = studentBuilder.fathername;
        this.rollNum = studentBuilder.rollNum;
        this.surname = studentBuilder.surname;
        this.subject = studentBuilder.subject;
    }

    public String getSubject() {
        return subject;
    }
}
