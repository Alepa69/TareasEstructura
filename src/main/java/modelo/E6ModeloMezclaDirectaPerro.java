
package modelo;

/**
 *
 * @author PC
 */
public class E6ModeloMezclaDirectaPerro implements Comparable<E6ModeloMezclaDirectaPerro> {

    private String nombre;
    private int edad;
    private String raza;

    public E6ModeloMezclaDirectaPerro() {
    }

    public E6ModeloMezclaDirectaPerro(String nombre, int edad, String raza) {
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    // Se ordena por edad ascendente (puedes cambiarlo por nombre o raza si quieres)
    @Override
    public int compareTo(E6ModeloMezclaDirectaPerro otro) {
        return Integer.compare(this.edad, otro.edad);
    }

    // Cada objeto en una línea con atributos separados por comas
    @Override
    public String toString() {
        return nombre + "," + edad + "," + raza;
    }
}