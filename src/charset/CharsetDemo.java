package charset;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Set;

public class CharsetDemo {
    @Test
    public void charSetEncoderAndDecoder() throws
            CharacterCodingException {
        //0.获取Charset对象
        Charset charset= Charset.forName("UTF-8");

        //1.获取编码器对象
        CharsetEncoder charsetEncoder=charset.newEncoder();

        //2.获取解码器对象
        CharsetDecoder charsetDecoder=charset.newDecoder();

        //3.创建buffer缓冲区对象，并写入数据
        CharBuffer charBuffer=CharBuffer.allocate(1024);
        charBuffer.put("gwt数据");
        charBuffer.flip();//转换读写模式

        //4.通过编码器对象，进行编码
        ByteBuffer byteBuffer=charsetEncoder.encode(charBuffer);
        System.out.println("编码后：");
        for (int i=0;i<byteBuffer.limit();i++) {
            System.out.println(byteBuffer.get());
        }

        //5.解码
        byteBuffer.flip();//读写切换
        //通过解码器对象，进行解码
        CharBuffer charBuffer1=charsetDecoder.decode(byteBuffer);
        System.out.println("解码后：");
        System.out.println(charBuffer1.toString());

        System.out.println("指定其他格式解码:");
        Charset charset1=Charset.forName("GBK");
        byteBuffer.flip();//读写切换
        CharBuffer charBuffer2 =charset1.decode(byteBuffer);
        System.out.println(charBuffer2.toString());

        //6.获取 Charset 所支持的字符编码
        Map<String ,Charset> map= Charset.availableCharsets();
        Set<Map.Entry<String,Charset>> set=map.entrySet();
        for (Map.Entry<String,Charset> entry: set) {
            System. out .println(entry.getKey()+"="+entry.getValue().toString());
        }
    }
}
