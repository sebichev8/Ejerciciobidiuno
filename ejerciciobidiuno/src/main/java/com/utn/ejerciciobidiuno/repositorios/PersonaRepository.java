package com.utn.ejerciciobidiuno.repositorios;

import com.utn.ejerciciobidiuno.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
