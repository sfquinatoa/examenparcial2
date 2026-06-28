package com.example.examen_parcial2.service;
import com.example.examen_parcial2.model.Evento;
import com.example.examen_parcial2.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() { return eventoRepository.findAll(); }

    public Optional<Evento> buscarPorId(Long id) { return eventoRepository.findById(id); }

    public Evento guardar(Evento evento) { return eventoRepository.save(evento); }

    public void eliminar(Long id) { eventoRepository.deleteById(id); }

    public List<Evento> buscarPorTipo(String tipo) {
        return eventoRepository.findByTipoOrderByNombreAsc(tipo);
    }

    public List<Evento> buscarPorCapacidadYPonente(Integer capacidad, Long ponenteId) {
        return eventoRepository.findByCapacidadGreaterThanEqualAndPonenteId(capacidad, ponenteId);
    }

    public List<Evento> buscarPorPonenteIdOrdenado(Long ponenteId) {
        return eventoRepository.findByPonenteIdOrderByFechaDesc(ponenteId);
    }
}
