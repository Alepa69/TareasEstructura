
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.E2MetodoQuicksortEmpleado;

import modelo.E2ModeloQuicksortEmpleado;
import vista.E2VistaQuicksortEmpleado;

/**
 *
 * @author alfar
 */
public class E2CtrlQuicksortEmpleado implements ActionListener {
    private E2VistaQuicksortEmpleado vista;
    private ArrayList<E2ModeloQuicksortEmpleado> lista;

    public E2CtrlQuicksortEmpleado(E2VistaQuicksortEmpleado vista, ArrayList<E2ModeloQuicksortEmpleado> lista) {
        this.vista = vista;
        this.lista = lista;
        
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnOrdenarSalario().addActionListener(this);

        actualizarTabla(this.lista);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // 1. LÓGICA PARA EL BOTÓN AGREGAR
    if (e.getSource() == vista.getBtnAgregar()) {
        try { 
            // Obtenemos los datos de la vista
            String nom = vista.getTxtNombre().getText();
            double sal = Double.parseDouble(vista.getTxtSalario().getText());
            LocalDate fecha = LocalDate.parse(vista.getTxtFechaIngreso().getText()); 
            
            // Agregamos a la lista y actualizamos la tabla
            lista.add(new E2ModeloQuicksortEmpleado(nom, sal, fecha));
            actualizarTabla(lista);
            
            System.out.println("E2QuicksortModeloEmpleado agregado con éxito: " + nom);
            
            // Limpiar los campos después de agregar (opcional pero recomendado)
            vista.getTxtNombre().setText("");
            vista.getTxtSalario().setText("");
            vista.getTxtFechaIngreso().setText("");

        } catch (Exception ex) { 
            javax.swing.JOptionPane.showMessageDialog(vista, 
                "Error en los datos. Revisa que:\n" +
                "- El salario sea un número.\n" +
                "- La fecha tenga formato AAAA-MM-DD (ej: 2024-03-18)");
            ex.printStackTrace(); 
        }
    } 

    // 2. LÓGICA PARA EL BOTÓN ORDENAR
    else if (e.getSource() == vista.getBtnOrdenarSalario()) {
        if (!lista.isEmpty()) {
            E2ModeloQuicksortEmpleado[] array = lista.toArray(new E2ModeloQuicksortEmpleado[0]);
            E2MetodoQuicksortEmpleado.quickSort(array, 0, array.length - 1);
            
            lista.clear();
            for (E2ModeloQuicksortEmpleado emp : array) {
                lista.add(emp);
            }
            actualizarTabla(lista);
        }
    }
}  
public void actualizarTabla(ArrayList<E2ModeloQuicksortEmpleado> listaEmpleados) {
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.getTblDatosEmpleado().getModel();
        
        modeloTabla.setRowCount(0);

        for (E2ModeloQuicksortEmpleado emp : listaEmpleados) {
            Object[] fila = { 
                emp.getNombre(), 
                emp.getSalario(), 
                emp.getFechaIngreso(), 
                emp.getAntiguedad() + " años" 
            };
            modeloTabla.addRow(fila);
        }
    } 
}
