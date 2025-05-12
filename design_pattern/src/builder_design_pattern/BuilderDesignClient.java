package builder_design_pattern;

import builder_design_pattern.WithPattern.CSStudentBuilder;
import builder_design_pattern.WithPattern.Director;
import builder_design_pattern.WithPattern.MBAStudentBuilder;
import builder_design_pattern.WithPattern.Student;

public class BuilderDesignClient {

    public static void run() {

        Director director = new Director(new CSStudentBuilder());
        Student aatman = director.createStudent().setAge("24").setFathername("Jitendrabhai").setFirstname("Aatman").setRollNum("25").build();

        Director director2 = new Director(new MBAStudentBuilder());
        Student mitul = director2.createStudent().setAge("24").setFathername("Jitendrabhai").setFirstname("Mitul").setRollNum("25").build();

        System.out.println(aatman.getSubject());
        System.out.println(mitul.getSubject());

    }
}
