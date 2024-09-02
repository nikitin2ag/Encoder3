package mainPack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ToggleButton changeEncodingRegimeTBtn, showPassword, langTBtn;
    @FXML
    private Label label;
    @FXML
    private TextArea inputtedText, transformedText, infoText;
    @FXML
    private PasswordField password;
    @FXML
    private Button encodeBtn, saveBtn;
    @FXML
    private ComboBox<String> boxCodes;

    private final ObservableList<String> itemCodes = FXCollections.observableArrayList("Код Морзе", "Символика ASCII", "Шифр Виженера", "Шифр AES");
    private int nameFileCount = 0;
    private String key = "";
    private boolean checkCodingRegime = true;
    private final String[] infoCodes = new String[]{
            "В режиме кодирования вы сами выбирате нужный код/шифр.\n\nОбычно кодом называют взаимно однозначное отображение конечного упорядоченного множеств символов, принадлежащих некоторому конечному алфавиту на иное, не обязательно упорядоченное, как правило более обширное множество символов для кодирования передачи, хранения или преобразования информации.\n\nШифр — система обратимых преобразований, зависящая от некоторого секретного параметра и предназначенная для обеспечения секретности передаваемой информации.",
            "Азбука Мо́рзе, «Морзя́нка», Код Мо́рзе — способ знакового кодирования, представление букв алфавита, цифр, знаков препинания и других символов последовательностью сигналов: длинных (тире) и коротких (точек).\n\nЗа единицу времени принимается длительность одной точки. Длительность тире равна трём точкам. Пауза между элементами одного знака — одна точка, между знаками в слове — 3 точки, между словами — 7 точек.\n\nКод Морзе назван в честь американского изобретателя и художника Сэмюэля Морзе.",
            "ASCII (англ. American standard code for information interchange) — кодировочная таблица (целочисленный набор), где некоторым распространённым печатным и непечатным символам сопоставлены числовые коды. Таблица была разработана и стандартизована в США, в 1963 году.",
            "Шифр Виженера состоит из последовательности нескольких шифров Цезаря с различными значениями сдвига. Для зашифровывания может использоваться таблица алфавитов, так называемая \"tabula recta\" или таблица (квадрат) Виженера.\n\nПрименительно к латинскому алфавиту код Виженера составляется из строк по 26 символов, причём каждая следующая строка сдвигается на несколько позиций. Таким образом, в таблице получается 26 различных шифров Цезаря.\n\nНа каждом этапе шифрования используются различные алфавиты, выбираемые в зависимости от символа ключевого слова.",
            "Advanced Encryption Standard (AES), также известный как \"Rijndael\" (произносится [rɛindaːl] — Рейндал) — симметричный алгоритм блочного шифрования (размер блока 128 бит, ключ 128/192/256 бит), принятый в качестве стандарта шифрования правительством США по результатам конкурса AES. Этот алгоритм хорошо проанализирован и сейчас широко используется, как это было с его предшественником DES.\n\nНациональный институт стандартов и технологий США (англ. National Institute of Standards and Technology, NIST) опубликовал спецификацию AES 26 ноября 2001 года после пятилетнего периода, в ходе которого были созданы и оценены 15 кандидатур.\n\n26 мая 2002 года AES был объявлен стандартом шифрования.\n\nПо состоянию на 2009 год AES является одним из самых распространённых алгоритмов симметричного шифрования. Поддержка ускорения AES была введена фирмой Intel в семейство процессоров x86 начиная с Arrandale в 2010 году, а затем на процессорах Sandy Bridge; фирмой AMD - в Bulldozer с 2011 года.",
            "В режиме декодирования вам требуется ввести специальный ключ, введённый ещё в режиме кодирования.\n\nКлюч шифрования (пароль) - это секретная последовательность символов, используемая криптографическими алгоритмами для расшифровки данных."
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoText.setText(infoCodes[0]);
        encodeBtn.setDisable(true);

        changeEncodingRegimeTBtn.setGraphic(new ImageView("unlock.png"));

        password.textProperty().addListener((observable, oldValue, newValue) -> key = newValue);

//        changeEncodingRegimeTBtn.setOnAction(event -> {
//            if (boxCodes.getValue().equals("Код Морзе") || boxCodes.getValue().equals("AES")) {
//                password.setDisable(false);
//                showPassword.setDisable(false);
//            }
//        });
        showPassword.setGraphic(new ImageView("eye.png"));

        showPassword.setOnAction(event -> {
            if (showPassword.isSelected()) {
                password.setPromptText(key);
                password.setDisable(true);
                password.setText("");
            } else {
                password.setText(password.getPromptText());
                password.setDisable(false);
                password.setPromptText("");
            }
        });

        boxCodes.setItems(itemCodes);
        boxCodes.setOnAction(event -> {
            langTBtn.setVisible(false);
            if (checkCodingRegime)
                password.clear();
            switch (boxCodes.getValue()) {
                case "Код Морзе":
                    password.setDisable(true);
                    password.clear();
                    showPassword.setDisable(true);
                    infoText.setText(infoCodes[1]);
                    break;
                case "Символика ASCII":
                    password.setDisable(true);
                    password.clear();
                    showPassword.setDisable(true);
                    infoText.setText(infoCodes[2]);
                    break;
                case "Шифр Виженера":
                    password.setDisable(false);
                    showPassword.setDisable(false);
                    infoText.setText(infoCodes[3]);
                    break;
                case "Шифр AES":
                    password.setDisable(false);
                    showPassword.setDisable(false);
                    infoText.setText(infoCodes[4]);
                    break;
            }
        });

        inputtedText.textProperty().addListener((observable, oldValue, newValue) -> {
            langTBtn.setVisible(false);
            saveBtn.setDisable(true);
            transformedText.setText("");
            transformedText.setDisable(true);
            encodeBtn.setDisable(newValue.replaceAll("\\s+", "").equals(""));
        });
    }

    public void onChangeRegime() {
        password.clear();
        langTBtn.setVisible(false);
        password.clear();
        transformedText.setText("");
        if (changeEncodingRegimeTBtn.isSelected()) {
            showPassword.setDisable(false);
            password.setDisable(false);
            changeEncodingRegimeTBtn.setGraphic(new ImageView("lock.png"));
            label.setText("Режим: ДЕКОДИРОВАНИЕ");
            boxCodes.setValue("Выберите режим декодирования");
            encodeBtn.setText("Декодировать");
            if (!checkCodingRegime) {
                if (boxCodes.getValue().equals(itemCodes.get(0)) || boxCodes.getValue().equals(itemCodes.get(1))) {
                    password.setDisable(true);
                    showPassword.setDisable(true);
                } else if (!boxCodes.getValue().equals(boxCodes.getPromptText())) {
                    password.setDisable(true);
                    showPassword.setDisable(true);
                }
            }
            infoText.setText(infoCodes[5]);
            checkCodingRegime = false;
        } else {
            label.setText("Режим: КОДИРОВАНИЕ");
            encodeBtn.setText("Закодировать");
            boxCodes.setValue("Выберите режим кодирования");
            password.setDisable(true);
            showPassword.setDisable(true);
            infoText.setText(infoCodes[0]);
            checkCodingRegime = true;
            changeEncodingRegimeTBtn.setGraphic((new ImageView("unlock.png")));
        }
    }

    public void onEncode() {
        if (checkCodingRegime) {
            if (!password.isDisable() && password.getText() == null)
                showAlert("Введите пароль!");
            else if (!infoText.getText().equals(infoCodes[0])) {
                transformedText.setDisable(false);
                saveBtn.setDisable(false);
                switch (boxCodes.getValue()) {
                    case "Код Морзе":
                        transformedText.setText(Morse.morseCode(inputtedText.getText()));
                        break;
                    case "Символика ASCII":
                        transformedText.setText(ASCII.asciiCode(inputtedText.getText()));
                        break;
                    case "Шифр Виженера":
                        if (showPassword.isSelected())password.setText(password.getPromptText());
                        password.setDisable(false);
                        if (password.getText().equals("")) {
                            showAlert("Введите ключ!");
                            transformedText.setDisable(true);
                            return;
                        } else transformedText.setText(Vigenere.vigCode(inputtedText.getText(), key));
                        break;
                    case "Шифр AES":
                        transformedText.setText("В разработке...");
                        //transformedText.setText(new String(AesCipher.code(key.getBytes(), inputtedText.getText().getBytes())));
                        break;
                    default:
                        showAlert("Что-то пошло не так!\t:(");
                }
            } else
                showAlert("Выберите кодировку!\nНаходясь в режиме КОДИРОВАНИЕ, Вам требуется выбрать алгоритм кодирования.\nДля этого найдите кнопку \"Выберите алгоритм кодирования\" (см. левый верхний угол под названием выбранного режима). Нажмите на неё − и раскроется всплывающий список криптоалгоритмов.");

        } else {
            transformedText.setDisable(false);
            saveBtn.setDisable(false);
            switch (boxCodes.getValue()) {
                case "Код Морзе":
                    morseDecode();
                    langTBtn.setVisible(true);
                    password.setDisable(true);
                    showPassword.setDisable(true);
                    break;
                case "Символика ASCII":
                    transformedText.setText(ASCII.asciiDecode(inputtedText.getText()));
                    password.setDisable(true);
                    showPassword.setDisable(true);
                    break;
                case "Шифр Виженера":
                    if (showPassword.isSelected())password.setText(password.getPromptText());
                    password.setDisable(false);
                        if (password.getText().isEmpty()) {
                            showAlert("Введите ключ!");
                            transformedText.setDisable(true);
                            transformedText.clear();
                            return;
                        }
                    transformedText.setText(Vigenere.vigDecode(inputtedText.getText(), key));
                    password.setDisable(false);
                    showPassword.setDisable(false);
                    break;
                case "Шифр AES":
                    transformedText.setText("В разработке...");
                    //transformedText.setText(new String(AesCipher.decode(key.getBytes(), inputtedText.getText().getBytes())));
                    password.setDisable(false);
                    showPassword.setDisable(false);
                    break;
                default:
                    showAlert("Выберите алгоритм декодирования!");
            }
        }
    }


    public void morseDecode() {
        password.setDisable(true);
        showPassword.setDisable(true);
        String[] coded_chars = inputtedText.getText().split(" ");
        if (langTBtn.isSelected()) {
            langTBtn.setText("ENG");
        } else {
            langTBtn.setText("RUS");
        }
        Morse.morseDecode(coded_chars, langTBtn.getText());
        transformedText.setText((String.join("", coded_chars)));
    }

    public void onFileSave() throws IOException {
        nameFileCount++;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите место хранения файла...");
        if (checkCodingRegime)
            fileChooser.setInitialFileName(nameFileCount + "-e зашифрованный текст");
        else fileChooser.setInitialFileName(nameFileCount + "-e дешифрованный текст");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("\n" + "Текстовый документ", "*.txt"));
        File newFile = fileChooser.showSaveDialog(saveBtn.getScene().getWindow());
        try {
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(transformedText.getText());
            fileWriter.close();
        } catch (NullPointerException e) {
            nameFileCount--;
            System.out.println("Произошла ошибка \"" + e + "\" из-за отмены сохранения файла!");
        }
    }

    public void showAlert(String alertText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Произошла ошибка!");
        alert.initOwner(showPassword.getScene().getWindow());
        alert.setHeaderText(null);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

}