package mainPack;

public class Morse {

    public static String changeSymbolsIntoMorse(String origin_char) {
        if (origin_char.equalsIgnoreCase("a") || origin_char.equalsIgnoreCase("а"))
            return "·-";
        if (origin_char.equalsIgnoreCase("b") || origin_char.equalsIgnoreCase("б"))
            return "-···";
        if (origin_char.equalsIgnoreCase("c") || origin_char.equalsIgnoreCase("ц"))
            return "-·-·";
        if (origin_char.equalsIgnoreCase("d") || origin_char.equalsIgnoreCase("д"))
            return "-··";
        if (origin_char.equalsIgnoreCase("e") || origin_char.equalsIgnoreCase("е"))
            return "·";
        if (origin_char.equalsIgnoreCase("f") || origin_char.equalsIgnoreCase("ф"))
            return "··-·";
        if (origin_char.equalsIgnoreCase("g") || origin_char.equalsIgnoreCase("г"))
            return "--·";
        if (origin_char.equalsIgnoreCase("h") || origin_char.equalsIgnoreCase("х"))
            return "····";
        if (origin_char.equalsIgnoreCase("i") || origin_char.equalsIgnoreCase("и"))
            return "··";
        if (origin_char.equalsIgnoreCase("j") || origin_char.equalsIgnoreCase("й"))
            return "·---";
        if (origin_char.equalsIgnoreCase("k") || origin_char.equalsIgnoreCase("к"))
            return "-·-";
        if (origin_char.equalsIgnoreCase("l") || origin_char.equalsIgnoreCase("л"))
            return "·-··";
        if (origin_char.equalsIgnoreCase("m") || origin_char.equalsIgnoreCase("м"))
            return "--";
        if (origin_char.equalsIgnoreCase("n") || origin_char.equalsIgnoreCase("н"))
            return "-·";
        if (origin_char.equalsIgnoreCase("o") || origin_char.equalsIgnoreCase("о"))
            return "---";
        if (origin_char.equalsIgnoreCase("p") || origin_char.equalsIgnoreCase("п"))
            return "·--·";
        if (origin_char.equalsIgnoreCase("q") || origin_char.equalsIgnoreCase("щ"))
            return "--·-";
        if (origin_char.equalsIgnoreCase("r") || origin_char.equalsIgnoreCase("р"))
            return "·-·";
        if (origin_char.equalsIgnoreCase("s") || origin_char.equalsIgnoreCase("с"))
            return "···";
        if (origin_char.equalsIgnoreCase("t") || origin_char.equalsIgnoreCase("т"))
            return "-";
        if (origin_char.equalsIgnoreCase("u") || origin_char.equalsIgnoreCase("у"))
            return "··-";
        if (origin_char.equalsIgnoreCase("v") || origin_char.equalsIgnoreCase("ж"))
            return "···-";
        if (origin_char.equalsIgnoreCase("w") || origin_char.equalsIgnoreCase("в"))
            return "·--";
        if (origin_char.equalsIgnoreCase("x") || origin_char.equalsIgnoreCase("ь"))
            return "-··-";
        if (origin_char.equalsIgnoreCase("y") || origin_char.equalsIgnoreCase("ы"))
            return "-·--";
        if (origin_char.equalsIgnoreCase("z") || origin_char.equalsIgnoreCase("з"))
            return "--··";
        if (origin_char.equalsIgnoreCase("я"))
            return "·-·-";
        if (origin_char.equals("0"))
            return "-----";
        if (origin_char.equals("1"))
            return "·----";
        if (origin_char.equals("2"))
            return "··---";
        if (origin_char.equals("3"))
            return "···--";
        if (origin_char.equals("4"))
            return "····-";
        if (origin_char.equals("5"))
            return "·····";
        if (origin_char.equals("6"))
            return "-····";
        if (origin_char.equals("7"))
            return "--···";
        if (origin_char.equals("8"))
            return "---··";
        if (origin_char.equals("9"))
            return "----·";
        if (origin_char.equals("·"))
            return "·-·-";
        if (origin_char.equals(","))
            return "--··--";
        if (origin_char.equals("?"))
            return "··--··";
        if (origin_char.equals("@"))
            return "·−−·−·";
        if (origin_char.equals("!"))
            return "−−··−−";
        if (origin_char.equals("(") | origin_char.equals(")"))
            return "−·−−·−";
        if (origin_char.equalsIgnoreCase("ш"))
            return "----";
        if (origin_char.equalsIgnoreCase("э"))
            return "··-··";
        if (origin_char.equalsIgnoreCase("ъ"))
            return "--·--";
        if (origin_char.equalsIgnoreCase("ч"))
            return "---·";
        if (origin_char.equalsIgnoreCase("ю"))
            return "··--";
        if (origin_char.equals("_"))
            return "··−−·−";
        if (origin_char.equals(" "))
            return "−···−";
        if (origin_char.equals(";"))
            return "−·−·−·";
        return origin_char;
    }

