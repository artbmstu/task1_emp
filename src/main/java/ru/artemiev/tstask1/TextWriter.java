package ru.artemiev.tstask1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TextWriter {

    TextWriter(String[] args, List<List<String>> changeVariations, List<String> depInfo) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(args[0]);
            for (int i = 0; i < changeVariations.size(); i++) {
                for (int j = 0; j < changeVariations.get(i).size(); j++) {
                    writer.write(changeVariations.get(i).get(j) + " -> " + depInfo.get(i) + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
        } catch (NullPointerException np){
            System.out.println("Не заданы параметры для записи. Запустите метод checkTransfer");
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка закрытия потока FileWriter");
            }
        }

    }
}
