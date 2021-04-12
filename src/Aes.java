public class Aes {

    public String[][] swapIndexes(String[][] matrix) {
        String[][] newMatrix = new String[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        return newMatrix;
    }

    public String[][] addRoundKey(String[][] matrix, String[][] key) {
        int m;
        int k;
        int len = matrix.length;
        String[][] xor = new String[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                m = Integer.parseInt(matrix[i][j], 16);
                k = Integer.parseInt(key[i][j], 16);
                xor[i][j] = String.format("%02x", m ^ k);
            }
        }
        return xor;
    }
}
