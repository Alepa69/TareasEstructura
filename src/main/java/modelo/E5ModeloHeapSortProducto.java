
package modelo;

/**
 *
 * @author PC
 */
public class E5ModeloHeapSortProducto implements Comparable<E5ModeloHeapSortProducto> {
    private String codigo;
    private String nombre;
    private E5EnumHeapSortClasificacion clasificacion;

    public E5ModeloHeapSortProducto(String codigo, String nombre, E5EnumHeapSortClasificacion clasificacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public E5EnumHeapSortClasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(E5EnumHeapSortClasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public int compareTo(E5ModeloHeapSortProducto otro) {
        return this.codigo.compareTo(otro.getCodigo());
    }
}
