package mx.com.gm.peliculas.servicio;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.peliculas.datos.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.peliculas.excepciones.AccesoDatosEx;
import mx.com.peliculas.excepciones.LecturaDatosEx;

/**
 *
 * @author Roi
 */
public class CatalogoPeliculasImpl implements ICatalogoPeliculas {
    
    //Se pueden agregar más métodos, pero no es común porque fuera de aquí no puedes usarlos.
    //ESTO ACTUARÍA COMO NUESTRA CAPA DE NEGOCIO; ERGO; ESTÁ MÄS ORIENTADA A QUE EL USUARIO INTERACTÚE CON ELLA
    
    private final IAccesoDatos datos;

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }
    
    @Override
    public void agregarPelicular(String nombrePelicula) {
        try {
            Pelicula pelicula = new Pelicula(nombrePelicula);
            boolean anexar = false; //Para saber si anexamos o sobreescribimos
            
            anexar = datos.existe(NOMBRE_RECURSO); //CTE definida en interfaz.
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
            
            //No hay que abrir y cerrar flujo/archivo ya que de eso se encarga la capa de 
            //acceso a datos.
        } catch (AccesoDatosEx ex) {
            System.out.println("Agregar Peliculas: Error de acceso a datos");
            ex.printStackTrace();
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            //La función de la capa de datos devuelve una List
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            for (var pelicula: peliculas) {
                System.out.println("Pelicula = " + pelicula);
            }
        } catch (LecturaDatosEx ex) {
            System.out.println("Listar Peliculas: Error de accesso a datos");
            ex.printStackTrace();
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (LecturaDatosEx ex) {
            System.out.println("Buscar Pelicula: Error de accso a datos");
            ex.printStackTrace(System.out);
        }
        System.out.println("resultado = " + resultado);
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            //Esto nos vale para reiniciar el archivo si ya existe
            if (this.datos.existe(NOMBRE_RECURSO)) {
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            } else {
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("IniciarCatalogoPeliculas: ERROR.");
            ex.printStackTrace(System.out);
        }
    }
    
}
