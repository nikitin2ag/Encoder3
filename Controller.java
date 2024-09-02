package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;


public class Controller {

    @FXML
    private Button btnSourceFile, btnKeyFile, btnEncrypted, btnDecrypted;

    @FXML
    private Label labelSourceFilePath, labelKeyFilePath;

    @FXML
    private RadioButton rbECB, rbCBC, rbCFB, rbOFB, rbPCBC;

    private ToggleGroup mode = new ToggleGroup();

    private byte[] arrayFileBytes; // байты исходного файла
    private byte[] arrayKeyBytes; // байты файла ключа

    private boolean fileChoose = false;
    private boolean keyChoose = false;



    // кнопка выбора исходного файла
    public void BtnSourceFileAction() throws Exception {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            String sourceFile = selectedFile.getAbsolutePath(); // получаем абсолютный путь к файлу
            labelSourceFilePath.setText(sourceFile); // выводим абсолютный путь к файлу на экран
            arrayFileBytes = FileProcessing.readFile(sourceFile); // преобразуем файл в массив байтов
            System.out.print("\nБайты исходного файла: " + arrayFileBytes);
            fileChoose = true;
        } else {
            labelSourceFilePath.setText("..."); // выводим абсолютный путь к файлу на экран
        }
    }


    // кнопка выбора файла ключа
    public void BtnKeyFileAction() throws Exception {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            String keyFile = selectedFile.getAbsolutePath(); // получаем абсолютный путь к файлу
            labelKeyFilePath.setText(keyFile); // выводим абсолютный путь к файлу на экран
            arrayKeyBytes = FileProcessing.readFile(keyFile); // преобразуем файл в массив байтов
            System.out.print("\nБайты файла ключа: " + arrayKeyBytes);
            keyChoose = true;
        } else {
            labelKeyFilePath.setText("..."); // выводим абсолютный путь к файлу на экран
        }
    }


    // кнопка "Зашифровать"
    public void BtnEncryptedAction() throws Exception {

        // определяем выбранный режим
        rbECB.setToggleGroup(mode);
        rbCBC.setToggleGroup(mode);
        rbCFB.setToggleGroup(mode);
        rbOFB.setToggleGroup(mode);
        rbPCBC.setToggleGroup(mode);
        RadioButton rbMode = (RadioButton) mode.getSelectedToggle();
        System.out.print(rbMode.getText());

        if (fileChoose && keyChoose) {
            byte[] encryptedText = AesCipher.encrypt(arrayKeyBytes, arrayFileBytes, rbMode.getText()); // зашифрованные байты
            if (encryptedText != null)
                FileProcessing.writeFile(encryptedText, "encryptedFile");
            System.out.print("\nЗашифрованные байты: " + encryptedText);
        } else {
            ShowMessage("Необходимо выбрать исходный файл и файл ключа!");
        }
    }


    // кнопка "Дешифровать"
    public void BtnDecryptedAction() throws Exception {

        // определяем выбранный режим
        rbECB.setToggleGroup(mode);
        rbCBC.setToggleGroup(mode);
        rbCFB.setToggleGroup(mode);
        rbOFB.setToggleGroup(mode);
        rbPCBC.setToggleGroup(mode);
        RadioButton rbMode = (RadioButton) mode.getSelectedToggle();
        System.out.print(rbMode.getText());

        if (fileChoose && keyChoose) {
            byte[] decryptedText = AesCipher.decrypt(arrayKeyBytes, arrayFileBytes, rbMode.getText()); // дешифрованные байты
            if (decryptedText != null)
                FileProcessing.writeFile(decryptedText, "decryptedFile");
            System.out.print("\nДешифрованные байты: " + decryptedText);
        } else {
            ShowMessage("Необходимо выбрать исходный файл и файл ключа!");
        }
    }


    // показ сообщения
    static void ShowMessage(String contextText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}