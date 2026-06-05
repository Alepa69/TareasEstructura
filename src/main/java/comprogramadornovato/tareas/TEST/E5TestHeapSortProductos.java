
package comprogramadornovato.tareas.TEST;

import controlador.E5CtrlHeapSortProductos;
import vista.E5VistaHeapsortProductos;

/**
 *
 * @author PC
 */
public class E5TestHeapSortProductos {
    public static void main(String[] args) {
        // Inicializar los componentes siguiendo MVC
        E5VistaHeapsortProductos vista = new E5VistaHeapsortProductos();
        E5CtrlHeapSortProductos controlador = new E5CtrlHeapSortProductos(vista);

        // Desplegar la ventana
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
