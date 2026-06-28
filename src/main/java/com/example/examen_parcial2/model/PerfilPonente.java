package com.example.examen_parcial2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil_ponente")
public class PerfilPonente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 500)
    private String biografia;

    @Size(max = 255)
    private String linkedin;

    @Size(max = 255)
    private String paginaWeb;

    @OneToOne
    @JoinColumn(name = "ponente_id")
    @JsonIgnoreProperties("perfilPonente")
    private Ponente ponente;
}
