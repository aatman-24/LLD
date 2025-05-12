package builder_design_pattern.WithPattern;

public class CSStudentBuilder extends StudentBuilder{
    @Override
    public StudentBuilder setSubject() {
        this.subject = "CSWithMojo";
        return this;
    }
}
