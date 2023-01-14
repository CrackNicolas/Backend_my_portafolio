package com.example.Api_my_portafolio.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pasatiempo")
public class Pasatiempo {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id_pasatiempo;
    
    @NotNull
    @Column(length = 200)
    private String descripcion;
    
    @NotNull
    @Column(length = 20)
    private String nombre_logo;
    
    @NotNull
    @Column(columnDefinition = "enum('Deportes','Instrumento','Otros')")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Persona persona;
}