package FileChannel演示;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DatagramChannelDemo {
    //发送的实现
    @Test
    public void sendDatagram() throws IOException, InterruptedException {
        //打开 DatagramChannel
        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1",9999);

        //发送
        while(true) {
            ByteBuffer buffer = ByteBuffer.wrap("你好,我是顾文韬".getBytes(StandardCharsets.UTF_8));
            sendChannel.send(buffer,sendAddress);
            System.out.println("已经发送完成");
            Thread.sleep(1000);
        }
    }
    //接收的实现
    @Test
    public void receiveDatagram() throws IOException {
        //打开 DatagramChannel
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress sendAddress = new InetSocketAddress(9999);
        //绑定
        receiveChannel.bind(sendAddress);

        //buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //接收
        while(true) {
            buffer.clear();
            SocketAddress socketAddress = receiveChannel.receive(buffer);
            buffer.flip();
            System.out.println(socketAddress.toString());
            System.out.println(Charset.forName("UTF-8").decode(buffer));
        }
    }
    @Test
    public void testConnect() throws IOException {
        //打开DatagramChannel
        DatagramChannel connChannel = DatagramChannel.open();
        //绑定
        connChannel.bind(new InetSocketAddress(9999));
        //连接
        connChannel.connect(new InetSocketAddress("127.0.0.1",9999));
        //write方法
        connChannel.write(ByteBuffer.wrap("你好,我是顾文韬".getBytes(StandardCharsets.UTF_8)));
        //buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(true) {
            buffer.clear();
            connChannel.read(buffer);
            buffer.flip();
            System.out.println(Charset.forName("UTF-8").decode(buffer));
        }
    }
    public static void main(String[] args) throws IOException {
        DatagramChannel server = DatagramChannel.open();
        ByteBuffer sendBuffer = ByteBuffer.wrap("client send".getBytes());
        server.send(sendBuffer,new InetSocketAddress("127.0.0.1",10086));

        server.socket().bind(new InetSocketAddress(10086));
        ByteBuffer receiveBuffer = ByteBuffer.allocate(64);
        receiveBuffer.clear();
        SocketAddress receiveAddr = server.receive(receiveBuffer);
        server.connect(new InetSocketAddress("127.0.0.1",10086));
        int redSize = server.read(receiveBuffer);
        server.write(sendBuffer);
    }
}
