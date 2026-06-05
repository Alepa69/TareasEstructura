
package comprogramadornovato.tareas.TEST;

import controlador.ControladorArbolBinarioBusqueda;
import modelo.ArbolBinarioBusqueda;
import vista.ArbolVistaPrincipal;

/**
 *
 * @author alfar
 */
public class TestArbolBinarioBusqueda {
    public static void main(String[] args) {
        // 1. Instanciar los componentes del MVC
        ArbolVistaPrincipal vista = new ArbolVistaPrincipal();
        ArbolBinarioBusqueda<Integer> modelo = new ArbolBinarioBusqueda<>();
        
        // 2. El controlador se encarga de unirlos y gestionar los eventos
        ControladorArbolBinarioBusqueda controlador = new ControladorArbolBinarioBusqueda(vista, modelo);
        
        // 3. Hacer visible la pantalla
        vista.setLocationRelativeTo(null); // Centrar en pantalla
        vista.setVisible(true);
    }
}
