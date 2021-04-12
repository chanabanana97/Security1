import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Processing {


    public static void writeToFile(byte[] b, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(b);

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
        String messageHex = byteArrayToHex(message);
        return stringToHex(messageHex);
    }

    public static String[] stringToHex(String messageHex){

        String[] hexArray = new String[messageHex.length() / 2];
        for (int i = 0; i < hexArray.length; i++) {
            hexArray[i] = messageHex.charAt(i * 2) + "" + messageHex.charAt(i * 2 + 1);
        }
    return hexArray;
    }

    public static String[][] block2mat(String[] hexArray){
        String[][] matrix = new String[4][4];
        int k = 0;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                matrix[i][j] = hexArray[k];
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

//    public static byte[] hexMatrixToByteArr(String[][] data){
//        int k=0;
//        byte[] b = new byte[data.length * data[0].length];
//        for (int i = 0; i <data.length ; i++) {
//            for (int j = 0; j <data[0].length ; j++) {
//                b[k] = hexStringToByte(data[i][j]);
//            }
//        }
//        return b;
//    }
    public static byte hexStringToByte(String s) {
        byte data = (byte) ((Character.digit(s.charAt(0), 16) << 4)
                    + Character.digit(s.charAt(1), 16));
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