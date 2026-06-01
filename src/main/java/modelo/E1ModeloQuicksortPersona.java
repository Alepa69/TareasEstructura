
package modelo;

/**
 *
 * @author alfar
 */
public class E1ModeloQuicksortPersona {
        private String nombre;
    private double altura;

    public E1ModeloQuicksortPersona(String nombre, double altura) {
        this.nombre = nombre;
        this.altura = altura;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public double getAltura(){
        return altura;
    }

    @Override
    public String toString() {
        return nombre + "(" + altura + "cm)";
    }
}
