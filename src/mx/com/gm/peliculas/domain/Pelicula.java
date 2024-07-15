package mx.com.gm.peliculas.domain;

public class Pelicula {
    private String nombre;

    public Pelicula() {
    }
    public Pelicula(String nombre) {
        this.nombre = nombre;
    }

    /*Esta clase actua como un Bean, osea que los atrs deben ser privados
    y debemos de tener un constructor vacío. También es interesante
    encapsular los get/set con la opción correspondiente al generarlos.
    
    Faltaría que implemente Serializable, OJO.
    
    Estas clases del paquete dominio también se les conoce como clases de identidad
    porque sus atributos concuerdan con los de la base de datos.
    */
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Lo dejamos así para facilitar luego la función búsqueda.
    @Override
    public String toString() {
        return this.nombre;
    }
    
}
