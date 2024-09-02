package mainPack;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

class AesCipher {

    static byte[] code(byte[] secretKey, byte[] plainText) {

        try {
            if (isKeyLengthValid(secretKey)) {
                throw new Exception("Длина ключа должна быть 128, 192 или 256 бит!");
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[cipher.getBlockSize()];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] c = cipher.doFinal(plainText);

            return ByteBuffer.wrap(new byte[iv.length + c.length]).put(iv).put(c).array();
        } catch (Throwable cause) {
            System.out.print(cause.getMessage());
        }
        return null;
    }

    static byte[] decode(byte[] secretKey, byte[] cipherText) {

        try {
            if (isKeyLengthValid(secretKey)) throw new Exception("Длина ключа должна быть 128, 192 или 256 бит!");

            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

            byte[] iv = new byte[cipher.getBlockSize()];
            System.arraycopy(cipherText, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] cipherTextNew = new byte[cipherText.length - iv.length];
            System.arraycopy(cipherText, 16, cipherTextNew, 0, cipherTextNew.length);

            return cipher.doFinal(cipherTextNew);
        } catch (Throwable cause) {
            System.out.print(cause.getMessage());
        }

        return null;
    }


    private static boolean isKeyLengthValid(byte[] key) {
        return (key.length != 16 && key.length != 24 && key.length != 32);
    }


    // генерация инициализирующего вектора
    private static String genInitVector() {

        Random r = new Random();
        StringBuilder builder = new StringBuilder();

        for (int j = 0; j < 16; j++) {
            char code = (char) (r.nextInt(94) + 33); // символы с кодами от 33 по 126
            builder.append(Character.toString(code));
        }

        return builder.toString();
    }

}