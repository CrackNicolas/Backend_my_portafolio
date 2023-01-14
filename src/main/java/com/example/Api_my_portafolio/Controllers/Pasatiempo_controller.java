package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Pasatiempo;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Pasatiempo_repository;
import com.example.Api_my_portafolio.Repositories.Persona_repository;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/pasatiempo")
@CrossOrigin(origins = "http://localhost:4200")
public class Pasatiempo_controller {
    @Autowired
    private Pasatiempo_repository pasatiempo_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/get/id/{id_pasatiempo}")
    public ResponseEntity<Pasatiempo> Get_pasatiempo(@PathVariable("id_pasatiempo") int id_pasatiempo){
        Optional<Pasatiempo> educacion_optional = pasatiempo_repository.findById(id_pasatiempo);
        
        if(!educacion_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(educacion_optional.get());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Pasatiempo>> Get_pasatiempos(){
        return ResponseEntity.ok(pasatiempo_repository.findAll());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Pasatiempo> Add_pasatiempo(@Valid @RequestBody Pasatiempo pasatiempo){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        pasatiempo.setPersona(persona_optional.get());
    
        Pasatiempo save_pasatiempo = pasatiempo_repository.save(pasatiempo);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_pasatiempo}").buildAndExpand(save_pasatiempo.getId_pasatiempo()).toUri();
        return ResponseEntity.created(ubicacion).body(save_pasatiempo);
    }
    
    @PutMapping("/edit/id/{id_pasatiempo}")
    public ResponseEntity<Pasatiempo> Edit_pasatiempo(@PathVariable("id_pasatiempo") int id_pasatiempo, @Valid @RequestBody Pasatiempo pasatiempo){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Pasatiempo> pasatiempo_optional = pasatiempo_repository.findById(id_pasatiempo);
        if(!pasatiempo_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        pasatiempo.setPersona(persona_optional.get());
        pasatiempo.setId_pasatiempo(pasatiempo_optional.get().getId_pasatiempo());
        pasatiempo_repository.save(pasatiempo);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/delete/id/{id_pasatiempo}")
    public ResponseEntity<Pasatiempo> Delete_pasatiempo(@PathVariable("id_pasatiempo") int id_pasatiempo){
        Optional<Pasatiempo> pasatiempo_optional = pasatiempo_repository.findById(id_pasatiempo);
        if(!pasatiempo_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        pasatiempo_repository.delete(pasatiempo_optional.get());
        
        return ResponseEntity.noContent().build(); 
    }
}