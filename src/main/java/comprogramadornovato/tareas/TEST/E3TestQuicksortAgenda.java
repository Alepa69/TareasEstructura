
package comprogramadornovato.tareas.TEST;

import java.util.ArrayList;

import controlador.E3CtrlQuicksortAgenda;
import modelo.E3ModeloQuicksortAgenda;
import vista.E3VistaQuicksortAgenda;

/**
 *
 * @author alfar
 */public class E3TestQuicksortAgenda {
    
    public static void main(String[] args) {
        E3VistaQuicksortAgenda vista = new E3VistaQuicksortAgenda();
        
        ArrayList<E3ModeloQuicksortAgenda> lista = new ArrayList<>();
        E3CtrlQuicksortAgenda controlador = new E3CtrlQuicksortAgenda(vista, lista);
        
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
    }
}
