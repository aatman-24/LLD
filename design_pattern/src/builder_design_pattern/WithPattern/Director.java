package builder_design_pattern.WithPattern;

public class Director {

    StudentBuilder studentBuilder;

    public Director(StudentBuilder studentBuilder) {
        this.studentBuilder = studentBuilder;
    }

    public StudentBuilder createStudent() {

        if(studentBuilder instanceof CSStudentBuilder) {
            return createCSStudent();
        }

        return createMBAStudent();
    }

    public StudentBuilder createMBAStudent() {
        return studentBuilder.setSubject();
    }

    public StudentBuilder createCSStudent() {
        return studentBuilder.setSubject();
    }
}
