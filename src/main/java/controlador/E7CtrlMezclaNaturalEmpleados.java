package controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.E7MetodoMezclaNaturalEmpleados;
import modelo.E7ModeloMezclaNaturalEmpleados;
import modelo.Parseo;
import vista.E7VistaNaturalMezclaEmpleado;

public class E7CtrlMezclaNaturalEmpleados {

    private E7VistaNaturalMezclaEmpleado vista;
    private E7MetodoMezclaNaturalEmpleados metodo;

    // Parseo: convierte una linea "id,dui,nombre,..." en un Empleado
    private final Parseo<E7ModeloMezclaNaturalEmpleados> parseoEmpleado = (String linea) -> {
        String[] p = linea.split(",");
        int id = Integer.parseInt(p[0].trim());
        String dui = p[1].trim();
        String nombre = p[2].trim();
        String telefono = p[3].trim();
        String correo = p[4].trim();
        String cargo = p[5].trim();
        String fechaContrato = p[6].trim();
        double salario = Double.parseDouble(p[7].trim());
        return new E7ModeloMezclaNaturalEmpleados(id, dui, nombre, telefono,
                correo, cargo, fechaContrato, salario);
    };

    public E7CtrlMezclaNaturalEmpleados(E7VistaNaturalMezclaEmpleado vista) {
        this.vista = vista;
        this.metodo = new E7MetodoMezclaNaturalEmpleados();

        ejecutarOrdenamiento();
    }

    private void ejecutarOrdenamiento() {
        // 0. Restaurar el archivo de datos desde el respaldo (datos originales desordenados)
        if (!restaurarDesdeRespaldo()) {
            return; // si no se puede restaurar, no continuamos
        }

        // 1. Leer el archivo en su estado ORIGINAL (antes de ordenar)
        ArrayList<E7ModeloMezclaNaturalEmpleados> antes = metodo.leerArchivo(
                E7MetodoMezclaNaturalEmpleados.ARCHIVO_DATOS, parseoEmpleado);

        llenarTabla(vista.getTblAntes(), antes);

        // 2. Aplicar el algoritmo Mezcla Natural sobre el archivo
        metodo.mezclaNatural(E7MetodoMezclaNaturalEmpleados.ARCHIVO_DATOS, parseoEmpleado);

        // 3. Leer el archivo ya ordenado
        ArrayList<E7ModeloMezclaNaturalEmpleados> despues = metodo.leerArchivo(
                E7MetodoMezclaNaturalEmpleados.ARCHIVO_DATOS, parseoEmpleado);

        llenarTabla(vista.getTblDatosOrdenados(), despues);

    }

    /* Copia EmpleadosOriginalesMezclaNatural.txt -> EmpleadosOrdenadosMezclaNatural.txt
       para garantizar que cada ejecucion arranque con los datos desordenados originales. */
    private boolean restaurarDesdeRespaldo() {
        try {
            Files.copy(
                    Paths.get(E7MetodoMezclaNaturalEmpleados.ARCHIVO_ORIGINAL),
                    Paths.get(E7MetodoMezclaNaturalEmpleados.ARCHIVO_DATOS),
                    StandardCopyOption.REPLACE_EXISTING
            );
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista,
                    "No se pudo restaurar el archivo de respaldo:\n"
                    + E7MetodoMezclaNaturalEmpleados.ARCHIVO_ORIGINAL
                    + "\n\nVerifica que el archivo exista al nivel del proyecto.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    private void llenarTabla(JTable tabla, ArrayList<E7ModeloMezclaNaturalEmpleados> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // limpiar tabla
        for (E7ModeloMezclaNaturalEmpleados e : lista) {
            modelo.addRow(new Object[]{
                e.getId(),
                e.getDui(),
                e.getNombre(),
                e.getTelefono(),
                e.getCorreo(),
                e.getCargo(),
                e.getFechaContrato(),
                e.getSalario()
            });
        }
    }
}