package composite_design_pattern.WithPattern;

import
composite_design_pattern.WithoutPattern.File;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem{

    String dirName;
    List<FileSystem> fileSystemList;

    public Directory(String dirName) {
        this.dirName = dirName;
        this.fileSystemList = new ArrayList<>();
    }

    public void add(FileSystem object) {
        fileSystemList.add(object);
    }

    @Override
    public void ls() {
        System.out.println("Directory Name: " + dirName);
        for(FileSystem obj: fileSystemList) {
            obj.ls();
        }
    }
}

