package sample;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessing {


    // чтение байтов из файла
    static byte[] readFile(String fileName) throws Exception {
        return Files.readAllBytes(Paths.get(fileName));
    }


    // запись байтов в файл
    static void writeFile(byte[] arrayBytes, String fileName) throws Exception {

        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(arrayBytes, 0, arrayBytes.length);
        fos.flush();
        fos.close();

    }


}