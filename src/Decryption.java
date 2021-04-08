import java.util.Arrays;

public class Decryption {

    public static void decrypt(String[] cypherText, String[] key) {
        String[] key1 = new String[key.length / 2];
        String[] key2 = new String[key.length / 2];
        System.arraycopy(key, 0, key1, 0, key1.length);
        System.arraycopy(key, key1.length, key2, 0, key2.length);

        String[][] key1mat = Processing.block2mat(key1);
        String[][] key2mat = Processing.block2mat(key2);
        int numOfBlocks = cypherText.length / 16;
        String[][] cypher;
        for (int i = 0; i < numOfBlocks; i++) {
            String[] block = Arrays.copyOfRange(cypherText, i, i + 16);
            String[][] mat = Processing.block2mat(block);
            cypher = aes1Invert(aes1Invert(mat, key2mat), key1mat);
            Processing.writeToFile(cypher, "2.txt");
        }
    }

    public static String[][] aes1Invert(String[][] mat, String[][] keyMat) {
        return swapIndexes(addRoundKey(mat,keyMat));
    }


    public static String[][] swapIndexes(String[][] matrix) {
        String[][] newMatrix = new String[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        return newMatrix;

    }

    public static String[][] addRoundKey(String[][] message, String[][] key) {
        int m = 0;
        int k = 0;
        int len = message.length;
        String[][] xor = new String[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < message[0].length; j++) {
                m = Integer.parseInt(message[i][j], 16);
                k = Integer.parseInt(key[i][j], 16);
                xor[i][j] = String.format("%02x", m ^ k);
            }
        }
        return xor;
    }
}