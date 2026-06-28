package com.example.examen_parcial2.controller;
import com.example.examen_parcial2.model.Evento;
import com.example.examen_parcial2.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/evento")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @GetMapping
    public List<Evento> listar() { return eventoService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerPorId(@PathVariable Long id) {
        return eventoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evento crear(@RequestBody Evento evento) {
        return eventoService.guardar(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizar(@PathVariable Long id, @Valid @RequestBody Evento ev) {
        return eventoService.buscarPorId(id).map(e -> {
            e.setNombre(ev.getNombre());
            e.setTipo(ev.getTipo());
            e.setFecha(ev.getFecha());
            e.setCapacidad(ev.getCapacidad());
            e.setPonente(ev.getPonente());
            return ResponseEntity.ok(eventoService.guardar(e));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/buscar/tipo")
    public List<Evento> consulta1(@RequestParam String tipo) {
        return eventoService.buscarPorTipo(tipo);
    }

    @GetMapping("/buscar/capacidad-ponente")
    public List<Evento> consulta3(@RequestParam Integer capacidad, @RequestParam Long ponenteId) {
        return eventoService.buscarPorCapacidadYPonente(capacidad, ponenteId);
    }

    @GetMapping("/buscar/ponente-fecha")
    public List<Evento> consulta5(@RequestParam Long ponenteId) {
        return eventoService.buscarPorPonenteIdOrdenado(ponenteId);
    }
}