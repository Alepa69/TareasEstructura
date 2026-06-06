/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alfar
 */
public class E5ModeloIntercalacionProducto implements Comparable<E5ModeloIntercalacionProducto> {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public E5ModeloIntercalacionProducto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public int compareTo(E5ModeloIntercalacionProducto o) {
        return this.codigo.compareTo(o.getCodigo());
    }

    @Override
    public String toString() {
        return codigo + "," + nombre + "," + precio + "," + cantidad;
    }

}
