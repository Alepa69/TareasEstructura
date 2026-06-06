/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comprogramadornovato.tareas.TEST;

import vista.E5VistaMezclaSucursales;

/**
 *
 * @author alfar
 */
public class E5TestIntercalacionProducto {
    public static void main(String args[]) {
        /* Configuración opcional del Look and Feel (Nimbus) */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(E5VistaMezclaSucursales.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Inicialización de la arquitectura MVC */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                E5VistaMezclaSucursales vistaApp = new E5VistaMezclaSucursales();
                vistaApp.setLocationRelativeTo(null); // Centrar ventana

                // 1. Hacemos visible la interfaz primero para que cargue sus componentes en
                // memoria grafica
                vistaApp.setVisible(true);

                // 2. Ahora el controlador toma el control, lee los archivos planos y pinta las
                // filas
                new controlador.E5CtrlIntercalacionSucursal(vistaApp);

                // 3. Forzar un repintado general de la interfaz por si acaso
                vistaApp.repaint();
            }
        });
    }
}
