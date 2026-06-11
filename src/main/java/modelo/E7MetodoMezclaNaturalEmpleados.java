
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
public class E7MetodoMezclaNaturalEmpleados {
     // Nombres de los archivos (ajusta si tus archivos NO tienen .txt)
    public static final String ARCHIVO_DATOS = "EmpleadosOrdenadosMezclaNatural.txt";
    public static final String ARCHIVO_F1 = "EmpleadosMezclaNatural1.txt";
    public static final String ARCHIVO_F2 = "EmpleadosMezclaNatural2.txt";
    public static final String ARCHIVO_ORIGINAL = "EmpleadosOriginalesMezclaNatural.txt";
    
    private int lecturas = 0;
    private int escrituras = 0;

    /* Metodo principal de la Mezcla Natural generico */
    public <T extends Comparable<T>> void mezclaNatural(String archivo, Parseo<T> parseo) {
        int pasadas;
        do {
            pasadas = particionNatural(archivo, parseo); // escribe runs alternados en F1 y F2
            if (pasadas > 1) {
                fusionNatural(archivo, parseo);          // mezcla runs de F1 y F2 hacia archivo
            }
        } while (pasadas > 1);
    }

    /* Separa runs naturales del archivo original alternando entre F1 y F2 */
    private <T extends Comparable<T>> int particionNatural(String archivo, Parseo<T> parseo) {
        escribir(ARCHIVO_F1, "", false);
        escribir(ARCHIVO_F2, "", false);
        int pasada = 0;
        boolean aF1 = true;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            // saltar lineas vacias al inicio
            while (linea != null && linea.trim().isEmpty()) {
                linea = br.readLine();
            }
            if (linea == null) {
                return 0;
            }

            T datoPrevio = parseo.parsear(linea);
            lecturas++;
            pasada = 1; // al menos 1 run
            escribir(aF1 ? ARCHIVO_F1 : ARCHIVO_F2, datoPrevio.toString() + "\n", true);

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                T dato = parseo.parsear(linea);
                lecturas++;
                // Si hay "caida", comienza una nueva pasada y alterna archivo
                if (dato.compareTo(datoPrevio) < 0) {
                    pasada++;
                    aF1 = !aF1;
                }
                escribir(aF1 ? ARCHIVO_F1 : ARCHIVO_F2, dato.toString() + "\n", true);
                datoPrevio = dato;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return pasada;
    }

    /* Mezcla las runs de F1 y F2 hacia el archivo principal */
    private <T extends Comparable<T>> void fusionNatural(String archivo, Parseo<T> parseo) {
        escribir(archivo, "", false);
        try (BufferedReader brA = new BufferedReader(new FileReader(ARCHIVO_F1));
             BufferedReader brB = new BufferedReader(new FileReader(ARCHIVO_F2))) {

            T datoA = leerSiguiente(brA, parseo);
            T datoB = leerSiguiente(brB, parseo);

            // Mientras existan datos en alguno de los dos archivos
            while (datoA != null || datoB != null) {
                lecturas++;
                // Cada ciclo mezcla 1 run de F1 con 1 run de F2
                T ultimoA = null; // para detectar cuando termina una corrida
                T ultimoB = null;
                boolean archivoA = (datoA != null);
                boolean archivoB = (datoB != null);

                while (archivoA || archivoB) {
                    if (!archivoB) {
                        // Solo queda la ejecucion de archivo A
                        escribir(archivo, datoA.toString() + "\n", true);
                        ultimoA = datoA;
                        datoA = leerSiguiente(brA, parseo);
                        archivoA = (datoA != null
                                && (ultimoA == null || datoA.compareTo(ultimoA) >= 0));
                        continue;
                    }

                    if (!archivoA) {
                        // Solo queda la ejecucion de archivo B
                        escribir(archivo, datoB.toString() + "\n", true);
                        ultimoB = datoB;
                        datoB = leerSiguiente(brB, parseo);
                        archivoB = (datoB != null
                                && (ultimoB == null || datoB.compareTo(ultimoB) >= 0));
                        continue;
                    }

                    // Ambas ejecuciones activas: comparar
                    if (datoA.compareTo(datoB) <= 0) {
                        escribir(archivo, datoA.toString() + "\n", true);
                        ultimoA = datoA;
                        datoA = leerSiguiente(brA, parseo);
                        // sigue el run si no hay caida
                        archivoA = (datoA != null && datoA.compareTo(ultimoA) >= 0);
                    } else {
                        escribir(archivo, datoB.toString() + "\n", true);
                        ultimoB = datoB;
                        datoB = leerSiguiente(brB, parseo);
                        archivoB = (datoB != null && datoB.compareTo(ultimoB) >= 0);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /* Lee la siguiente linea no vacia del BufferedReader y la parsea */
    private <T extends Comparable<T>> T leerSiguiente(BufferedReader br, Parseo<T> parseo) throws IOException {
        String linea = br.readLine();
        while (linea != null && linea.trim().isEmpty()) {
            linea = br.readLine();
        }
        return (linea != null) ? parseo.parsear(linea) : null;
    }

    /* Escribe en el fichero (append true = agrega, false = limpia) */
    private void escribir(String archivo, String texto, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, append))) {
            bw.write(texto);
            bw.flush();
            escrituras++;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /* Metodo auxiliar para que el controlador pueda leer un archivo a una lista */
    public <T extends Comparable<T>> ArrayList<T> leerArchivo(String archivo, Parseo<T> parseo) {
        ArrayList<T> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(parseo.parsear(linea));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public int getLecturas() { return lecturas; }
    public int getEscrituras() { return escrituras; }
}
