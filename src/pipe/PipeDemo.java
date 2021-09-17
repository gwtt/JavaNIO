package pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeDemo {
    public static void main(String[] args) throws IOException {
        // 1. 获取管道
        Pipe pipe = Pipe.open();
        // 2 获取sink通道
        Pipe.SinkChannel sinkChannel = pipe.sink();
        // 3.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.put("发送数据".getBytes());
        buffer.flip(); // 切换数据模式
        // 4.写入数据
        sinkChannel.write(buffer);

        // 5. 获取source通道
        Pipe.SourceChannel sourceChannel = pipe.source();
        buffer.flip();
        int len = sourceChannel.read(buffer);
        System.out.println(new String(buffer.array(), 0, len));

        // 7. 关闭管道
        sinkChannel.close();
        sourceChannel.close();

    }
}
