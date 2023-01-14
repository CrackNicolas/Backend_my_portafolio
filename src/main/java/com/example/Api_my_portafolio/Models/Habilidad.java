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
@Table(name = "habilidad")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_habilidad;
    
    @NotNull
    @Column(length = 30)
    private String nombre;
    
    @NotNull
    private int porcentaje;
    
    @NotNull
    @Column(length = 45)
    private String nombre_icono;
    
    @NotNull
    @Column(columnDefinition = "enum('blanda','tecnica','gestion_de_proyecto','controlador_de_versiones')")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    
    @NotNull
    @Column(columnDefinition = "enum('uso_actual','uso_antiguo')")
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Persona persona;
}