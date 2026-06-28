package com.example.examen_parcial2.controller;
import com.example.examen_parcial2.model.Ponente;
import com.example.examen_parcial2.service.PonenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ponente")
@CrossOrigin(origins = "*")
public class PonenteController {

    @Autowired
    private PonenteService ponenteService;

    @GetMapping
    public List<Ponente> listar() { return ponenteService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Ponente> obtenerPorId(@PathVariable Long id) {
        return ponenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ponente crear(@Valid @RequestBody Ponente p) { return ponenteService.guardar(p); }

    @PutMapping("/{id}")
    public ResponseEntity<Ponente> actualizar(@PathVariable Long id, @Valid @RequestBody Ponente pon) {
        return ponenteService.buscarPorId(id).map(p -> {
            p.setNombre(pon.getNombre());
            p.setEspecialidad(pon.getEspecialidad());
            p.setEmail(pon.getEmail());
            p.setAnosExperiencia(pon.getAnosExperiencia());
            return ResponseEntity.ok(ponenteService.guardar(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ponenteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/especialidad")
    public List<Ponente> consulta2(@RequestParam String palabra) {
        return ponenteService.buscarPorEspecialidad(palabra);
    }
}