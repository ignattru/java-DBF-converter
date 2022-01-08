
import com.linuxense.javadbf.*;
import java.nio.charset.Charset;
import java.io.*;

public class d0Generation {
    public static void main() {
        Main.pw.println("Пускаем класс d0Generation");
        File inputFile = new File("data/scheme/d.DBF");

        DBFReader reader = null;
        DBFWriter writer = null;
        FileOutputStream baos = null;
        try {
            baos = new FileOutputStream("..\\" + Main.year + "\\" + Main.dateCurrent + "\\" + "D" + Main.month + Main.year + ".DBF");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            reader = new DBFReader(new FileInputStream(inputFile));
            writer = new DBFWriter(baos, Charset.forName("IBM866"));

            DBFField[] fieldsDefinition = new DBFField[reader.getFieldCount()];
            for (int i = 0; i < fieldsDefinition.length; i++) {
                fieldsDefinition[i] = reader.getField(i);
            }

            writer.setFields(fieldsDefinition);
            Main.pw.println("Поля файла d0 установлены");
            Main.pw.println("Записываем объекты из матрицы в d0");
            Object[] row = null;
            int j = 0; //Колво записей
            int x = 0;
            while ((row = reader.nextRecord()) != null) {
                if (x < deadallRead.numRecord) {
                    row[0] = deadallRead.matrix[0][j];
                    row[1] = deadallRead.matrix[1][j];
                    row[2] = deadallRead.matrix[2][j];
                    row[4] = deadallRead.matrix[4][j];
                    row[10] = deadallRead.matrix[13][j];
                    row[11] = deadallRead.matrix[14][j];
                    row[13] = deadallRead.matrix[18][j];
                    row[14] = deadallRead.matrix[19][j];
                    row[15] = deadallRead.matrix[19][j];
                    row[16] = deadallRead.matrix[21][j];
                    row[17] = deadallRead.matrix[11][j];
                    row[18] = deadallRead.matrix[22][j];
                    row[20] = deadallRead.matrix[24][j];
                    j++;
                    x++;
                    writer.addRecord(row);
               }
            }
            Main.pw.println("Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
            DBFUtils.close(writer);
        }
    }
}
