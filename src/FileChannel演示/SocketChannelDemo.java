package FileChannel演示;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        // 创建SocketChannel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com",80));

        //设置阻塞和非阻塞
        socketChannel.configureBlocking(false);
        //读操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("over");
//        socketChannel.isOpen(); //测试SocketChannel是否为open状态
//        socketChannel.isConnected(); //测试SocketChannel是否被连接
//        socketChannel.isConnectionPending(); //测试SocketChannel是否正在连接
//        socketChannel.finishConnect(); //校验正在进行套接字连接额SocketChannel是否已经完成连接
        socketChannel.getOption(StandardSocketOptions.SO_KEEPALIVE);
        socketChannel.getOption(StandardSocketOptions.SO_RCVBUF);
    }
}
