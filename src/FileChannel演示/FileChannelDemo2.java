package FileChannel演示;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//写操作
public class FileChannelDemo2 {
    public static void main(String[] args) throws IOException {
        //打开FileChannel
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\gwt.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        //创建buffer对象
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String newData = "Hello";

        //写入内容
        buffer.put(newData.getBytes());
        buffer.flip();

        //FileChannel最终实现
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

        //关闭
        channel.close();
    }
}
