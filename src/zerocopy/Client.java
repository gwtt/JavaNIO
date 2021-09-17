package zerocopy;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        normalCopy.copy();
        zeroCopy.copy();
    }
}
