package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.E1MetodoQuicksortPersona;
import modelo.E1ModeloQuicksortPersona;
import vista.E1VistaQuicksortPersona;

/**
 *
 * @author alfar
 */
public class E1CtrlQuicksortPersona implements ActionListener{
    private E1VistaQuicksortPersona vista;
    private ArrayList<E1ModeloQuicksortPersona> model;

    public E1CtrlQuicksortPersona(E1VistaQuicksortPersona vista, ArrayList<E1ModeloQuicksortPersona> model) {
        this.vista = vista;
        this.model = model;
        
        this.vista.getBtnAgregar().addActionListener(this);

        this.vista.getBtnOrdenAltura().addActionListener(this);
        this.vista.getBtnOrdenNombre().addActionListener(this);

        actualizarTabla(this.model);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){

    // 1. LÓGICA PARA AGREGAR
    if (e.getSource() == vista.getBtnAgregar()) {
        try {
            // Extraemos el texto de los JTextFields usando tus getters
            String nombre = vista.getTxtNombre().getText().trim();
            String alturaStr = vista.getTxtAltura().getText().trim();

            if (!nombre.isEmpty() && !alturaStr.isEmpty()) {
                double altura = Double.parseDouble(alturaStr);
                
                // Creamos la persona y la metemos al ArrayList (Modelo)
                model.add(new E1ModeloQuicksortPersona(nombre, altura));
                
                // LE DECIMOS A LA VISTA QUE SE REFRESQUE
                actualizarTabla(model);
                
                // Limpiamos los cuadros de texto
                vista.getTxtNombre().setText("");
                vista.getTxtAltura().setText("");
            }else {
                    javax.swing.JOptionPane.showMessageDialog(vista, "Por favor, llene todos los campos.");
                }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(vista, "La altura debe ser un número (ej: 1.70)");
        }
    }
    
    // 2. LÓGICA PARA ORDENAR POR NOMBRE
    else if (e.getSource() == vista.getBtnOrdenNombre()) {
        if (!model.isEmpty()) {
            E1ModeloQuicksortPersona[] arr = model.toArray(new E1ModeloQuicksortPersona[0]);
            // Llamamos a tu QuickSort (true para nombre)
            E1MetodoQuicksortPersona.sort(arr, 0, arr.length - 1, "nombre");
            
            // Pasamos el arreglo ordenado de vuelta al ArrayList
            model.clear();
            for (E1ModeloQuicksortPersona p : arr) model.add(p);
            
            actualizarTabla(model);
        }
    }
    
    // 3. LÓGICA PARA ORDENAR POR ALTURA
    else if (e.getSource() == vista.getBtnOrdenAltura()) {
        if (!model.isEmpty()) {
            E1ModeloQuicksortPersona[] arr = model.toArray(new E1ModeloQuicksortPersona[0]);

            //LLamada del metodo Quicksort (false para altura)
            E1MetodoQuicksortPersona.sort(arr, 0, arr.length - 1, "altura");
            
            model.clear();
            for (E1ModeloQuicksortPersona p : arr) model.add(p);
            
            actualizarTabla(model);
        }
    }
  }

  /***********************************************************************/
public void actualizarTabla(ArrayList<E1ModeloQuicksortPersona> lista) {
        // Obtenemos el modelo directamente desde la JTable de la vista
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.getTblAltura().getModel();
        
        // 1. Limpiamos el modelo para evitar duplicados
        modeloTabla.setRowCount(0);

        // 2. Recorremos el ArrayList y agregamos las filas
        for (E1ModeloQuicksortPersona p : lista) {
            Object[] fila = { p.getNombre(), p.getAltura() };
            modeloTabla.addRow(fila);
        }
    }
}
