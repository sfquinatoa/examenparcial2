package com.example.examen_parcial2.repository;

import com.example.examen_parcial2.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Consulta 1: Obtener eventos por tipo ordenados por nombre de forma ascendente
    List<Evento> findByTipoOrderByNombreAsc(String tipo);

    // Consulta 3: Eventos con capacidad >= un valor dado y que pertenezcan a un ponente por ID
    List<Evento> findByCapacidadGreaterThanEqualAndPonenteId(Integer capacidad, Long ponenteId);

    // Consulta 5: Obtener eventos de un ponente por ID ordenados por fecha de forma descendente
    List<Evento> findByPonenteIdOrderByFechaDesc(Long ponenteId);
}
