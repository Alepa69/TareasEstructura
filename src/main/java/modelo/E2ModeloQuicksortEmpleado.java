
package modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author alfar
 */
public class E2ModeloQuicksortEmpleado {
        private String nombre;
    private double salario;
    private LocalDate fechaIngreso;

    public E2ModeloQuicksortEmpleado(String nombre, double salario, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }
    
    //METODO PARA CALCULAR LA ANTIGUEDAD UNICAMENTE
    public int getAntiguedad(){
        return Period.between(fechaIngreso, LocalDate.now()).getYears();
    }
    
    //GETTER
    public String getNombre(){
        return nombre;
    }
    
    public double getSalario(){
        return salario;
    }
    
    public LocalDate getFechaIngreso(){
        return fechaIngreso;
    }
}
