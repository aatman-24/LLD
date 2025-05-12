package composite_design_pattern.WithoutPattern;

public class File {

    String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public void ls() {
        System.out.println("File: " + this.fileName );
    }
}
