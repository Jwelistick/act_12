package Telephone;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {
    public static void main(String[] args) throws ParseException, IOException {

        //Variables
        Scanner sc = new Scanner(System.in);
        int res = 0;
        int con = 0;
        String line = null;
        HashMap<String, String> map = new HashMap<String, String>();

        //Código main
        Help();
        while(con == 0){
        System.out.println("Que quieres hacer? (escribe número)");
        res = sc.nextInt();
        sc.nextLine();

        switch (res) {
            //Load
            case 1:
                Load(map,line);
                break;
            //Save
            case 2:
                Save(map);
                break;
            //List
            case 3:
                List(map);
                break;
            //Create
            case 4:
                Create(map);
                break;
            //Delete
            case 5:
                Delete(map);
                break;
            //Help
            case 6:
                Help();
                break;
            //Exit
            case 7:
                con=Exit();
                break;
            default:
                System.out.println("Opción inválida");
                break;
            }
        }
    }




    //Métodos
    public static void Load(HashMap<String,String> map, String line) throws ParseException, IOException{
        //Lectura del csv
        //Basado de este thread: https://stackoverflow.com/questions/20068383/convert-csv-values-to-a-hashmap-key-value-pairs-in-java
        BufferedReader br = new BufferedReader(new FileReader("./src/Telephone/file.csv"));

        while ((line = br.readLine()) != null) {
            String str[] = line.split(",");
            for (int i = 1; i < str.length; i++) {
                String arr[] = str[i].split(":");
                map.put(arr[0], arr[1]);
            }
        }
    }

    public static void Save(HashMap<String,String> map/*, Writer writer*/) throws IOException {
      /*//Código de https://stackoverflow.com/questions/31172003/java-write-hashmap-to-a-csv-file (Comentario porque
        //no funciona, necesita meterle Jackson API al programa y no sé hacer eso)
        CsvSchema schema = null;
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        if (map != null && !map.isEmpty()) {
            for (String col : map.get(0).keySet()) {
                schemaBuilder.addColumn(col);
            }
            schema = schemaBuilder.build().withLineSeparator(System.lineSeparator()).withHeader();
        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).writeAll(map);
        writer.flush();*/
        System.out.println("Cambios Guardados!");
    }

    public static void List(HashMap<String,String> map){
        System.out.println(map);
    }

    public static void Create(HashMap<String,String> map){
        Scanner sc = new Scanner(System.in);
        String a,b;
        System.out.println("Escribe el número de teléfono");
        a = sc.nextLine();
        System.out.println("Escribe el nombre de contacto");
        b = sc.nextLine();
        map.put(a,b);
    }

    public static void Help(){
        System.out.println("" +
                "1-Load (Cargar contactos)\n" +
                "2-Save (Guardar contactos)\n" +
                "3-List (Mostrar contactos)\n" +
                "4-Create (Crear contacto)\n" +
                "5-Delete (Borrar contacto)\n" +
                "6-Help (Ver menú de nuevo)\n" +
                "7-Salir");
    }

    public static void Delete(HashMap<String,String> map){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el número telefónico a borrar");
        map.remove(sc.nextLine());
    }

    public static int Exit(){
        return 1;
    }

}