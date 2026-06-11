
package comprogramadornovato.tareas.TEST;

import controlador.E6CtrlMezclaDirectaPerro;
import vista.E6VistaMezclaDirecta;
/**
 *
 * @author PC
 */
public class E6TestMezclaDirectaPerro {
        public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            E6VistaMezclaDirecta vista = new E6VistaMezclaDirecta();
            new E6CtrlMezclaDirectaPerro(vista);
            vista.setVisible(true);
        });
    }
}
