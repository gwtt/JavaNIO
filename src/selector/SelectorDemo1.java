package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo1 {
    public static void main(String[] args) throws IOException {
        //1.创建selector
        Selector selector = Selector.open();

        //2.获取通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        //3.设置非阻塞
        socketChannel.configureBlocking(false);

        //4.绑定连接
        socketChannel.bind(new InetSocketAddress(9999));

        //5.将通道注册到选择器上,并制定监听事件: "接收" 事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //查询已经就绪通道操作
        Set<SelectionKey> selectionKeys = selector.selectedKeys();;
        //遍历集合
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            //判断key就绪状态
            if(key.isAcceptable()) {

            } else if (key.isConnectable()) {

            } else if (key.isReadable()) {

            } else if (key.isWritable()) {

            }
            iterator.remove();
        }
    }
}
