/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author alfar
 */
public class E5MetodoIntercalacionProducto {
    public <T extends Comparable<T>> void intercalar(String ruta1, String ruta2, String rutaResultado,
            Parseo<T> parseo) {
        try {
            BufferedReader brArchivo1 = new BufferedReader(new FileReader(ruta1));
            BufferedReader brArchivo2 = new BufferedReader(new FileReader(ruta2));
            BufferedWriter bwArchivo3 = new BufferedWriter(new FileWriter(rutaResultado));

            String linea1 = brArchivo1.readLine();
            String linea2 = brArchivo2.readLine();

            T obj1 = (linea1 != null) ? parseo.parsear(linea1) : null;
            // Corrección de la plantilla base: evaluar la linea2 correcta
            T obj2 = (linea2 != null) ? parseo.parsear(linea2) : null;

            while (obj1 != null && obj2 != null) {
                if (obj1.compareTo(obj2) <= 0) {
                    bwArchivo3.write(obj1.toString());
                    bwArchivo3.newLine();
                    linea1 = brArchivo1.readLine();
                    obj1 = (linea1 != null) ? parseo.parsear(linea1) : null;
                } else {
                    bwArchivo3.write(obj2.toString());
                    bwArchivo3.newLine();
                    linea2 = brArchivo2.readLine();
                    obj2 = (linea2 != null) ? parseo.parsear(linea2) : null;
                }
            }

            while (obj1 != null) {
                bwArchivo3.write(obj1.toString());
                bwArchivo3.newLine();
                linea1 = brArchivo1.readLine();
                obj1 = (linea1 != null) ? parseo.parsear(linea1) : null;
            }

            while (obj2 != null) {
                bwArchivo3.write(obj2.toString());
                bwArchivo3.newLine();
                linea2 = brArchivo2.readLine();
                obj2 = (linea2 != null) ? parseo.parsear(linea2) : null;
            }

            bwArchivo3.flush();
            brArchivo1.close();
            brArchivo2.close();
            bwArchivo3.close();
        } catch (IOException e) {
            System.out.println("Error ejecutando intercalación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
