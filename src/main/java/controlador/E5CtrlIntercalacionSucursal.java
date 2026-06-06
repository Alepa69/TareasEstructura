/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import modelo.E5MetodoIntercalacionProducto;
import modelo.E5ModeloIntercalacionProducto;
import modelo.Parseo;
import vista.E5VistaMezclaSucursales;

/**
 *
 * @author alfar
 */
public class E5CtrlIntercalacionSucursal {

    private E5VistaMezclaSucursales vista;
    private E5MetodoIntercalacionProducto algoritmoIntercalacion;

    // Constantes con los nombres de tus archivos en la raíz del proyecto
    private final String RUTA_SUCURSAL_1 = "Sucursal1.txt";
    private final String RUTA_SUCURSAL_2 = "Sucursal2.txt";
    private final String RUTA_REPORTE = "ReporteSucursales.txt";

    // Expresión Lambda que implementa la interfaz funcional Parseo para Productos
    private final Parseo<E5ModeloIntercalacionProducto> productoParser = (linea) -> {
        String[] datos = linea.split(",");
        String codigo = datos[0];
        String nombre = datos[1];
        double precio = Double.parseDouble(datos[2]);
        int cantidad = Integer.parseInt(datos[3]);
        return new E5ModeloIntercalacionProducto(codigo, nombre, precio, cantidad);
    };

    public E5CtrlIntercalacionSucursal(E5VistaMezclaSucursales vista) {
        this.vista = vista;
        this.algoritmoIntercalacion = new E5MetodoIntercalacionProducto();

        // Ejecutar los procesos automáticos de carga inicial
        inicializarTablas();
    }

    private void inicializarTablas() {
        // 1. Leer y llenar la tabla de la Sucursal 1
        llenarTablaDesdeArchivo(RUTA_SUCURSAL_1, (DefaultTableModel) vista.getTblSucursalUno().getModel());

        // 2. Leer y llenar la tabla de la Sucursal 2
        llenarTablaDesdeArchivo(RUTA_SUCURSAL_2, (DefaultTableModel) vista.getTblSucursalDos().getModel());

        // 3. Procesar la intercalación física de ambos archivos hacia el Reporte
        // Central
        algoritmoIntercalacion.intercalar(RUTA_SUCURSAL_1, RUTA_SUCURSAL_2, RUTA_REPORTE, productoParser);

        // 4. Mostrar el archivo consolidado final en la tabla central de la vista
        llenarTablaDesdeArchivo(RUTA_REPORTE, (DefaultTableModel) vista.getTblReporteSucursales().getModel());
    }

    private void llenarTablaDesdeArchivo(String rutaArchivo, DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    E5ModeloIntercalacionProducto prod = productoParser.parsear(linea);
                    modeloTabla.addRow(new Object[] {
                            prod.getCodigo(),
                            prod.getNombre(),
                            "$" + prod.getPrecio(),
                            prod.getCantidad()
                    });
                }
            }

            // CORRECCIÓN CENTRAL: Notificar al modelo que los datos cambiaron para que se
            // dibuje
            modeloTabla.fireTableDataChanged();

        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                    "Error con el archivo " + rutaArchivo + ": " + e.getMessage());
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                    "Error de formato en " + rutaArchivo + ": " + ex.getMessage());
        }
    }
}
