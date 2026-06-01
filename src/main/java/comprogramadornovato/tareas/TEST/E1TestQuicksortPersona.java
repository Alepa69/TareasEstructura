package comprogramadornovato.tareas.TEST;

import java.util.ArrayList;

import controlador.E1CtrlQuicksortPersona;
import modelo.E1ModeloQuicksortPersona;
import vista.E1VistaQuicksortPersona;

/**
 *
 * @author alfar
 */
public class E1TestQuicksortPersona {
        public static void main(String[] args) {
        
        E1VistaQuicksortPersona vista = new E1VistaQuicksortPersona();
        
        ArrayList<E1ModeloQuicksortPersona> model = new ArrayList();
        
        E1CtrlQuicksortPersona control = new E1CtrlQuicksortPersona(vista, model);
        
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
    }
}
