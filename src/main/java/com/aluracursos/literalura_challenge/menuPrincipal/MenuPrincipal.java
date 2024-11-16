package com.aluracursos.literalura_challenge.menuPrincipal;

import com.aluracursos.literalura_challenge.entity.Libro;
import com.aluracursos.literalura_challenge.model.DatosLibro;
import com.aluracursos.literalura_challenge.model.DatosLista;
import com.aluracursos.literalura_challenge.repository.LibroRepository;
import com.aluracursos.literalura_challenge.service.ConsumoAPI;
import com.aluracursos.literalura_challenge.service.ConvierteDatos;
import java.util.List;
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
    public void mostrarMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    //busca el libro por titulo
                    buscarLibro();
                    break;
                case 2:
                    // metodo de libros registrados en db
                    System.out.println("Libros registrados:");
                    librosRegistrados();

                    break;
                case 3:
                    // metodo de autores registrados en db

                    break;
                case 4:
                    // metodo autores vivos en x año
                    break;
                case 5:
                    // metodo de libros por idioma
                    System.out.println("Ingrese el idioma (código ISO, por ejemplo, 'en'):");
                    String idioma = teclado.nextLine();
                    obtenerLibrosPorIdioma(idioma);

                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
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
                    datosLibro.id(), // ID
                    datosLibro.titulo(), // Título
                    datosLibro.autor().get(0).nombre(), // Nombre del primer autor
                    datosLibro.lenguaje().get(0), // Primer idioma
                    datosLibro.descargas() // Número de descargas
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

    public void librosRegistrados() {
        var librosRegistrados = repository.findAll();
        System.out.println("Estos son los libros registrados" + librosRegistrados);
    }

    public void obtenerLibrosPorIdioma(String idioma) {
        var resultado = repository.findByIdioma(idioma);
        System.out.println("Estos son los resultados en ese idioma" + resultado);
    }

}
