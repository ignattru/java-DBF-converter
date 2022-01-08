/* Программма для преобразования структуры файла ЗАГСА умерших за месяц dead_all.DBF в файлы
 * DDDGGGG.DBF и .DBF в T:\ZAGS для отдела пенс. обеспечения отдельных категорий граждан
 * Данный класс чистит макет файла inputFile и генерирует dbf вида szDMMGGGG
 * файл dead_all должен находться в
 *
 *
 * ТАБЛИЦА СООТВЕТСВИЯ НАЗВАНИЯ ПОЛЕЙ DBF И ИДЕНТИФИКАТОРОВ
 *
 * dead_all:      d0DDGGGG:         szDDGGGG:
 * 0 FAMPEOP
 * 1 NAMEPEPOP
 * 2 OTCHPEOP
 * 3 POLPEOP
 * 4 DATER
 * 5 GOSR
 * 6 SUBGOSR
 * 7 RAYONR
 * 8 GORODR
 * 9 NAMENPL
 * 10 TYPENPL
 * 11 STREETL
 * 12 HOUSEL
 * 13 KORPR
 * 14
 * 15
 * 16
 * 17
 * 18
 * 19
 * 20
 * 21
 * 22
 * 23
 * 24
 * 25
 * 26
 * 27
 * 28
 * 29
 * 30
 * 31
 * 32
 * 33
 * 34
 * 35
 * 36
 * 37
 *
 * */

//


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    //Начало ЗАГС импорта
    //Беру дату нужного формата для названия папок и файлов
    public static String day = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
    public static String month = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
    public static String year = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
    public static String dateCurrent= new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    public static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        File log = new File("log.txt");
        if(!log.exists()){
            log.createNewFile();
        } else{
            log.delete();
            log.createNewFile();
        }
        pw = new PrintWriter(log);
        pw.println("Запуск программы: " + dateCurrent);

        //Делаем пути для папок года и текущей даты
        File folderYear = new File("..\\" + year);
        File folderCurrentDate = new File(folderYear.getPath() + "\\" + dateCurrent);

        if (!folderYear.exists()) {
            pw.println("Создаю папку текущего года: " + folderYear.getPath());
            try{
                folderYear.mkdir();
                pw.println("Папка текущего года создана");
            }
            catch(SecurityException se){
            }
        } else {
            pw.println("Папка текущего года уже существует");
        }

        if (!folderCurrentDate.exists()) {
            pw.println("Создаю папку текущего дня: " + folderCurrentDate.getPath());
            try{
                folderCurrentDate.mkdir();
                pw.println("Папка текущего дня создана");
            }

            catch(SecurityException se){
            }

        } else {
            pw.println("Папка текущего дня уже существует");
        }

        try {
            deadallConvert.main(); //класс конвертит поля dead_all.dbf и записывает в dAll.dbf
            deadallRead.main(); //класс для чтения dAll.dbf и создания матрицы объектов
            d0Generation.main();//из матрицы объектов записываем в файл d0
            szGeneration.main();//из матрицы объектов записываем в файл sz
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


