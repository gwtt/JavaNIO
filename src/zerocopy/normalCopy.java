package zerocopy;

import java.io.*;

public class normalCopy {

    public static void copy() throws IOException {
        long startTime = System.currentTimeMillis();//记录开始时间

        //------Core Code------
        InputStream inputStream = new FileInputStream("D:\\File.exe");
        OutputStream outputStream = new FileOutputStream("E:\\File.exe");

        byte[] buffer = new byte[4096];
        while (inputStream.read(buffer)>=0) {
            outputStream.write(buffer);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
        //--------------------

        long endTime = System.currentTimeMillis();
        float exactTime = (float)(endTime - startTime) / 1000;
        System.out.println("流式拷贝时间:"+exactTime);

    }
}
