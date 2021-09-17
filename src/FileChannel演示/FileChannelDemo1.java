package FileChannel演示;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {
    //让FileChannel读取数据到buffer中
    public static void main(String[] args) throws IOException {
        //创建FileChannel
        RandomAccessFile aFile = new RandomAccessFile("D:\\gwt.txt","rw");
        FileChannel channel = aFile.getChannel();

        //创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读取数据到buff中
        int byteRead = channel.read(buffer);
        while(byteRead >= 0 ) {
            System.out.println("读取了:" + byteRead);
            buffer.flip();
            while(buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            byteRead = channel.read(buffer);
        }
        aFile.close();
        System.out.println("结束");

    }
}
