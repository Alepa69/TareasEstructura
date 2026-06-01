package comprogramadornovato.tareas.TEST;

import java.util.ArrayList;

import controlador.E2CtrlQuicksortEmpleado;
import modelo.E2ModeloQuicksortEmpleado;
import vista.E2VistaQuicksortEmpleado;

/**
 *
 * @author alfar
 */
public class E2TestQuicksortEmpleado {

    public static void main(String[] args) {
        E2VistaQuicksortEmpleado vista = new E2VistaQuicksortEmpleado();

        ArrayList<E2ModeloQuicksortEmpleado> model = new ArrayList<>();

        E2CtrlQuicksortEmpleado control = new E2CtrlQuicksortEmpleado(vista, model);

        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

}
