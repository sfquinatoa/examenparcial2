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
@Table(name = "ponente")
public class Ponente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;

    @NotBlank
    private String especialidad;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Min(0)
    private Integer anosExperiencia;

    @OneToOne(mappedBy = "ponente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ponente")
    private PerfilPonente perfilPonente;

    @OneToMany(mappedBy = "ponente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ponente")
    private List<Evento> eventos;
}