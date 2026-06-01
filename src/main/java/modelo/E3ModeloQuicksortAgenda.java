
package modelo;

/**
 *
 * @author alfar
 */
public class E3ModeloQuicksortAgenda {
        private String nombre;
    private String numero; // Usamos String para admitir guiones o prefijos (ej: +503)

    public E3ModeloQuicksortAgenda(String nombre, String numero) {
        this.nombre = nombre;
        this.numero = numero;
    }
    
    // GETTERS: Vitales para que el QuickSort y la Tabla funcionen
    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    // SETTERS: Por si necesitas editar un contacto después
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
