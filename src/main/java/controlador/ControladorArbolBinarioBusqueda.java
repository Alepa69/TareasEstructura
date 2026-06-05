/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.ArbolBinarioBusqueda;
import vista.ArbolVistaPrincipal;

/**
 *
 * @author alfar
 */
public class ControladorArbolBinarioBusqueda implements ActionListener {

    private ArbolVistaPrincipal vista;
    private ArbolBinarioBusqueda<Integer> modelo;

    public ControladorArbolBinarioBusqueda(ArbolVistaPrincipal vista, ArbolBinarioBusqueda<Integer> modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.getBtnIngresarDatos().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);
        this.vista.btnArbolEspejo.addActionListener(this);
        this.vista.btnArbolLleno.addActionListener(this);
        this.vista.btnArbolLleno.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Accion al dar click en INGRESAR DATOS
        if (e.getSource() == vista.getBtnIngresarDatos()) {
            try {
                int valor = Integer.parseInt(vista.getTxtDatosArbol().getText());
                modelo.insertar(valor);

                // Limpiar la casilla de entrada
                vista.getTxtDatosArbol().setText("");
                vista.getTxtDatosArbol().requestFocus();

                // Actualizar la interfaz con los resultados de los métodos
                actualizarCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingresa un número entero válido.");
            }
        }

        // Acción al dar click en LIMPIAR
        if (e.getSource() == vista.getjButton1()) {
            modelo = new ArbolBinarioBusqueda<>(); // Resetea el árbol
            limpiarControles();
        }

        // Acción: 9. SON ESPEJO
        if (e.getSource() == vista.getBtnSonEspejo()) {
            // Ejemplo de validación local (compara el árbol consigo mismo)
            boolean esEspejoConSiMismo = modelo.sonEspejos(modelo.getRaiz(), modelo.getRaiz());
            JOptionPane.showMessageDialog(vista,
                    "¿El árbol actual es espejo de sí mismo?: " + (esEspejoConSiMismo ? "SÍ" : "NO"));
        }

        // Acción: 10. ARBOL ESPEJO
        if (e.getSource() == vista.getBtnArbolEspejo()) {
            if (modelo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El árbol está vacío.");
                return;
            }
            ArbolBinarioBusqueda<Integer> arbolEspejo = modelo.construirArbolEspejo();
            JOptionPane.showMessageDialog(vista, "¡Árbol espejo creado con éxito!\nInOrden original: " + modelo.IND()
                    + "\nInOrden Espejo: " + arbolEspejo.IND());
        }

        // Acción: 11. ARBOL LLENO
        if (e.getSource() == vista.getBtnArbolLleno()) {
            String msg = modelo.isArbolLleno() ? "¡El árbol está completamente lleno!"
                    : "El árbol NO está completamente lleno.";
            JOptionPane.showMessageDialog(vista, msg);
        }
    }

    private void actualizarCampos() {
        // Muestra la lista de elementos en el campo grande (usando el recorrido
        // InOrden)
        vista.getjTextField1().setText(modelo.IND().toString());

        // 1. Peso del árbol
        vista.getTxtPeso().setText(String.valueOf(modelo.calcularPeso()));

        // 2. Nodos con dos hijos
        vista.getTxtNodosDosHijos().setText(modelo.obtenerNodosDosHijosStr());

        // 3. Valor Máximo
        Object max = modelo.encontrarMaximo();
        vista.getTxtValorMaximo().setText(max != null ? max.toString() : "");

        // 4. Valor Mínimo
        Object min = modelo.encontrarMinimo();
        vista.getTxtValorMinimo().setText(min != null ? min.toString() : "");

        // 5. Sumatoria rama derecha
        vista.getTxtSumatoria().setText(String.valueOf(modelo.sumarRamaDerecha()));

        // 6. Total Impares
        vista.getTxtImpares().setText(String.valueOf(modelo.contarImpares()));

        // 8. Recorrido por Niveles
        vista.getTxtNodosNivel().setText(modelo.obtenerNodosPorNivelStr());

        // 12. Longitud
        vista.getTxtLongitud().setText(String.valueOf(modelo.encontrarLongitud()));

        // 13. Rama más larga
        // Redirigimos temporalmente la consola o acumulamos los valores para mostrarlos
        // limpios
        vista.getTxtNodosRamaLarga().setText("Revisar consola o calcular ruta...");
    }

    private void limpiarControles() {
        vista.getTxtDatosArbol().setText("");
        vista.getjTextField1().setText("");
        vista.getTxtPeso().setText("");
        vista.getTxtNodosDosHijos().setText("");
        vista.getTxtValorMaximo().setText("");
        vista.getTxtValorMinimo().setText("");
        vista.getTxtSumatoria().setText("");
        vista.getTxtImpares().setText("");
        vista.getTxtNodosNivel().setText("");
        vista.getTxtLongitud().setText("");
        vista.getTxtNodosRamaLarga().setText("");
    }

}
