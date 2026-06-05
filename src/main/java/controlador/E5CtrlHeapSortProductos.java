
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.E5EnumHeapSortClasificacion;
import modelo.E5MetodoHeapSortProducto;
import modelo.E5ModeloHeapSortProducto;
import modelo.HeapSort;
import vista.E5VistaHeapsortProductos;

/**
 *
 * @author PC
 */
public class E5CtrlHeapSortProductos implements ActionListener {
    private E5VistaHeapsortProductos vista;
    private ArrayList<E5ModeloHeapSortProducto> listaProductos;
    private DefaultTableModel modeloTabla;

    public E5CtrlHeapSortProductos(E5VistaHeapsortProductos vista) {
        this.vista = vista;
        this.listaProductos = new ArrayList<>();
        this.modeloTabla = (DefaultTableModel) vista.getTblProductos().getModel();

        // Registrar los listeners de los botones
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnAscendente().addActionListener(this);
        this.vista.getBtnDescendente().addActionListener(this);

        llenarComboBoxCategorias();

        // Limpiar filas por defecto de la tabla al iniciar
        modeloTabla.setRowCount(0);
    }

    // Método para cargar dinámicamente las clasificaciones en el ComboBox
    private void llenarComboBoxCategorias() {
        vista.getCbxCategoria().removeAllItems(); // Limpia elementos previos si los hay

        // Recorremos todos los valores que tiene definidos el Enum
        for (E5EnumHeapSortClasificacion c : E5EnumHeapSortClasificacion.values()) {
            // Agregamos el texto legible (gracias al método toString() del Enum)
            vista.getCbxCategoria().addItem(c.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAgregar()) {
            agregarProducto();
        } else if (e.getSource() == vista.getBtnAscendente()) {
            ordenarProductos(true);
        } else if (e.getSource() == vista.getBtnDescendente()) {
            ordenarProductos(false);
        }
    }

    private void agregarProducto() {
        String codigo = vista.getTxtCodigoProducto().getText().trim();
        String nombre = vista.getTtxtNombreProducto().getText().trim();
        int indiceSeleccionado = vista.getCbxCategoria().getSelectedIndex();

        if (codigo.isEmpty() || nombre.isEmpty() || indiceSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Por favor complete todos los campos.");
            return;
        }

        // Buscamos directamente el Enum usando el índice del ComboBox (coinciden
        // perfectamente en orden)
        E5EnumHeapSortClasificacion clasificacion = E5EnumHeapSortClasificacion.values()[indiceSeleccionado];

        // Crear el objeto y añadirlo a la lista en memoria
        E5ModeloHeapSortProducto nuevoProducto = new E5ModeloHeapSortProducto(codigo, nombre, clasificacion);
        listaProductos.add(nuevoProducto);

        // Actualizar tabla visual y limpiar campos
        actualizarTabla();
        limpiarCampos();
    }

    private void ordenarProductos(boolean ascendente) {
        if (listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay productos para ordenar.");
            return;
        }

        // Convertir la lista dinámica a un arreglo de tipo estático Producto[]
        // requerido por el genérico
        E5ModeloHeapSortProducto[] arregloProductos = listaProductos.toArray(new E5ModeloHeapSortProducto[0]);

        // Ejecutar el ordenamiento genérico HeapSort
        E5MetodoHeapSortProducto.ordenar(arregloProductos, ascendente);

        // Volver a volcar los datos ordenados del arreglo a nuestra lista principal
        listaProductos.clear();
        for (E5ModeloHeapSortProducto p : arregloProductos) {
            listaProductos.add(p);
        }

        // Refrescar la UI
        actualizarTabla();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Reiniciar filas
        for (E5ModeloHeapSortProducto p : listaProductos) {
            modeloTabla.addRow(new Object[] {
                    p.getCodigo(),
                    p.getNombre(),
                    p.getClasificacion().toString()
            });
        }
    }

    private void limpiarCampos() {
        vista.getTxtCodigoProducto().setText("");
        vista.getTtxtNombreProducto().setText("");
        vista.getCbxCategoria().setSelectedIndex(0); // Restablece a la primera opción
        vista.getTxtCodigoProducto().requestFocus();
    }
}
