package FileChannel演示;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelDemo3 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("D:\\gwt.txt","rw");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile("D:\\qwe.txt","rw");
        FileChannel fromChannel = randomAccessFile1.getChannel();
        FileChannel toChannel = randomAccessFile2.getChannel();
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel,position,count);
        randomAccessFile1.close();
        randomAccessFile2.close();
        System.out.println("结束");
    }
}
