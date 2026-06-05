
package controlador;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.HeapSort;
import vista.E4VistaHeapsortArreglo;

/**
 *
 * @author PC
 */
public class E4CtrlHeapsortArreglo implements ActionListener {

    private E4VistaHeapsortArreglo vista;
    private HeapSort modelo;
    private ArrayList<Integer> listaDatos;

    public E4CtrlHeapsortArreglo(E4VistaHeapsortArreglo vista, HeapSort modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.listaDatos = new ArrayList<>();

        // Registrar el botón de la vista en el escuchador de eventos
        this.vista.btnAgregar.addActionListener(this);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            String entrada = vista.txtDatos.getText().trim();

            // Validar que el campo no se encuentre vacío
            if (entrada.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número entero.");
                return;
            }

            try {
                // Parseamos la entrada a un Entero (que implementa Comparable de manera nativa)
                int nuevoDato = Integer.parseInt(entrada);

                // 1. Agregar a la lista de datos del programa
                listaDatos.add(nuevoDato);

                // 2. Mostrar cómo va el arreglo ANTES de ordenar en el JTextArea
                vista.txtAreaAntes.setText(listaDatos.toString());

                // 3. Crear una copia de la lista para ordenar y no alterar el historial del
                // "Antes"
                ArrayList<Integer> listaParaOrdenar = new ArrayList<>(listaDatos);

                // 4. Ejecutar tu método genérico de ordenamiento hecho en clase
                modelo.heapSortObj(listaParaOrdenar);

                // 5. Mostrar el resultado final en el JTextArea "Arreglo despues"
                vista.txtAreaDespues.setText(listaParaOrdenar.toString());

                // Limpiar el campo de texto para agilizar una nueva inserción
                vista.txtDatos.setText("");
                vista.txtDatos.requestFocus();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Dato no válido. Debe ingresar únicamente números enteros.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
