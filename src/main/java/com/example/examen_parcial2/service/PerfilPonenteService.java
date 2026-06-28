package com.example.examen_parcial2.service;

import com.example.examen_parcial2.model.PerfilPonente;
import com.example.examen_parcial2.repository.PerfilPonenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilPonenteService {

    @Autowired
    private PerfilPonenteRepository perfilPonenteRepository;

    public List<PerfilPonente> listarTodos() { return perfilPonenteRepository.findAll(); }

    public Optional<PerfilPonente> buscarPorId(Long id) { return perfilPonenteRepository.findById(id); }

    public PerfilPonente guardar(PerfilPonente perfilPonente) { return perfilPonenteRepository.save(perfilPonente); }

    public void eliminar(Long id) { perfilPonenteRepository.deleteById(id); }
}