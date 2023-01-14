package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Educacion;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Educacion_repository;
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
@RequestMapping("/api/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class Educacion_controller {
    @Autowired
    private Educacion_repository educacion_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/get/id/{id_educacion}")
    public ResponseEntity<Educacion> Get_educacion(@PathVariable("id_educacion") int id_educacion){
        Optional<Educacion> educacion_optional = educacion_repository.findById(id_educacion);
        
        if(!educacion_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(educacion_optional.get());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Educacion>> Get_educaciones(){
        return ResponseEntity.ok(educacion_repository.findAll());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Educacion> Add_educacion(@Valid @RequestBody Educacion educacion){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        educacion.setPersona(persona_optional.get());
    
        Educacion save_educacion = educacion_repository.save(educacion);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_educacion}").buildAndExpand(save_educacion.getId_educacion()).toUri();
        return ResponseEntity.created(ubicacion).body(save_educacion);
    }
    
    @PutMapping("/edit/id/{id_educacion}")
    public ResponseEntity<Educacion> Edit_educacion(@PathVariable("id_educacion") int id_educacion, @Valid @RequestBody Educacion educacion){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Educacion> educacion_optional = educacion_repository.findById(id_educacion);
        if(!educacion_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        educacion.setPersona(persona_optional.get());
        educacion.setId_educacion(educacion_optional.get().getId_educacion());
        educacion_repository.save(educacion);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/delete/id/{id_educacion}")
    public ResponseEntity<Educacion> Delete_educacion(@PathVariable("id_educacion") int id_educacion){
        Optional<Educacion> educacion_optional = educacion_repository.findById(id_educacion);
        if(!educacion_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        educacion_repository.delete(educacion_optional.get());
        
        return ResponseEntity.noContent().build(); 
    }
}