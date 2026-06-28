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
@Table(name = "patrocinador")
public class Patrocinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String empresa;

    @NotBlank
    private String sector;

    @NotNull
    @Min(1)
    private Double montoAporte;

    @ManyToMany
    @JoinTable(
            name = "evento_patrocinador",
            joinColumns = @JoinColumn(name = "patrocinador_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    @JsonIgnoreProperties("patrocinadores")
    private List<Evento> eventos;
}
