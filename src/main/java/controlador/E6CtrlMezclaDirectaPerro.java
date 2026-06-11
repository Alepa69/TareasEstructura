
package controlador;

import javax.swing.JOptionPane;
import modelo.E6MetodoMezclaDirectaPerro;
import modelo.E6ModeloMezclaDirectaPerro;
import modelo.Parseo;
import vista.E6VistaMezclaDirecta;
/**
 *
 * @author PC
 */
public class E6CtrlMezclaDirectaPerro {
    private E6VistaMezclaDirecta vista;
    private E6MetodoMezclaDirectaPerro metodo;

    // Parseo para convertir cada línea "nombre,edad,raza" en un objeto Perro
    private final Parseo<E6ModeloMezclaDirectaPerro> parseoPerro = (String linea) -> {
        String[] partes = linea.split(",");
        String nombre = partes[0].trim();
        int edad = Integer.parseInt(partes[1].trim());
        String raza = partes[2].trim();
        return new E6ModeloMezclaDirectaPerro(nombre, edad, raza);
    };

    public E6CtrlMezclaDirectaPerro(E6VistaMezclaDirecta vista) {
        this.vista = vista;
        this.metodo = new E6MetodoMezclaDirectaPerro();

        // Al iniciar, mostramos el contenido actual de los archivos (si ya hay datos)
        mostrarArchivoEntrada();
        mostrarArchivoSalida();

        // ActionListener con lambda para el botón Agregar
        this.vista.getBtnAgregar().addActionListener(e -> agregarPerro());
    }

    private void agregarPerro() {
        try {
            String nombre = vista.getTxtNombre().getText().trim();
            String edadStr = vista.getTxtEdad().getText().trim();
            String raza = vista.getTxtRaza().getText().trim();

            if (nombre.isEmpty() || edadStr.isEmpty() || raza.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos.");
                return;
            }

            int edad = Integer.parseInt(edadStr);

            // 1. Creamos el perro
            E6ModeloMezclaDirectaPerro perro = new E6ModeloMezclaDirectaPerro(nombre, edad, raza);

            // 2. Lo agregamos al archivo de datos
            metodo.agregarPerroAlArchivo(perro);

            // 3. Mostramos el archivo de entrada (antes de ordenar)
            mostrarArchivoEntrada();

            // 4. Aplicamos el algoritmo de mezcla directa
            metodo.mezclaDirecta(E6MetodoMezclaDirectaPerro.ARCHIVO_DATOS, parseoPerro);

            // 5. Mostramos el archivo de salida (ya ordenado)
            mostrarArchivoSalida();

            // 6. Limpiamos los campos del formulario
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "La edad debe ser un número entero.");
        }
    }

    private void mostrarArchivoEntrada() {
        String contenido = metodo.leerArchivoComoTexto(E6MetodoMezclaDirectaPerro.ARCHIVO_DATOS);
        vista.getTxtAreaEntrada().setText(contenido);
    }

    private void mostrarArchivoSalida() {
        String contenido = metodo.leerArchivoComoTexto(E6MetodoMezclaDirectaPerro.ARCHIVO_DATOS);
        vista.getTxtAreaSalida().setText(contenido);
    }

    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtEdad().setText("");
        vista.getTxtRaza().setText("");
        vista.getTxtNombre().requestFocus();
    }
}
