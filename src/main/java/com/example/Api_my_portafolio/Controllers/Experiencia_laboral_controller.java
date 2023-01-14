package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Experiencia_laboral;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Experiencia_laboral_repository;
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
@RequestMapping("/api/experiencia_laboral")
@CrossOrigin(origins = "http://localhost:4200")
public class Experiencia_laboral_controller {
    @Autowired
    private Experiencia_laboral_repository experiencia_laboral_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/get/id/{id_experiencia}")
    public ResponseEntity<Experiencia_laboral> Get_experiencia_laboral(@PathVariable("id_experiencia") int id_experiencia){
        Optional<Experiencia_laboral> experiencia_optional = experiencia_laboral_repository.findById(id_experiencia);
        
        if(!experiencia_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(experiencia_optional.get());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Experiencia_laboral>> Get_experiencias_laborales(){
        return ResponseEntity.ok(experiencia_laboral_repository.findAll());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Experiencia_laboral> Add_experiencia_laboral(@Valid @RequestBody Experiencia_laboral experiencia_laboral){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        experiencia_laboral.setPersona(persona_optional.get());
    
        Experiencia_laboral save_experiencia_laboral = experiencia_laboral_repository.save(experiencia_laboral);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_experiencia}").buildAndExpand(save_experiencia_laboral.getId_experiencia()).toUri();
        return ResponseEntity.created(ubicacion).body(save_experiencia_laboral);
    }
    
    @PutMapping("/edit/id/{id_experiencia}")
    public ResponseEntity<Experiencia_laboral> Edit_experiencia_laboral(@PathVariable("id_experiencia") int id_experiencia, @Valid @RequestBody Experiencia_laboral experiencia_laboral){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Experiencia_laboral> experiencia_laboral_optional = experiencia_laboral_repository.findById(id_experiencia);
        if(!experiencia_laboral_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        experiencia_laboral.setPersona(persona_optional.get());
        experiencia_laboral.setId_experiencia(experiencia_laboral_optional.get().getId_experiencia());
        experiencia_laboral_repository.save(experiencia_laboral);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/delete/id/{id_experiencia}")
    public ResponseEntity<Experiencia_laboral> Delete_experiencia_laboral(@PathVariable("id_experiencia") int id_experiencia){
        Optional<Experiencia_laboral> experiencia_laboral_optional = experiencia_laboral_repository.findById(id_experiencia);
        if(!experiencia_laboral_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        experiencia_laboral_repository.delete(experiencia_laboral_optional.get());
        
        return ResponseEntity.noContent().build(); 
    }
}