package com.example.examen_parcial2.controller;

import com.example.examen_parcial2.model.Patrocinador;
import com.example.examen_parcial2.service.PatrocinadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patrocinador")
@CrossOrigin(origins = "*")
public class PatrocinadorController {

    @Autowired
    private PatrocinadorService patrocinadorService;

    @GetMapping
    public List<Patrocinador> listar() { return patrocinadorService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Patrocinador> obtenerPorId(@PathVariable Long id) {
        return patrocinadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patrocinador crear(@Valid @RequestBody Patrocinador pat) { return patrocinadorService.guardar(pat); }

    @PutMapping("/{id}")
    public ResponseEntity<Patrocinador> actualizar(@PathVariable Long id, @Valid @RequestBody Patrocinador pat) {
        return patrocinadorService.buscarPorId(id).map(p -> {
            p.setEmpresa(pat.getEmpresa());
            p.setSector(pat.getSector());
            p.setMontoAporte(pat.getMontoAporte());
            return ResponseEntity.ok(patrocinadorService.guardar(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        patrocinadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/aporte")
    public List<Patrocinador> consulta4(@RequestParam Double min, @RequestParam Double max) {
        return patrocinadorService.buscarPorRangoAporte(min, max);
    }
}
