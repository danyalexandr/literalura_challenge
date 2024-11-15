package com.aluracursos.literalura_challenge.menuPrincipal;

import com.aluracursos.literalura_challenge.entity.Libro;
import com.aluracursos.literalura_challenge.repository.LibroRepository;
import com.aluracursos.literalura_challenge.service.ConsumoAPI;


public class MenuPrincipal {
    
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
    
    public void buscarLibro(){
        
    var json = consumoApi.obtenerDatos("https://gutendex.com/books/?search=");
        System.out.println(json);
//        DatosLibro datos = getDatosLibro();
//        Libro libro = new Libro(datos);
//        repository.save(libro);
//        System.out.println(libro);
    }
    
}
