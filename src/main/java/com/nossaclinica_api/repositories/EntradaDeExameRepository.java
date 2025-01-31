package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.EntradaExame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaDeExameRepository extends JpaRepository<EntradaExame, Long> {
}
