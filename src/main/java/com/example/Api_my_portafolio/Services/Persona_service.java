package com.example.Api_my_portafolio.Services;

import com.example.Api_my_portafolio.Interfaces.Persona_interface;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Persona_repository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Persona_service implements Persona_interface{
    @Autowired
    private Persona_repository persona_repository;
    
    @Override
    public Persona Add_persona(Persona persona) {
        return persona_repository.save(persona);
    }

    @Override
    public Optional<Persona> Get_persona(int id_persona) {
        return persona_repository.findById(id_persona);
    }
}