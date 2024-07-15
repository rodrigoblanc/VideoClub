package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.peliculas.excepciones.*;

/**
 *
 * @author Roi
 */
public class AccesoDatosImpl implements IAccesoDatos{

    
    //Podemos poner var en vez de File para los archivos.
    @Override
    public boolean existe(String nombreArchivo) {
        var archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList();
        
        try {
            //Nos apoyamos en BufferedReader porque FileReader no lee líneas enteras.
            var entrada = new BufferedReader(new FileReader(archivo));
            
            //Ahora leemos cada línea:
            String linea = null;
            
            try {
                //En general le añadimos un bloque try-catch a todas las funciones
                //que puedan devolver una excepción.
                linea = entrada.readLine();
                
                while(linea != null){
                    var pelicula = new Pelicula(linea);
                    peliculas.add(pelicula);
                    linea = entrada.readLine();
                }
                entrada.close();
                
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new LecturaDatosEx("Exception: Listar Películas -> "+ex.getMessage());
            }
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            //Y ahora propagamos la excepción
            throw new LecturaDatosEx("Exception: Listar Películas -> "+ex.getMessage());
        }
        
        return peliculas;
        
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito información al archivo: "+pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Exception: Listar Películas -> "+ex.getMessage());
        }
        
    }

    @Override
    public String buscar(String nombre, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombre);
        String resultado = null;
        
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            try {
                linea = entrada.readLine();
            } catch (IOException ex) {
                Logger.getLogger(AccesoDatosImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            var indice = 1;
            while(linea != null) {
                if (buscar != null && buscar.equalsIgnoreCase(linea)) {
                    resultado = "Pelicula "+linea+" encontrada en el índice "+indice;
                    break;
                }
                try {
                    linea = entrada.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(AccesoDatosImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                indice++;
            }
            entrada.close();
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
            throw new LecturaDatosEx("Exception: Listar Películas -> "+ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(AccesoDatosImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Archivo creado.");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Exception: Crear Archivo -> "+ex.getMessage());
        }
        
    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        
        if (archivo.exists())
            archivo.delete();
        System.out.println("Archivo borrado.");
    }
    
}