    public static String morseCode(String origin_txt) {
        String[] origin_chars = origin_txt.split("");
        for (int i = 0; i < origin_chars.length; i++) {
            origin_chars[i] = Morse.changeSymbolsIntoMorse(origin_chars[i]);
        }
        return String.join(" ", origin_chars);
    }

    public static void morseDecode(String[] coded_chars, String langBtn) {
        int bracketsCounter = 0;
        if (langBtn.equals("ENG")) {
            for (int i = 0; i < coded_chars.length; i++) {
                if (coded_chars[i].equals("−·−−·−"))
                    bracketsCounter++;
                coded_chars[i] = Morse.changeSymbolsFromMorseEng(coded_chars[i], bracketsCounter);
            }
        } else for (int i = 0; i < coded_chars.length; i++) {
            if (coded_chars[i].equals("−·−−·−"))
                bracketsCounter++;
            coded_chars[i] = changeSymbolsFromMorseRus(coded_chars[i], bracketsCounter);

        }
    }

    public static String changeSymbolsFromMorseRus(String encoded_char, int bracketsCounter) {
        if (encoded_char.equals("·-"))
            return "А";
        if (encoded_char.equals("-···"))
            return "Б";
        if (encoded_char.equals("-·-·"))
            return "Ц";
        if (encoded_char.equals("-··"))
            return "Д";
        if (encoded_char.equals("·"))
            return "Е";
        if (encoded_char.equals("··-·"))
            return "Ф";
        if (encoded_char.equals("--·"))
            return "Г";
        if (encoded_char.equals("····"))
            return "Х";
        if (encoded_char.equals("··"))
            return "И";
        if (encoded_char.equals("·---"))
            return "Й";
        if (encoded_char.equals("-·-"))
            return "К";
        if (encoded_char.equals("·-··"))
            return "Л";
        if (encoded_char.equals("--"))
            return "М";
        if (encoded_char.equals("-·"))
            return "Н";
        if (encoded_char.equals("---"))
            return "О";
        if (encoded_char.equals("·--·"))
            return "П";
        if (encoded_char.equals("--·-"))
            return "Щ";
        if (encoded_char.equals("·-·"))
            return "Р";
        if (encoded_char.equals("···"))
            return "С";
        if (encoded_char.equals("-"))
            return "Т";
        if (encoded_char.equals("··-"))
            return "У";
        if (encoded_char.equals("···-"))
            return "Ж";
        if (encoded_char.equals("·--"))
            return "В";
        if (encoded_char.equals("-··-"))
            return "Ь";
        if (encoded_char.equals("-·--"))
            return "Ы";
        if (encoded_char.equals("--··"))
            return "З";
        if (encoded_char.equals("·-·-"))
            return "Я";
        if (encoded_char.equals("----"))
            return "Ш";
        if (encoded_char.equals("··-··"))
            return "Э";
        if (encoded_char.equals("--·--"))
            return "Ъ";
        if (encoded_char.equals("---·"))
            return "Ч";
        if (encoded_char.equals("··--"))
            return "Ю";
        if (encoded_char.equals("-----"))
            return "0";
        if (encoded_char.equals("·----"))
            return "1";
        if (encoded_char.equals("··---"))
            return "2";
        if (encoded_char.equals("···--"))
            return "3";
        if (encoded_char.equals("····-"))
            return "4";
        if (encoded_char.equals("·····"))
            return "5";
        if (encoded_char.equals("-····"))
            return "6";
        if (encoded_char.equals("--···"))
            return "7";
        if (encoded_char.equals("---··"))
            return "8";
        if (encoded_char.equals("----·"))
            return "9";
        if (encoded_char.equals("······"))
            return ".";
        if (encoded_char.equals("−−−···"))
            return ":";
        if (encoded_char.equals("--··--"))
            return ",";
        if (encoded_char.equals("··--··"))
            return "?";
        if (encoded_char.equals("−−··−−"))
            return "!";
        if (encoded_char.equals("·−−·−·"))
            return "@";
        if (encoded_char.equals("··−−·−"))
            return "_";
        if (encoded_char.equals("−···−"))
            return " ";
        if (encoded_char.equals("−·−−·−")) {
            if (bracketsCounter % 2 == 1)
                return "(";
            else return ")";
        }
        if (encoded_char.equals("−·−·−·"))
            return ";";
        return encoded_char;
    }

