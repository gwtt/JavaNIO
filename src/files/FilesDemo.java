package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesDemo {
    public static void main(String[] args) {
        //createDirectory
//        Path path = Paths.get("D:\\gwt");
//        try {
//            Path directory = Files.createDirectory(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //copy
//        Path sourcePath      = Paths.get("D:\\gwt.txt");
//        Path destinationPath = Paths.get("E:\\gwt.txt");
//
//        try {
//            Files.copy(sourcePath, destinationPath);
//        } catch(FileAlreadyExistsException e) {
//            // 目录已经存在
//        } catch (IOException e) {
//            // 其他发生的异常
//            e.printStackTrace();
//        }

        //delete
//        Path path= Paths.get("D:\\gwt.txt");
//        try {
//            Files.delete(path);
//        } catch (IOException e) {
//            // 删除文件失败
//            e.printStackTrace();
//        }

        //walkFileTree

        Path path = Paths.get("D:\\QT");
        String fileToFind = File.separator + "1.txt";
        try{
            Files.walkFileTree(path,new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = "+fileString);

                    if(fileString.endsWith(fileToFind)) {
                        System.out.println("file found at path: " + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
