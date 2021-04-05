import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class Processing {


    public static void writeToFile(String[][] cypher, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            for (String[] row: cypher ) {
                fos.write(hexStringToByteArray(Arrays.toString(row)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[] readFile(String pathString) {

        Path path = Paths.get(pathString);

        byte[] message = null;
        {
            try {
                message = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        char[] messageHex = byteArrayToHex(message).toCharArray(); // message as hex in char array
//        System.out.println(messageHex);

        String[] hexArray = new String[messageHex.length / 2];
        for (int i = 0; i < hexArray.length; i++) {
            hexArray[i] = messageHex[i * 2] + "" + messageHex[i * 2 + 1];
        }
//        for (int i = 0; i < 4 ; i++) {
//            for (int j = 0; j < 4 ; j++) {
//                System.out.print(matrix[i][j]);
//
//            }
//            System.out.println();
//        }
//
//        System.out.println("---------------");

        return hexArray;
    }

    public static String[][] block2mat(String[] hexArray){
        String[][] matrix = new String[4][4];
        int k = 0;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                matrix[j][i] = hexArray[k];
                k++;
            }
        }
        return matrix;
    }



    // TODO fix runtime maybe?
    public String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

//    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
//
//    public static String bytesToHex(byte[] bytes) {
//        byte[] hexChars = new byte[bytes.length * 2];
//        for (int j = 0; j < bytes.length; j++) {
//            int v = bytes[j] & 0xFF;
//            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
//            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
//        }
//        return new String(hexChars, StandardCharsets.UTF_8);
//    }
}