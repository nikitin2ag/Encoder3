package mainPack;

public class ASCII {

    public static String asciiCode(String text) {
        StringBuilder ascii = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            ascii.append((int) (text.toCharArray()[i])).append(" ");
        }
        return ascii.toString();
    }

    public static String asciiDecode(String ascii) {
        Controller controller = new Controller();
        StringBuilder text = new StringBuilder();
        int[] ints = new int[ascii.split(" ").length];
        for (int i = 0; i < ints.length; i++) {
            try {
                ints[i] = Integer.parseInt(ascii.split(" ")[i]);
            } catch (NumberFormatException e) {
                controller.showAlert("Выбранный криптоалгоритм, используемый в режиме ДЕКОДИРОВАНИЕ, не соответствует тому криптоалгоритму, который применялся при кодировании данного сообщения.");
                return " ";
            }
        }
        for (int anInt : ints) {
            text.append((char) anInt);
        }
        return text.toString();
    }
}
