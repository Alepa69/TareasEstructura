
package modelo;

/**
 *
 * @author PC
 */
public class E7ModeloMezclaNaturalEmpleados implements Comparable<E7ModeloMezclaNaturalEmpleados>{
    private int id;
    private String dui;
    private String nombre;
    private String telefono;
    private String correo;
    private String cargo;
    private String fechaContrato;
    private double salario;

    public E7ModeloMezclaNaturalEmpleados() {
    }

    public E7ModeloMezclaNaturalEmpleados(int id, String dui, String nombre, String telefono,
            String correo, String cargo, String fechaContrato, double salario) {
        this.id = id;
        this.dui = dui;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.cargo = cargo;
        this.fechaContrato = fechaContrato;
        this.salario = salario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDui() { return dui; }
    public void setDui(String dui) { this.dui = dui; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getFechaContrato() { return fechaContrato; }
    public void setFechaContrato(String fechaContrato) { this.fechaContrato = fechaContrato; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    // Se ordena por salario ascendente (segun lo pide el ejercicio)
    @Override
    public int compareTo(E7ModeloMezclaNaturalEmpleados otro) {
        return Double.compare(this.salario, otro.salario);
    }

    // Cada objeto en una linea con atributos separados por comas
    @Override
    public String toString() {
        return id + "," + dui + "," + nombre + "," + telefono + "," + correo + ","
                + cargo + "," + fechaContrato + "," + salario;
    }
}
