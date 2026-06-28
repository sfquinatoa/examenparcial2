package com.example.examen_parcial2.service;

import com.example.examen_parcial2.model.Patrocinador;
import com.example.examen_parcial2.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatrocinadorService {

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    public List<Patrocinador> listarTodos() { return patrocinadorRepository.findAll(); }

    public Optional<Patrocinador> buscarPorId(Long id) { return patrocinadorRepository.findById(id); }

    public Patrocinador guardar(Patrocinador patrocinador) { return patrocinadorRepository.save(patrocinador); }

    public void eliminar(Long id) { patrocinadorRepository.deleteById(id); }

    public List<Patrocinador> buscarPorRangoAporte(Double min, Double max) {
        return patrocinadorRepository.findByMontoAporteBetweenOrderByMontoAporteDesc(min, max);
    }
}
