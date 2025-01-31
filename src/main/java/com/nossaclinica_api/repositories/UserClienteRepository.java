package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.UsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClienteRepository extends JpaRepository<UsuarioCliente, Long> {
}
