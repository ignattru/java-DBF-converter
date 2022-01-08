import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class deadallRead {

    public static int numRecord;
    public static int numFields;
    public static Object[] deadallFields = new Object[100];
    public static String[] deadallFieldsName  = new String[100];
    public static DBFDataType[] deadallFieldsType  = new DBFDataType[100];
    public static Integer[] deadallFieldsLength  = new Integer[100];
    public static Object[][] matrix; //попробуем двумерный массив, представим дбфку как матрицу

    public static void main() {
        Main.pw.println("Пускаем класс deadallRead" );
        File inputFile = new File("data/scheme/dAll.dbf");
        DBFReader reader = null;

        try {
            reader = new DBFReader(new FileInputStream(inputFile));

            // Читаю поля и назначаю
            DBFField[] fieldsDefinition = new DBFField[reader.getFieldCount()];
            for (int i = 0; i < fieldsDefinition.length; i++) {
                fieldsDefinition[i] = reader.getField(i);
                deadallFields[i] = reader.getField(i);
                deadallFieldsName[i] = fieldsDefinition[i].getName();
                deadallFieldsType[i] = fieldsDefinition[i].getType();
                deadallFieldsLength[i] = fieldsDefinition[i].getLength();
            }
            Main.pw.println("Делаем матрицу объектов для записи туда файла " + inputFile.getName());
            matrix = new Object[reader.getFieldCount()][reader.getRecordCount()];
            //Задаем матрицу размером с кол-во полей и записей в DBF
            Object[] row = null;
            int j = 0; //Колво записей
            int i = 0; //колво полей
            while ((row = reader.nextRecord()) != null) {
                for (i = 0; i < row.length; i++) {
                    matrix[i][j] = row[i];
                }
                j++;
            }
            numRecord = reader.getRecordCount();
            numFields = reader.getFieldCount();
            Main.pw.println("Матрица записана" );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
        }

    }
}
