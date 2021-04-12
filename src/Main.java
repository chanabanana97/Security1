public class Main {


    public static void main(String[] args) {
        Processing proc = new Processing();
        String keyPath = args[2];
        String inputPath = args[4];
        String outputPath = args[6];
        String[] input = proc.readFile(inputPath);
        String[] key = proc.readFile(keyPath);

        if (args[0].equals("-e")) { //encryption
            Encryption.encrypt(input, key, outputPath);
        }

        if (args[0].equals("-d")) { //decryption

            Decryption.decrypt(input, key, outputPath);
        }

        if (args[0].equals("-b")) { //breakIn

            BreakInto.breakIn(key, input, outputPath);
        }


//        try {
//            byte[] f = Files.readAllBytes(Paths.get(outputPath));
//            byte[] c = Files.readAllBytes(Paths.get("C:\\Users\\Chana\\Desktop\\Security1\\src\\cipher_long"));
//            for (byte b : c) {
//                System.out.printf("%02X", b);
//            }
//            System.out.println();
//
//            for (byte b : f) {
//                System.out.printf("%02X", b);
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        for (byte b : f) {
//            System.out.printf("%02X", b);
        }

    }