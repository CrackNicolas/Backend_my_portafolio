package com.example.Api_my_portafolio.Repositories;

import com.example.Api_my_portafolio.Models.Habilidad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Habilidad_repository extends JpaRepository<Habilidad,Integer>{
    @Query(value = "select *from habilidad where tipo = :tipo", nativeQuery = true)
    List<Habilidad> Get_habilidades_tipo(@Param("tipo") String tipo);
    @Query(value = "select *from habilidad where estado = :estado", nativeQuery = true)
    List<Habilidad> Get_habilidades_estado(@Param("estado") String estado);
    @Query(value = "select *from habilidad where tipo = :tipo and estado = :estado", nativeQuery = true)
    List<Habilidad> Get_habilidades_estado_tipo(@Param("tipo") String tipo, @Param("estado") String estado);
}