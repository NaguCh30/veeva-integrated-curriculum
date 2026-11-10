package ByteStream;

public class ByteDemo {
    public static void main(String[] args) {
        
        byte value = 65;
        System.out.println(value);
        System.err.println((char) value);

        byte[] data = {65, 66, 67};
        for (byte b : data) {
            System.out.println((char)b);
        }
    }
}
