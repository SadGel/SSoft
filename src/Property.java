import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
Класс чтения пропертей из файла и анализ на ошибки
 */
public class Property {

    private static void parseLine(String strLine, int strNumber,StringBuilder key, StringBuilder val) { //Преобразование строки файла в "ключ" "значение"

        char[] chrMass = new char[strLine.length()];
        Boolean keyFlag = false;
        strLine.getChars(0,strLine.length(),chrMass,0);

        for (char el:chrMass) {

            if (el!='='&&!keyFlag) {

                key.append(el);

            }
            else
                if (el=='=') keyFlag = true;
                else {
                    val.append(el);
                }

        }
        //орбработка ошибок формата файла
        if (key.length() == 0||!keyFlag) {
            key.setLength(0);
            key.append("error");
            val.setLength(0);
            val.append("Ошибка в "+strNumber+ " строке файла SSoft.properties");
            return;
        }

        if (val.length() == 0){
            val.append("---");
        }
        //орбработка ошибок формата файла

    }

    private static void addToList(HashMap rez, StringBuilder key, StringBuilder val) {

        String sKey = key.toString();
        StringBuilder tempVal;
        if (rez.containsKey(sKey)) {
            tempVal = (StringBuilder)rez.get(sKey);
            tempVal.append("<br/>").append(val);
            rez.put(sKey,tempVal);
        }else {
            rez.put(sKey,val);
        }

    }

    public static HashMap Read (){ //чтение файла пропертей
        HashMap rez = new HashMap();

        try{
            FileInputStream fileInputStream = new FileInputStream("SSoft.properties");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            int strNumber = 1; // номер строки для отображение ошибок
            while ((strLine = bufferedReader.readLine()) != null){

                StringBuilder key = new StringBuilder();
                StringBuilder val = new StringBuilder();

                parseLine(strLine, strNumber, key, val);
                addToList(rez, key, val);

                //System.out.println(key.toString()+" "+val.toString());
                strNumber++;
            }
        }catch (IOException e){ //орбработка ошибок чтения файла
            StringBuilder key = new StringBuilder("error");
            StringBuilder val = new StringBuilder("Ошибка: "+e.toString());
            System.out.println("Ошибка: "+e.toString());
        }


        return rez;
    }


}


