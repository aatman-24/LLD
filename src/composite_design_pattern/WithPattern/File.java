package composite_design_pattern.WithPattern;

public class File implements FileSystem{

    String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ls() {
        System.out.println("File: " + this.fileName );
    }
}
