package com.aluracursos.literalura_challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") Long id,                     // Identificador del libro
        @JsonAlias("title") String titulo,               // Título del libro
        @JsonAlias("authors") List<DatosAutor> autor,    // Lista de autores (modelada con DatosAutor)
        @JsonAlias("translators") List<DatosAutor> translators, // Traductores
        @JsonAlias("subjects") List<String> subject,     // Lista de temas del libro
        @JsonAlias("bookshelves") List<String> bookshelves,     // Estanterías/colecciones
        @JsonAlias("languages") List<String> lenguaje,   // Idiomas del libro
        @JsonAlias("copyright") boolean copyright,       // Información sobre derechos de autor
        @JsonAlias("media_type") String media_type,      // Tipo de medio (Texto, etc.)
        @JsonAlias("formats") Map<String, String> formato, // Map con formatos del libro y sus URLs
        @JsonAlias("download_count") Integer descargas   // Número de descargas
) {
}
