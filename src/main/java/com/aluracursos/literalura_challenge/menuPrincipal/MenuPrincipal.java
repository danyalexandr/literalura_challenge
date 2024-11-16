package com.aluracursos.literalura_challenge.menuPrincipal;

import com.aluracursos.literalura_challenge.entity.Libro;
import com.aluracursos.literalura_challenge.model.DatosLibro;
import com.aluracursos.literalura_challenge.model.DatosLista;
import com.aluracursos.literalura_challenge.repository.LibroRepository;
import com.aluracursos.literalura_challenge.service.ConsumoAPI;
import com.aluracursos.literalura_challenge.service.ConvierteDatos;
import java.util.Scanner;


public class MenuPrincipal {
    
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private LibroRepository repository;

    //constructor
    public MenuPrincipal(LibroRepository repository) {
        this.repository = repository;
    }

    //metodo
    public String mostrarMenu() {
        
        var mensaje = "hola mundo metodo";
        return mensaje;
    }
    
public Libro buscarLibro() {
    // Solicitar el título al usuario
    System.out.println("¿Qué título buscas?");
    String titulo = teclado.nextLine(); // Capturar el título ingresado por el usuario

    // Construir la URL para la API de Gutendex
    String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");

    // Realizar la solicitud y obtener el JSON
    String json = consumoApi.obtenerDatos(url);

    // Convertir el JSON en un objeto DatosLista
    DatosLista datosLista = conversor.obtenerDatos(json, DatosLista.class);

    // Verificar si la lista de resultados contiene algún libro
    if (datosLista != null && datosLista.resultado() != null && !datosLista.resultado().isEmpty()) {
        // Obtener el primer libro de la lista
        DatosLibro datosLibro = datosLista.resultado().get(0);

        // Crear un objeto de tipo Libros a partir de los datos del libro
        var libro = new Libro(
                datosLibro.id(),                 // ID
                datosLibro.titulo(),             // Título
                datosLibro.autor().get(0).nombre(), // Nombre del primer autor
                datosLibro.lenguaje().get(0),    // Primer idioma
                datosLibro.descargas()           // Número de descargas
        );

        // Guardar el libro en la base de datos
        repository.save(libro);
        System.out.println("Libro encontrado y guardado exitosamente: " + libro);

        // Retornar el libro
        return libro;
    } else {
        // Manejo de caso donde no se encuentran resultados
        System.out.println("No se encontraron resultados para el título ingresado.");
        return null;
    }
}



    
}
