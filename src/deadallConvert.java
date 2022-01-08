import com.linuxense.javadbf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class deadallConvert {

        public static int numRecord;
        public static int numFields;

        public static void main() {
            Main.pw.println("Пускаем класс deadallConvert" );
            File inputFile = new File("data/dead_all.dbf");

            DBFReader reader = null;
            DBFWriter writer = null;
            FileOutputStream baos = null;
            try {
                baos = new FileOutputStream("data/scheme/dAll.dbf");
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
                fieldsDefinition[4].setType(DBFDataType.DATE);
                fieldsDefinition[11].setType(DBFDataType.DATE);
                /* Уберу преобразование поля flatl в NUMERIC 4 так как в него записывают мусор.
                fieldsDefinition[21].setType(DBFDataType.NUMERIC);
                fieldsDefinition[21].setLength(4);*/
                fieldsDefinition[24].setType(DBFDataType.DATE);

                writer.setFields(fieldsDefinition);
                Main.pw.println("Поля с датами сконвертированы и записаны в dAll.dbf" );
                SimpleDateFormat sdtF = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
                Main.pw.println("Начинаю запись строк в dAll.dbf" );
                Object[] row = null;
                while ((row = reader.nextRecord()) != null) {
                    for (int i = 0; i < row.length; i++) {

                        if (i == 4) {
                            String dateR = row[i].toString();
                            if (dateR.length() == 0){
                                row[i] = null;
                            }
                            else {
                                dateR = row[i].toString();
                                Date formatDateR = sdtF.parse(dateR);
                                row[i] = formatDateR;
                            }
                        }

                        if (i == 11) {
                            String dateSm = row[i].toString();
                            if (dateSm.length() == 0){
                                row[i] = null;
                            }
                            else {
                                dateSm = row[11].toString();
                                Date formatDateSm = sdtF.parse(dateSm);
                                row[i] = formatDateSm;
                            }
                        }
                        /* Проверка на поле 21 flatl в dead_all уберу так как тут может
                         быть мусор вроде номера квартиры "47-48", "общ." и тд.
                         На все не проверишь, так как поле char.

                        if (i == 21) {
                            String flatlNum = row[i].toString();
                            if (flatlNum.length() == 0){
                                row[i] = null;
                            }
                            else {
                                int flatlNumParse = Integer.parseInt(row[i].toString());
                                row[i] = flatlNumParse;
                            }
                        }
                         */
                        if (i == 24) {
                            String dateAzsm = row[i].toString();
                            if (dateAzsm.length() == 0){
                                row[i] = null;
                            }
                            else {
                                dateAzsm = row[i].toString();
                                Date formatDateAzsm = sdtF.parse(dateAzsm);
                                row[i] = formatDateAzsm;
                            }
                        }
                    }
                    writer.addRecord(row);
                }

                numRecord = reader.getRecordCount();
                numFields = reader.getFieldCount();
                Main.pw.println("Файл dAll записан");
                Main.pw.println("-Кодировка: " + reader.getCharset());
                Main.pw.println("-Кол во полей: " + numFields);
                Main.pw.println("-Кол во записей: " + numRecord);
            } catch (FileNotFoundException | ParseException e) {
                e.printStackTrace();
            } finally {
                DBFUtils.close(reader);
                DBFUtils.close(writer);
            }
        }

    }

