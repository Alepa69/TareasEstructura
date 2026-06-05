
package comprogramadornovato.tareas.TEST;

import controlador.E4CtrlHeapsortArreglo;
import modelo.HeapSort;

/**
 *
 * @author PC
 */
public class E4TestHeapSortArreglo {
    public static void main(String args[]) {
        // 1. Instanciar los componentes del MVC
        vista.E4VistaHeapsortArreglo ventana = new vista.E4VistaHeapsortArreglo();
        HeapSort algHeap = new HeapSort();

        // 2. Unirlos a través del controlador
        E4CtrlHeapsortArreglo controlador = new E4CtrlHeapsortArreglo(ventana, algHeap);

        // 3. Desplegar la pantalla
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
