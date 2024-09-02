package mainPack;

import java.util.Random;

public class Vigenere {

    //private static String characters = " АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюяABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.,:;!?-_()\"\'"; //символы алфавита
    private static final String characters = " абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz/*0123456789,.-?!:();";  //символы алфавита

    public static String vigCode(String text, String key) {
        StringBuilder ans = new StringBuilder(); //зашифрованный текст

        char[] textArray = text.toLowerCase()/*.replaceAll("\\W","").replaceAll("\\d","")*/.toCharArray(); //массив текста
        char[] keyArray = key.toLowerCase()/*.replaceAll("\\W","").replaceAll("\\d","")*/.toCharArray(); //массив ключа

        for (int i = 0; i < textArray.length; i++) {
            int num1 = characters.indexOf(textArray[i]); //номер буквы текста в алфавите
            int num2 = characters.indexOf(keyArray[i % keyArray.length]); //номер буквы ключа в алфавите
            int num = (num1 + num2) % characters.length(); //номер буквы шифра в алфавите
            char c = characters.charAt(num); //получаем нужную букву шифра
            ans.append(c);
        }
        return ans.toString();
    }

    public static String vigDecode(String cipher, String key) {

        StringBuilder ans = new StringBuilder(); //дешифрованный текст
        char[] cipherArray = cipher.toLowerCase().toCharArray(); //массив шифра
        char[] keyArray = key.toLowerCase().toCharArray(); //массив ключа

        for (int i = 0; i < cipherArray.length; i++) {

            int num1 = characters.indexOf(cipherArray[i]); //номер буквы шифра в алфавите
            int num2 = characters.indexOf(keyArray[i % keyArray.length]); //номер буквы ключа в алфавите

            int num = (num1 - num2 + characters.length()) % characters.length(); //номер буквы текста в алфавите

            char c = characters.charAt(num); //получаем нужную букву шифра
            ans.append(c);

        }

        return ans.toString();
    }

    public static String onGenerateKey(String text) {
        final Random r = new Random();
        int keyLength = 0;
        int num;
        StringBuilder genKey = new StringBuilder();

        if (text.length() != 0)
            keyLength = r.nextInt(text.length() - 1) + 2;
        else
            keyLength = r.nextInt(10 - 1) + 2;

        for (int i = 0; i < keyLength; i++) {
            num = r.nextInt(characters.length());
            char c = characters.charAt(num);
            genKey.append(c);
        }

        return genKey.toString();
    }
}