package zerocopy;

import java.io.*;
import java.nio.channels.FileChannel;

public class zeroCopy {

    public static void copy() throws IOException {
        long startTime = System.currentTimeMillis();//记录开始时间

        //------Core Code------
        File srcFile = new File("D:\\File.exe");
        File descFile = new File("E:\\File1.exe");

        FileChannel srcFileChannel = new RandomAccessFile(srcFile,"rw").getChannel();
        FileChannel descFileChannel = new RandomAccessFile(descFile,"rw").getChannel();

        srcFileChannel.transferTo(0,srcFile.length(),descFileChannel);
        //--------------------

        long endTime = System.currentTimeMillis();
        float exactTime = (float)(endTime - startTime) / 1000;
        System.out.println("零拷贝时间:"+exactTime);

    }
}
