package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Proyecto;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Proyecto_repository;
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
@RequestMapping("/api/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class Proyecto_controller{
    @Autowired
    private Proyecto_repository proyecto_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/get/id/{id_proyecto}")
    public ResponseEntity<Proyecto> Get_proyecto(@PathVariable("id_proyecto") int id_proyecto){
        Optional<Proyecto> proyecto_optional = proyecto_repository.findById(id_proyecto);
        
        if(!proyecto_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(proyecto_optional.get());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Proyecto>> Get_proyectos(){
        return ResponseEntity.ok(proyecto_repository.findAll());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Proyecto> Add_proyecto(@Valid @RequestBody Proyecto proyecto){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        proyecto.setPersona(persona_optional.get());
    
        Proyecto save_proyecto = proyecto_repository.save(proyecto);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_proyecto}").buildAndExpand(save_proyecto.getId_proyecto()).toUri();
        return ResponseEntity.created(ubicacion).body(save_proyecto);
    }
    
    @PutMapping("/edit/id/{id_proyecto}")
    public ResponseEntity<Proyecto> Edit_proyecto(@PathVariable("id_proyecto") int id_proyecto, @Valid @RequestBody Proyecto proyecto){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Proyecto> proyecto_optional = proyecto_repository.findById(id_proyecto);
        if(!proyecto_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        proyecto.setPersona(persona_optional.get());
        proyecto.setId_proyecto(proyecto_optional.get().getId_proyecto());
        proyecto_repository.save(proyecto);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/delete/id/{id_proyecto}")
    public ResponseEntity<Proyecto> Delete_proyecto(@PathVariable("id_proyecto") int id_proyecto){
        Optional<Proyecto> proyecto_optional = proyecto_repository.findById(id_proyecto);
        if(!proyecto_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        proyecto_repository.delete(proyecto_optional.get());
        
        return ResponseEntity.noContent().build(); 
    }
}