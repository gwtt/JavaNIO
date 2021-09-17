package path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo1 {
    public static void main(String[] args) {
        //创建path实例
        Path path = Paths.get("D:\\gwt.txt");
        //相对路径
        Path projects = Paths.get("D:\\","projects\\gwt.txt");
    }
}
