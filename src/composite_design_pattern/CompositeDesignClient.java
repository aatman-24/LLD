package composite_design_pattern;

import
composite_design_pattern.WithPattern.Directory;
import
composite_design_pattern.WithPattern.File;

public class CompositeDesignClient {

    public static void run() {
        Directory aatman = new Directory("Aatman");
        Directory part1 = new Directory("part-1");
        part1.add(new File("file1"));
        part1.add(new File("file2"));
        aatman.add(part1);
        aatman.add(new File("file3"));
        aatman.ls();
    }
}
