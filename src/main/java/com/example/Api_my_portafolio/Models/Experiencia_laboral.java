package com.example.Api_my_portafolio.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "experiencia_laboral")
public class Experiencia_laboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_experiencia;
    
    @NotNull
    @Column(length = 50)
    private String empresa;
    
    @NotNull
    @Column(length = 45)
    private String puesto;
    
    @NotNull
    @Column(length = 250)
    private String descripcion;
    
    @NotNull
    @Column(name = "fecha_inicio", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date fecha_inicio;
    
    @NotNull
    @Column(name = "fecha_fin", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date fecha_fin;
    
    @NotNull
    @Column(length = 150)
    private String url_imagen;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Persona persona;
}