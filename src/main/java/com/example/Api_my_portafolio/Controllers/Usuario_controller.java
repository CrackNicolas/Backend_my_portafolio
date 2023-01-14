package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Usuario;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Persona_repository;
import com.example.Api_my_portafolio.Repositories.Usuario_repository;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class Usuario_controller {
    @Autowired
    private Usuario_repository usuario_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/get/id/{id_usuario}")
    public ResponseEntity<Usuario> Get_usuario(@PathVariable("id_usuario") int id_usuario){
        Optional<Usuario> usuario_optional = usuario_repository.findById(id_usuario);
        
        if(!usuario_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(usuario_optional.get());
    }
    
    @GetMapping("/autenticar/{nombre}/{password}")
    public boolean Add_autenticar(@PathVariable("nombre") String nombre, @PathVariable("password") String password){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        Usuario user = persona_optional.get().getUsuario();
        return user.getNombre().equals(nombre) && user.getPassword().equals(password);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Usuario> Add_usuario(@Valid @RequestBody Usuario usuario){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setPersona(persona_optional.get());
    
        Usuario save_usuario = usuario_repository.save(usuario);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_usuario}").buildAndExpand(save_usuario.getId_usuario()).toUri();
        return ResponseEntity.created(ubicacion).body(save_usuario);
    }
    
    @PutMapping("/edit/id/{id_usuario}")
    public ResponseEntity<Usuario> Edit_usuario(@PathVariable("id_usuario") int id_usuario, @Valid @RequestBody Usuario usuario){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Usuario> usuario_optional = usuario_repository.findById(id_usuario);
        if(!usuario_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setPersona(persona_optional.get());
        usuario.setId_usuario(usuario_optional.get().getId_usuario());
        usuario_repository.save(usuario);
        
        return ResponseEntity.noContent().build();
    }
}