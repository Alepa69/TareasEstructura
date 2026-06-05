package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ArbolBinarioBusqueda;
import vista.A9EspejosVerificacionArboles;
import vista.ArbolVistaPrincipal;

/**
 * @author alfar
 */
public class ControladorArbolBinarioBusqueda implements ActionListener {

    // Componentes principales
    private ArbolVistaPrincipal vista;
    private ArbolBinarioBusqueda<Integer> modelo;

    // COMPONENTES ADICIONALES PARA EL APARTADO 9 (Declaración que faltaba)
    private A9EspejosVerificacionArboles vistaEspejos;
    private ArbolBinarioBusqueda<Integer> arbolA;
    private ArbolBinarioBusqueda<Integer> arbolB;

    public ControladorArbolBinarioBusqueda(ArbolVistaPrincipal vista, ArbolBinarioBusqueda<Integer> modelo) {
        this.vista = vista;
        this.modelo = modelo;

        // Enlazar componentes de la vista principal
        this.vista.getBtnIngresarDatos().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);
        this.vista.getBtnSonEspejo().addActionListener(this); // Asegúrate de tener este Getter en tu vista principal
        this.vista.getBtnArbolEspejo().addActionListener(this);
        this.vista.getBtnArbolLleno().addActionListener(this);
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

        // Acción al dar click en LIMPIAR (Corregido de getjButton1 a getBtnLimpiar)
        if (e.getSource() == vista.getBtnLimpiar()) {
            modelo = new ArbolBinarioBusqueda<>(); // Resetea el árbol
            limpiarControles();
        }

        // ABRE LA NUEVA INTERFAZ DEL APARTADO 9 (Corregido el acceso estático erróneo)
        if (e.getSource() == vista.getBtnSonEspejo()) {
            if (vistaEspejos == null) {
                vistaEspejos = new A9EspejosVerificacionArboles();
                arbolA = new ArbolBinarioBusqueda<>();
                arbolB = new ArbolBinarioBusqueda<>();

                // Enlazar los botones de la nueva ventana al controlador
                vistaEspejos.getBtnAgregar().addActionListener(this);
                vistaEspejos.getBtnLimpiar().addActionListener(this);
                vistaEspejos.getBtnCompararArboles().addActionListener(this);
                vistaEspejos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            }
            vistaEspejos.setLocationRelativeTo(vista);
            vistaEspejos.setVisible(true);
        }

        // --- EVENTOS DE LA VISTA 9 (ESPEJOS) ---
        if (vistaEspejos != null) {
            if (e.getSource() == vistaEspejos.getBtnAgregar()) {
                // Agregar al Árbol A si hay texto
                if (!vistaEspejos.getTxtDatosArbolA().getText().trim().isEmpty()) {
                    try {
                        int valA = Integer.parseInt(vistaEspejos.getTxtDatosArbolA().getText().trim());
                        arbolA.insertar(valA);
                        vistaEspejos.getTxtDatosArbolA().setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(vistaEspejos, "Dato inválido en Árbol A.");
                    }
                }
                // Agregar al Árbol B si hay texto
                if (!vistaEspejos.getTxtDatosArbolB().getText().trim().isEmpty()) {
                    try {
                        int valB = Integer.parseInt(vistaEspejos.getTxtDatosArbolB().getText().trim());
                        arbolB.insertarInverso(valB); // <--- CAMBIADO AQUÍ
                        vistaEspejos.getTxtDatosArbolB().setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(vistaEspejos, "Dato inválido en Árbol B.");
                    }
                }
                vistaEspejos.getTxtListaArbolA().setText(arbolA.IND().toString());
                vistaEspejos.getTxtListaArbolB().setText(arbolB.IND().toString());
            }

            if (e.getSource() == vistaEspejos.getBtnCompararArboles()) {
                // Compara la raíz de arbolA con la raíz de arbolB usando la lógica del modelo
                boolean resultado = modelo.sonEspejos(arbolA.getRaiz(), arbolB.getRaiz());
                if (resultado) {
                    JOptionPane.showMessageDialog(vistaEspejos, "¡LOS ÁRBOLES SON ESPEJOS!");
                } else {
                    JOptionPane.showMessageDialog(vistaEspejos, "LOS ÁRBOLES NO SON ESPEJOS.");
                }
            }

            if (e.getSource() == vistaEspejos.getBtnLimpiar()) {
                arbolA = new ArbolBinarioBusqueda<>();
                arbolB = new ArbolBinarioBusqueda<>();
                vistaEspejos.getTxtDatosArbolA().setText("");
                vistaEspejos.getTxtDatosArbolB().setText("");
                vistaEspejos.getTxtListaArbolA().setText("");
                vistaEspejos.getTxtListaArbolB().setText("");
            }
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
        // Corregido: Uso de las variables locales correctas (vista y modelo)
        vista.getjTextField1().setText(modelo.IND().toString());
        vista.getTxtPeso().setText(String.valueOf(modelo.calcularPeso()));

        // Apartado 2 Corregido
        vista.getTxtNodosDosHijos().setText(modelo.obtenerNodosDosHijosStr());

        Object max = modelo.encontrarMaximo();
        vista.getTxtValorMaximo().setText(max != null ? max.toString() : "");

        Object min = modelo.encontrarMinimo();
        vista.getTxtValorMinimo().setText(min != null ? min.toString() : "");

        vista.getTxtSumatoria().setText(String.valueOf(modelo.sumarRamaDerecha()));
        vista.getTxtImpares().setText(String.valueOf(modelo.contarImpares()));
        vista.getTxtNodosNivel().setText(modelo.obtenerNodosPorNivelStr());
        vista.getTxtLongitud().setText(String.valueOf(modelo.encontrarLongitud()));

        // Apartado 13 Resuelto dinámicamente
        vista.getTxtNodosRamaLarga().setText(modelo.obtenerRamaMasLargaStr());
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