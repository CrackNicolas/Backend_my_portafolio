package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Red_social;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Red_social_repository;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/red_social")
@CrossOrigin(origins = "http://localhost:4200")
public class Red_social_controller {
    @Autowired
    private Red_social_repository red_social_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/get/id/{id_red_social}")
    public ResponseEntity<Red_social> Get_educacion(@PathVariable("id_red_social") int id_red_social){
        Optional<Red_social> red_social_optional = red_social_repository.findById(id_red_social);
        
        if(!red_social_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(red_social_optional.get());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Red_social>> Get_redes_sociales(){
        return ResponseEntity.ok(red_social_repository.findAll());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Red_social> Add_red_social(@Valid @RequestBody Red_social red_social){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        red_social.setPersona(persona_optional.get());
    
        Red_social save_red_social = red_social_repository.save(red_social);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_red_social}").buildAndExpand(save_red_social.getId_red_social()).toUri();
        return ResponseEntity.created(ubicacion).body(save_red_social);
    }
    
    @DeleteMapping("/delete/id/{id_red_social}")
    public ResponseEntity<Red_social> Delete_red_social(@PathVariable("id_red_social") int id_red_social){
        Optional<Red_social> red_social_optional = red_social_repository.findById(id_red_social);
        if(!red_social_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        red_social_repository.delete(red_social_optional.get());
        
        return ResponseEntity.noContent().build(); 
    }
}