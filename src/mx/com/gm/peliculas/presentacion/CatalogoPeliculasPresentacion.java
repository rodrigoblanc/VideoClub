//Este paquete podría ser considerado como la VISTA, dado que es la que 
//se encarga de mostrar la info al usuario y recoger lo que quiere hacer.


package mx.com.gm.peliculas.presentacion;

import java.util.Scanner;
import mx.com.gm.peliculas.servicio.*;

public class CatalogoPeliculasPresentacion {
    public static void main(String[] args) {
        var option = -1;
        var scanner = new Scanner(System.in);
        ICatalogoPeliculas catalogo = new CatalogoPeliculasImpl();
        
        
        
        while (option != 0) {
            System.out.println("Escoge una opción: \n"
                    + "1. Iniciar Catálogo \n"
                    + "2. Agregar Pelicula \n"+
                    "3.Listar Peliculas \n"+
                    "4. Buscar Pelicula \n"+
                    "0. Salir");
            
            option =Integer.parseInt(scanner.nextLine());
            
            //Y ahora metemos un switch para procesar la entrada.
            switch(option) {
                case 1: 
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.println("Introduce el nombre de la película");
                    var nombrePelicula = scanner.nextLine();
                    catalogo.agregarPelicular(nombrePelicula);
                    break;
                case 3:
                    catalogo.listarPeliculas();
                    break;
                case 4:
                    System.out.println("Introduzca el nombre de la película");
                    var buscar = scanner.nextLine();
                    catalogo.buscarPelicula(buscar);
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("Opción no reconocida");
                    break;
            }
            //Gracias a la capa de negocio, aquí ya no tenemos que gestionar
            //las excepciones, así que el código nos queda más limpio
        }
    }
}
