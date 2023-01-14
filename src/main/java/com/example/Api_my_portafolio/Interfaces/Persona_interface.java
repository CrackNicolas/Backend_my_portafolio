package com.example.Api_my_portafolio.Interfaces;

import com.example.Api_my_portafolio.Models.Persona;
import java.util.Optional;

public interface Persona_interface {
    public Persona Add_persona(Persona persona);
    public Optional<Persona> Get_persona(int id_persona);
}