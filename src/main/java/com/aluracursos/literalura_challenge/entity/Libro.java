package com.aluracursos.literalura_challenge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column 
    private String título;
    
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Column
    private String idiomas;

    @Column
    private Integer descargas;

    public Libro() {
    }

    public Libro(String título, Autor autor, String idiomas, Integer descargas) {
        this.título = título;
        this.autor = autor;
        this.idiomas = idiomas;
        this.descargas = descargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    
}
