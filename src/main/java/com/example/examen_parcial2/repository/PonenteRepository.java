package com.example.examen_parcial2.repository;

import com.example.examen_parcial2.model.Ponente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PonenteRepository extends JpaRepository<Ponente, Long> {
    // Consulta 2: Buscar ponentes cuya especialidad contenga palabra clave (case-insensitive)
    List<Ponente> findByEspecialidadContainingIgnoreCase(String palabra);
}
