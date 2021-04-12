import java.util.Arrays;

public class BreakInto {

    public static void breakIn(String[] message, String[] cypher, String outPath) {
//        String rand = "00000000000000000000000000000000";
//        String rand = "6C5F02D689C67D0125499F72CECAAE6C";
        String rand = "79BC2DFF8A4795AE2F74B87EC6F8641A";
        Aes aes = new Aes();
        int numOfBlocks = message.length / 16;
        String[][] kMat = Processing.block2mat(Processing.stringToHex(rand));
        String[][] key1;
        String[][] key2;
        String[][] messageMat;
        String[][] cypherMat;
        String[] blockCypher;
        String[] blockMessage;
        int m = 0;
        byte[] b1 = new byte[16];
        byte[] b2 = new byte[16];
        byte[] b = new byte[b1.length+b2.length];
        for (int i = 0; i < numOfBlocks; i++) {
            blockMessage = Arrays.copyOfRange(message, i*16, i*16 + 16);
            blockCypher = Arrays.copyOfRange(cypher, i*16, i*16 + 16);
            messageMat = Processing.block2mat(blockMessage);
            cypherMat = Processing.block2mat(blockCypher);
            key2 = aes.addRoundKey(aes.addRoundKey(messageMat, cypherMat), kMat);
            key1 = aes.swapIndexes(kMat);

        if (i == numOfBlocks-1)
        {
        for (int j = 0; j < key1.length; j++) {
            for (int k = 0; k < key1[0].length; k++) {
                b1[m] = Processing.hexStringToByte(key1[j][k]);
                b2[m] = Processing.hexStringToByte(key2[j][k]);
                m++;
            }
        }
            System.arraycopy(b1, 0, b, 0, b1.length );
            System.arraycopy(b2, 0, b, b1.length, b2.length );
            Processing.writeToFile(b, outPath);
        }


    }


    }

}