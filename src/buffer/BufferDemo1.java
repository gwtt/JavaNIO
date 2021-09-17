package buffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo1 {
    public void buffer01() throws IOException {
        //FileChannel
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\gwt.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        //创建buffer,大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读
        int bytesRead = fileChannel.read(buffer);

        while(bytesRead != - 1) {
            //read模式
            buffer.flip();

            while(buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            bytesRead = fileChannel.read(buffer);
        }
        randomAccessFile.close();
    }
    @Test
    public void buffer02(){
        //创建buffer
        IntBuffer buffer = IntBuffer.allocate(8);

        //buffer放
        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2*(i+1);
            buffer.put(j);
        }

        //重置缓冲区
        buffer.flip();

        //获取
        while(buffer.hasRemaining()) {
            int value = buffer.get();
            System.out.println(value+" ");
        }
    }
}
