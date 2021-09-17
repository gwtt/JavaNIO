package com.gwt.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

//服务器端
public class ChatServer {
    //服务器端启动的方法
    public void startServer() throws IOException {
        //1.创建Selector选择器
        Selector selector = Selector.open();
        //2.创建ServerSocketChannel通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //3.为Channel通道绑定监听端口
        socketChannel.bind(new InetSocketAddress(8000));
        //设置非阻塞模式
        socketChannel.configureBlocking(false);

        //4.把channe通道注册到seletor选择器上
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        //5.循环,等待有新链接接入
        for(;;) {
            //获取Channel数量
            int readChannels = selector.select();

            if(readChannels == 0){
                continue;
            }

            //获取可用的Channel
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            //遍历集合
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                //移除set集合当前selectionKey
                iterator.remove();
                //6.根据就绪状态,调用对应方法实现具体业务操作
                //6.1如果Accept状态
                if(selectionKey.isAcceptable()) {
                    acceptOperator(socketChannel,selector);
                }
                //6.2如果可读状态
                if(selectionKey.isReadable()) {
                    readOperator(selector,selectionKey);
                }
            }
        }

    }

    //处理可读状态操作
    private void readOperator(Selector selector, SelectionKey selectionKey) throws IOException {
        //1.从selectionKey获取已经就绪的通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //2.创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //3.循环读取客户端消息
        int readLength = socketChannel.read(byteBuffer);
        String message = "";
        if(readLength > 0) {
            //切换读模式
            byteBuffer.flip();
            //读取内容
            message += Charset.forName("UTF-8").decode(byteBuffer);
        }
        //4.将channel再次注册到选择器,监听可读状态
        socketChannel.register(selector,SelectionKey.OP_READ);
        //5.把客户端发送消息,广播到其他客户端
        if(message.length()>0) {
            //广播给其他客户端
            System.out.println(message);
            castOtherClient(message, selector, socketChannel);
        }
    }


    //广播给其他客户端
    private void castOtherClient(String message, Selector selector, SocketChannel socketChannel) throws IOException {
        //1.获取所有已经接入的客户端
        Set<SelectionKey> selectionKeySet = selector.keys();

        //2.循环向所有的channel广播消息
        for(SelectionKey selectionKey : selectionKeySet) {
            //获取每个channel
            Channel channel = selectionKey.channel();
            //不需要给自己发送
            if(channel instanceof SocketChannel && channel != socketChannel) {
                ((SocketChannel) channel).write(Charset.forName("UTF-8").encode(message));
            }
        }

    }

    //处理接入状态操作
    private void acceptOperator(ServerSocketChannel socketChannel, Selector selector) throws IOException {
        //1.接入状态,创建socketChannel
        SocketChannel accept = socketChannel.accept();
        //2,把socketChannel设立为非阻塞模式
        accept.configureBlocking(false);
        //3.把channel注册到选择器上,监听可读状态
        accept.register(selector,SelectionKey.OP_READ);
        //4.客户端回复信息
        accept.write(Charset.forName("UTF-8").encode("欢迎进入♂聊天室"));
    }

    public static void main(String[] args) throws IOException {
        //启动
        new ChatServer().startServer();
    }
}
