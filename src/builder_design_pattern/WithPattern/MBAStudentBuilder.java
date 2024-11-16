package builder_design_pattern.WithPattern;

public class MBAStudentBuilder extends StudentBuilder {

    @Override
    public StudentBuilder setSubject() {
        this.subject = "MBAChaiWala";
        return this;
    }
}
