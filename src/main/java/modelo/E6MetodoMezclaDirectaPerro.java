
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class E6MetodoMezclaDirectaPerro {
    // Nombres de los archivos del proyecto (ajusta si tus archivos NO tienen .txt)
    public static final String ARCHIVO_DATOS = "ObjetosOrdenadosMezclaDirecta.txt";
    public static final String ARCHIVO_F1 = "ObjetosMezclaDirecta1.txt";
    public static final String ARCHIVO_F2 = "ObjetosMezclaDirecta2.txt";

    /* Método principal de Mezcla Directa genérico */
    public <T extends Comparable<T>> void mezclaDirecta(String datos, Parseo<T> parseo) {
        int n = contarDatos(datos);
        int longitud = 1;
        while (longitud < n) {  // lógica del algoritmo
            particionar(longitud, parseo);
            fusionar(longitud, parseo);
            longitud *= 2;
        }
    }

    /* Devuelve la cantidad de objetos (líneas) en el fichero */
    private int contarDatos(String archivo) {
        int cantidad = 0;
        File f = new File(archivo);
        if (!f.exists()) {
            return 0;
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                if (!linea.trim().isEmpty()) {
                    cantidad++;
                }
                linea = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cantidad;
    }

    /* Partición en bloques del tamaño de la longitud */
    private <T extends Comparable<T>> void particionar(int longitud, Parseo<T> parseo) {
        ArrayList<T> datos = leerDatos(ARCHIVO_DATOS, parseo);

        // limpiar los ficheros auxiliares
        escribirDatos(ARCHIVO_F1, "", false);
        escribirDatos(ARCHIVO_F2, "", false);

        int i = 0;
        boolean banderaArchivo = true;
        while (i < datos.size()) {
            int cuenta = 0;
            StringBuilder sb = new StringBuilder();
            while (cuenta < longitud && i < datos.size()) {
                sb.append(datos.get(i).toString()).append("\n");
                i++;
                cuenta++;
            }
            if (banderaArchivo) {
                escribirDatos(ARCHIVO_F1, sb.toString(), true);
            } else {
                escribirDatos(ARCHIVO_F2, sb.toString(), true);
            }
            banderaArchivo = !banderaArchivo;
        }
    }

    /* Fusión de los dos archivos auxiliares hacia el archivo principal */
    private <T extends Comparable<T>> void fusionar(int longitud, Parseo<T> parseo) {
        ArrayList<T> f1 = leerDatos(ARCHIVO_F1, parseo);
        ArrayList<T> f2 = leerDatos(ARCHIVO_F2, parseo);

        escribirDatos(ARCHIVO_DATOS, "", false);
        int i = 0;
        int j = 0;
        while (i < f1.size() || j < f2.size()) {
            int cuenta1 = 0;
            int cuenta2 = 0;

            while (cuenta1 < longitud && cuenta2 < longitud
                    && i < f1.size() && j < f2.size()) {
                if (f1.get(i).compareTo(f2.get(j)) <= 0) {
                    escribirDatos(ARCHIVO_DATOS, f1.get(i).toString() + "\n", true);
                    i++;
                    cuenta1++;
                } else {
                    escribirDatos(ARCHIVO_DATOS, f2.get(j).toString() + "\n", true);
                    j++;
                    cuenta2++;
                }
            }

            while (cuenta1 < longitud && i < f1.size()) {
                escribirDatos(ARCHIVO_DATOS, f1.get(i).toString() + "\n", true);
                i++;
                cuenta1++;
            }

            while (cuenta2 < longitud && j < f2.size()) {
                escribirDatos(ARCHIVO_DATOS, f2.get(j).toString() + "\n", true);
                j++;
                cuenta2++;
            }
        }
    }

    /* Lee el archivo línea por línea y parsea cada línea como un objeto T */
    private <T extends Comparable<T>> ArrayList<T> leerDatos(String archivo, Parseo<T> parseo) {
        ArrayList<T> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) {
            return lista;
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(parseo.parsear(linea));
                }
                linea = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    /* Escribe en el fichero (append true = agrega, false = limpia) */
    private void escribirDatos(String fichero, String contenido, boolean append) {
        try {
            FileWriter fw = new FileWriter(fichero, append);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /* Método auxiliar para que el controlador pueda leer un archivo y mostrarlo */
    public String leerArchivoComoTexto(String archivo) {
        StringBuilder sb = new StringBuilder();
        File f = new File(archivo);
        if (!f.exists()) {
            return "";
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                sb.append(linea).append("\n");
                linea = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return sb.toString();
    }

    /* Agrega una línea (un perro) al archivo principal de datos */
    public void agregarPerroAlArchivo(E6ModeloMezclaDirectaPerro perro) {
        escribirDatos(ARCHIVO_DATOS, perro.toString() + "\n", true);
    }
}