    public static String changeSymbolsFromMorseEng(String encoded_char, int bracketsCounter) {
        if (encoded_char.equals("\n··−·−"))
            return "";
        if (encoded_char.equals("−·−·−·"))
            return ";";
        if (encoded_char.equals("·-"))
            return "A";
        if (encoded_char.equals("-···"))
            return "B";
        if (encoded_char.equals("-·-·"))
            return "C";
        if (encoded_char.equals("-··"))
            return "D";
        if (encoded_char.equals("·"))
            return "E";
        if (encoded_char.equals("··-·"))
            return "F";
        if (encoded_char.equals("--·"))
            return "G";
        if (encoded_char.equals("····"))
            return "H";
        if (encoded_char.equals("··"))
            return "I";
        if (encoded_char.equals("·---"))
            return "J";
        if (encoded_char.equals("-·-"))
            return "K";
        if (encoded_char.equals("·-··"))
            return "L";
        if (encoded_char.equals("--"))
            return "M";
        if (encoded_char.equals("-·"))
            return "N";
        if (encoded_char.equals("---"))
            return "O";
        if (encoded_char.equals("·--·"))
            return "P";
        if (encoded_char.equals("--·-"))
            return "Q";
        if (encoded_char.equals("·-·"))
            return "R";
        if (encoded_char.equals("···"))
            return "S";
        if (encoded_char.equals("-"))
            return "T";
        if (encoded_char.equals("··-"))
            return "U";
        if (encoded_char.equals("···-"))
            return "V";
        if (encoded_char.equals("·--"))
            return "W";
        if (encoded_char.equals("-··-"))
            return "X";
        if (encoded_char.equals("-·--"))
            return "Y";
        if (encoded_char.equals("--··"))
            return "Z";
        if (encoded_char.equals("-----"))
            return "0";
        if (encoded_char.equals("·----"))
            return "1";
        if (encoded_char.equals("··---"))
            return "2";
        if (encoded_char.equals("···--"))
            return "3";
        if (encoded_char.equals("····-"))
            return "4";
        if (encoded_char.equals("·····"))
            return "5";
        if (encoded_char.equals("-····"))
            return "6";
        if (encoded_char.equals("--···"))
            return "7";
        if (encoded_char.equals("---··"))
            return "8";
        if (encoded_char.equals("----·"))
            return "9";
        if (encoded_char.equals("······"))
            return ".";
        if (encoded_char.equals("−−−···"))
            return ":";
        if (encoded_char.equals("--··--"))
            return ",";
        if (encoded_char.equals("··--··"))
            return "?";
        if (encoded_char.equals("−−··−−"))
            return "!";
        if (encoded_char.equals("·−−·−·"))
            return "@";
        if (encoded_char.equals("··−−·−"))
            return "_";
        if (encoded_char.equals("−···−"))
            return " ";
        if (encoded_char.equals("−·−−·−")) {
            if (bracketsCounter % 2 == 1)
                return "(";
            else return ")";
        }
        return encoded_char;
    }
}