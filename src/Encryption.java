import java.util.ArrayList;
import java.util.Arrays;

public class Encryption {

    public static void encrypt(String[] message, String[] key, String outPath) {
        String[] key1 = new String[key.length / 2];
        String[] key2 = new String[key.length / 2];
        System.arraycopy(key, 0, key1, 0, key1.length);
        System.arraycopy(key, key1.length, key2, 0, key2.length);

        int m = 0;
        String[][] key1mat = Processing.block2mat(key1);
        String[][] key2mat = Processing.block2mat(key2);
        int numOfBlocks = message.length / 16;
        String[][] cypher;
        byte[] b = new byte[message.length];
        for (int i = 0; i < numOfBlocks; i++) {
            String[] block = Arrays.copyOfRange(message, i * 16, (i * 16) + 16);
            String[][] mat = Processing.block2mat(block);
            cypher = aes1(aes1(mat, key1mat), key2mat);

            for (int j = 0; j < cypher.length; j++) {
                for (int k = 0; k < cypher[0].length; k++) {
                    b[m++] = Processing.hexStringToByte(cypher[j][k]);
                }

            }
        }
        Processing.writeToFile(b, outPath);

    }

    public static String[][] aes1(String[][] mat, String[][] keyMat) {
        Aes aes = new Aes();
        return aes.addRoundKey(aes.swapIndexes(mat), keyMat);
    }

}
