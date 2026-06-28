package com.example.examen_parcial2.repository;

import com.example.examen_parcial2.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Long> {

    List<Patrocinador> findByMontoAporteBetweenOrderByMontoAporteDesc(Double min, Double max);
}