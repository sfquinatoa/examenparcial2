package com.example.examen_parcial2.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "evento")
    public class Evento {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size(min = 5, max = 150)
        private String nombre;

        @NotBlank
        private String tipo;

        @NotBlank
        private String fecha;

        @NotNull
        @Min(1)
        private Integer capacidad;

        @ManyToOne
        @JoinColumn(name = "ponente_id")
        @JsonIgnoreProperties("eventos")
        private Ponente ponente;

        @ManyToMany(mappedBy = "eventos")
        @JsonIgnoreProperties("eventos")
        private List<Patrocinador> patrocinadores;
    }

