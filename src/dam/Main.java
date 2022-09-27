package dam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Crear un programa que ejecute 3 comandos del cmd, recoja una cadena por
 teclado y que muestre el resultado por consola de forma ordenada.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Leer desde un fichero qué carpeta hay que listar.
        Path path = Paths.get("C:\\Users\\DAM\\Desktop");


        // Leer desde teclado qué cadena hay que buscar.

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la cadena a buscar:");
        String cadena = sc.next();

        // Hacer un ProcessBuilder para sacar el listado de esa carpeta. //dir

        ProcessBuilder pbListado = new ProcessBuilder("cmd", "/C", "dir", path.toString());

        // Hacer un ProcessBuilder para buscar la cadena.

        ProcessBuilder pbCadena = new ProcessBuilder("findstr", cadena);

        // Hacer un ProcessBuilder para ejecutar el "sort",

        ProcessBuilder pbSort = new ProcessBuilder("sort");

        // y redirigir su salida a la consola.

        pbSort.redirectOutput(ProcessBuilder.Redirect.INHERIT);


        //Opción 2: escribir en un fichero.

      /*  File salida = new File("salida.txt");
        pbSort.redirectOutput(salida);
*/
        // Arrancar los tres ProcessBuilders en pipeline.

        List<ProcessBuilder> procesos = new ArrayList<>();
        procesos.add(pbListado);
        procesos.add(pbCadena);
        procesos.add(pbSort);

        ProcessBuilder.startPipeline(procesos);


    }
}