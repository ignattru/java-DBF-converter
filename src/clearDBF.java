import com.linuxense.javadbf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

public class clearDBF {
    public static void main() {

        System.out.println("========== пускаем класс clearDBF ============");

        File inputFile = new File("data/d_all.DBF");

        DBFReader reader = null;
        DBFWriter writer = null;
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("data/" + "_clear" + inputFile.getName() );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            reader = new DBFReader(new FileInputStream(inputFile));
            writer = new DBFWriter(output, Charset.forName("IBM866"));

            DBFField[] fieldsDefinition = new DBFField[reader.getFieldCount()];
            for (int i = 0; i < fieldsDefinition.length; i++) {
                fieldsDefinition[i] = reader.getField(i);
            }

            writer.setFields(fieldsDefinition);
            Object[] row = null;
            while ((row = reader.nextRecord()) != null) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = null;
                }
                writer.addRecord(row);
            }
            System.out.println("База очищена, " + inputFile.getName() + "Кодировка: " +  reader.getCharset());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
            DBFUtils.close(writer);
        }
    }
}
