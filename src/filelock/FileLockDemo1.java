package filelock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLockDemo1 {
    public static void main(String[] args) throws IOException {
        String input = "gwt";
        System.out.println("input:"+input);

        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());

        String filePath = "D:\\gwt.txt";
        Path path = Paths.get(filePath);
        FileChannel fileChannel = FileChannel.open(path,
                StandardOpenOption.WRITE,StandardOpenOption.APPEND);
        fileChannel.position(fileChannel.size()-1);

        //做一个加锁操作
        FileLock lock = fileChannel.lock();
        System.out.println("是否共享锁:" +lock.isShared());
        fileChannel.write(buffer);
        fileChannel.close();

        //读文件
        readFile(filePath);
    }

    private static void readFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tr = bufferedReader.readLine();
        System.out.println("读取出内容: ");
        while(tr != null) {
            System.out.println(" "+tr);
            tr = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
    }
}
