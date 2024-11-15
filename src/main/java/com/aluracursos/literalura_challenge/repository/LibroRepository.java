package com.aluracursos.literalura_challenge.repository;

import com.aluracursos.literalura_challenge.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {}
