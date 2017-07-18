import java.io.*;
import java.util.HashMap;

/**
 Класс генерации html страницы
 */

public class Html_page {

    private static String findKey(String strLine) { //ищем в строке из шаблона ключ для вставки из пропертей

        StringBuilder sKey = new StringBuilder();

        char[] chrMass = new char[strLine.length()];
        Boolean keyFlag = false;
        strLine.getChars(0, strLine.length(), chrMass, 0);

        for (char el : chrMass) {

            if (el == '[') {
                keyFlag = true;
                continue;
            }
            if (el == ']') break;

            if (keyFlag) {
                sKey.append(el);
            }
        }

        if (!keyFlag) return null;

        return sKey.toString();
    }

    public static void generate(HashMap propertyList) {
        String strLine, key, newStrLine, nameTemp;

        try {
            FileWriter writer = new FileWriter("index.html", false);

            if (propertyList.containsKey("error")) {
                nameTemp = "res/error.html";
            }
            else {
                nameTemp = "res/temp.html";
            }

            FileInputStream fileInputStream = new FileInputStream(nameTemp);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            while ((strLine = bufferedReader.readLine()) != null) {

                key = findKey(strLine);
                if (key != null) {
                    if (propertyList.containsKey(key)) {
                        newStrLine = strLine.replace("[" + key + "]", propertyList.get(key).toString());
                    } else {
                        newStrLine = strLine.replace("[" + key + "]", "---");
                    }
                } else {
                    newStrLine = strLine;
                }

                writer.write(newStrLine);
                writer.append('\n');

            }
            writer.flush();
        } catch (IOException e) { //орбработка ошибок чтения файла
            System.out.println("Ошибка: " + e.toString());
        }

    }

}
