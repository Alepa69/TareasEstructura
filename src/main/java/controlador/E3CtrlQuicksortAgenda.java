
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.E3MetodoQuicksortAgenda;
import modelo.E3ModeloQuicksortAgenda;
import vista.E3VistaQuicksortAgenda;

/**
 *
 * @author alfar
 */
public class E3CtrlQuicksortAgenda implements ActionListener{
    
    private E3VistaQuicksortAgenda vista;
    private ArrayList<E3ModeloQuicksortAgenda> lista;

    public E3CtrlQuicksortAgenda(E3VistaQuicksortAgenda vista, ArrayList<E3ModeloQuicksortAgenda> lista) {
        this.vista = vista;
        this.lista = lista;

        // Conectamos los botones de tu diseño
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnAscendente().addActionListener(this);
        this.vista.getBtnDescendente().addActionListener(this);

        actualizarTabla(this.lista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // BOTÓN AGREGAR
        if (e.getSource() == vista.getBtnAgregar()) {
            String nom = vista.getTxtNombre().getText(); // .getText() soluciona el error anterior
            String num = vista.getTxtNumero().getText();

            if (!nom.isEmpty() && !num.isEmpty()) {
                lista.add(new E3ModeloQuicksortAgenda(nom, num));
                actualizarTabla(lista);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "Llene todos los campos");
            }
        } 
        
        // BOTÓN ORDEN ASCENDENTE (A-Z)
        else if (e.getSource() == vista.getBtnAscendente()) {
            ordenar(true);
        } 
        
        // BOTÓN ORDEN DESCENDENTE (Z-A)
        else if (e.getSource() == vista.getBtnDescendente()) {
            ordenar(false);
        }
    }

    private void ordenar(boolean ascendente) {
  if (!lista.isEmpty()) {
            E3ModeloQuicksortAgenda[] array = lista.toArray(new E3ModeloQuicksortAgenda[0]);
            
            // CORREGIDO: Llamada exacta a tu clase y método Quicksort
            E3MetodoQuicksortAgenda.quickSort(array, 0, array.length - 1, ascendente);
            
            lista.clear();
            for (E3ModeloQuicksortAgenda c : array) {
                lista.add(c);
            }
            
            actualizarTabla(lista); // Sincroniza los cambios con la interfaz gráfica
        }
    }

    public void actualizarTabla(ArrayList<E3ModeloQuicksortAgenda> listaAgenda) {
        // Obtenemos el DefaultTableModel directamente de jTable1
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.getjTable1().getModel();
        
        // 1. Limpiamos las filas viejas
        modeloTabla.setRowCount(0);

        // 2. Cargamos los elementos actualizados
        for (E3ModeloQuicksortAgenda contacto : listaAgenda) {
            Object[] fila = { contacto.getNombre(), contacto.getNumero() };
            modeloTabla.addRow(fila);
        }
    }

    private void limpiar() {
        vista.getTxtNombre().setText("");
        vista.getTxtNumero().setText("");
        vista.getTxtNombre().requestFocus();
    }
}
