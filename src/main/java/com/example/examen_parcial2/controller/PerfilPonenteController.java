package com.example.examen_parcial2.controller;

import com.example.examen_parcial2.model.PerfilPonente;
import com.example.examen_parcial2.service.PerfilPonenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/perfil-ponente")
@CrossOrigin(origins = "*")
public class PerfilPonenteController {

    @Autowired
    private PerfilPonenteService perfilPonenteService;

//    @GetMapping
//    public List<PerfilPonente> listar() { return perfilPonenteService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilPonente> obtenerPorId(@PathVariable Long id) {
        return perfilPonenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PerfilPonente crear(@RequestBody PerfilPonente perfil) {
        return perfilPonenteService.guardar(perfil);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PerfilPonente> actualizar(@PathVariable Long id, @Valid @RequestBody PerfilPonente perfil) {
        return perfilPonenteService.buscarPorId(id).map(p -> {
            p.setBiografia(perfil.getBiografia());
            p.setLinkedin(perfil.getLinkedin());
            p.setPaginaWeb(perfil.getPaginaWeb());
            p.setPonente(perfil.getPonente());
            return ResponseEntity.ok(perfilPonenteService.guardar(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        perfilPonenteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}