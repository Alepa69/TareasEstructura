package comprogramadornovato.tareas.TEST;

import controlador.E7CtrlMezclaNaturalEmpleados;
import vista.E7VistaNaturalMezclaEmpleado;

/**
 *
 * @author PC
 */
public class E7TestMezclaNaturalEmpleados {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            E7VistaNaturalMezclaEmpleado vista = new E7VistaNaturalMezclaEmpleado();
            new E7CtrlMezclaNaturalEmpleados(vista);
            vista.setVisible(true);
        });
    }
}
