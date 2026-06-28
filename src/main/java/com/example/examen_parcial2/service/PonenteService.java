package com.example.examen_parcial2.service;

import com.example.examen_parcial2.model.Ponente;
import com.example.examen_parcial2.repository.PonenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PonenteService {

    @Autowired
    private PonenteRepository ponenteRepository;

    public List<Ponente> listarTodos() { return ponenteRepository.findAll(); }

    public Optional<Ponente> buscarPorId(Long id) { return ponenteRepository.findById(id); }

    public Ponente guardar(Ponente ponente) { return ponenteRepository.save(ponente); }

    public void eliminar(Long id) { ponenteRepository.deleteById(id); }

    public List<Ponente> buscarPorEspecialidad(String palabra) {
        return ponenteRepository.findByEspecialidadContainingIgnoreCase(palabra);
    }
}