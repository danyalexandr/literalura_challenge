package com.aluracursos.literalura_challenge.entity;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;
    private String autor;
    private String idioma;
    private int descargas;

    // Constructor vacío
    public Libro() {
    }

    // Constructor con todos los campos
    public Libro(Long id, String titulo, String autor, String idioma, int descargas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.descargas = descargas;
    }

    // Getters y Setters
    // (Añade getters y setters si es necesario)

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", descargas=" + descargas +
                '}';
    }
}
