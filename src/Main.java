import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class Main {


    public static void main(String[] args) throws IOException {
        Processing proc = new Processing();
        String[] message = proc.readFile("C:\\Users\\Chana\\Desktop\\Security1\\src\\message_short");
        String[] key = proc.readFile("C:\\Users\\Chana\\Desktop\\Security1\\src\\key_short");
        String[] cypher= proc.readFile("C:\\Users\\Chana\\Desktop\\Security1\\src\\cipher_short");
        Encryption.encrypt(message, key);

        Decryption.decrypt(cypher, key);
        byte[] f = Files.readAllBytes(Paths.get("2.txt"));
        for (byte b:f)
        {
            System.out.printf("%02X", b);
        }
        System.out.println();
        byte[] c = Files.readAllBytes(Paths.get("C:\\Users\\Chana\\Desktop\\Security1\\src\\message_short"));
        for (byte b:c)
        {
            System.out.printf("%02X", b);
        }

//        System.out.println(Arrays.toString(Files.readAllBytes(Paths.get("C:\\Users\\Chana\\Desktop\\Security1\\src\\cipher_short"))));
//        System.out.println(readFile("2.txt", StandardCharsets.UTF_16));


//        String[] cypher1 = proc.readFile("C:\\Users\\Chana\\Desktop\\Security1\\src\\cipher_short");
//        Decryption.decrypt(cypher1, key);
//        System.out.println(readFile("2.txt", StandardCharsets.UTF_16));
//
    }

//    static String readFile(String path, Charset encoding)
//            throws IOException {
//        byte[] encoded = Files.readAllBytes(Paths.get(path));
//        return new String(encoded, encoding);
//    }
}