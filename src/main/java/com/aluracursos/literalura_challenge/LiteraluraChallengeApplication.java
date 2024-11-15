package com.aluracursos.literalura_challenge;

import com.aluracursos.literalura_challenge.menuPrincipal.MenuPrincipal;
import com.aluracursos.literalura_challenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraChallengeApplication implements CommandLineRunner{
    
    @Autowired
    private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraChallengeApplication.class, args);         
	}

    @Override
    public void run(String... args) throws Exception {
        
        var menuPrincipal = new MenuPrincipal(repository);
        menuPrincipal.buscarLibro();
        
        
    }
    
}
