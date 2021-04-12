import java.util.Arrays;

public class Decryption {

    public static void decrypt(String[] cypherText, String[] key, String outPath) {
        String[] key1 = new String[key.length / 2];
        String[] key2 = new String[key.length / 2];
        System.arraycopy(key, 0, key1, 0, key1.length);
        System.arraycopy(key, key1.length, key2, 0, key2.length);

        int m = 0;
        String[][] key1mat = Processing.block2mat(key1);
        String[][] key2mat = Processing.block2mat(key2);
        int numOfBlocks = cypherText.length / 16;
        byte[] b = new byte[cypherText.length];
        String[][] message;
        for (int i = 0; i < numOfBlocks; i++) {
            String[] block = Arrays.copyOfRange(cypherText, i*16, (i*16) + 16);
            String[][] mat = Processing.block2mat(block);
            message = aes1Invert(aes1Invert(mat, key2mat), key1mat);
            for (int j = 0; j < message.length; j++) {
                for (int k = 0; k < message[0].length; k++) {
                    b[m++] = Processing.hexStringToByte(message[j][k]);
                }

            }
        }
            Processing.writeToFile(b, outPath);
        }


    public static String[][] aes1Invert(String[][] mat, String[][] keyMat) {
        Aes aes = new Aes();
        return aes.swapIndexes(aes.addRoundKey(mat,keyMat));
    }

}