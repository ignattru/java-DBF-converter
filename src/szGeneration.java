
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFUtils;
import com.linuxense.javadbf.DBFWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

public class szGeneration {
    public static void main() {
        Main.pw.println("Пускаем класс szGeneration");
        File inputFile = new File("data/scheme/sz.DBF");

        DBFReader reader = null;
        DBFWriter writer = null;
        FileOutputStream outputFile = null;
        try {
            outputFile = new FileOutputStream("..\\" + Main.year + "\\" + Main.dateCurrent + "\\" + "sz" + Main.day + Main.month + Main.year + ".DBF");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            reader = new DBFReader(new FileInputStream(inputFile));
            writer = new DBFWriter(outputFile, Charset.forName("IBM866"));

            DBFField[] fieldsDefinition = new DBFField[reader.getFieldCount()];
            for (int i = 0; i < fieldsDefinition.length; i++) {
                fieldsDefinition[i] = reader.getField(i);
            }
            writer.setFields(fieldsDefinition);
            Main.pw.println("Поля файла sz установлены");
            Main.pw.println("Начинаем запись из матрицы в файл sz");
            Object[] row = null;
            int j = 0; //Колво записей
            int x = 0;
            while ((row = reader.nextRecord()) != null) {
                if (x < deadallRead.numRecord) {
                    row[1] = deadallRead.matrix[22][j];
                    row[2] = deadallRead.matrix[24][j];
                    row[5] = deadallRead.matrix[0][j];
                    row[6] = deadallRead.matrix[1][j];
                    row[7] = deadallRead.matrix[2][j];
                    row[9] = deadallRead.matrix[14][j];
                    row[10] = deadallRead.matrix[13][j];
                    row[11] = deadallRead.matrix[12][j];
                    row[12] = deadallRead.matrix[18][j];
                    row[13] = deadallRead.matrix[19][j];
                    row[14] = deadallRead.matrix[20][j];
                    row[15] = deadallRead.matrix[21][j];
                    row[17] = deadallRead.matrix[3][j];
                    row[22] = deadallRead.matrix[4][j];
                    row[23] = deadallRead.matrix[11][j];
                    row[24] = deadallRead.matrix[25][j];
                    row[25] = deadallRead.matrix[26][j];
                    row[26] = deadallRead.matrix[27][j];
                    row[27] = deadallRead.matrix[28][j];
                    row[28] = deadallRead.matrix[29][j];
                    row[29] = deadallRead.matrix[30][j];
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
            Main.pw.println("============Генерация файлов sz и d0 закончена, конец программы============");
        }
    }
}
