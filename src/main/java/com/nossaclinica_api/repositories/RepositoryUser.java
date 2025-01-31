package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.repositories.queries.UserQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User, Long> {
    @Query(value = UserQueries.BUSCAR_USUARIO_POR_EMAIL_OR_TELEFONE, nativeQuery = false)
    Optional<User> buscarUsuarioPorUsernameOrEmailOrTelefone(@Param("dadosDeEntrada") String dadosDeEntrada);

    @Query(value = UserQueries.BUSCAR_USUARIO_POR_EMAIL_OR_TELEFONE, nativeQuery = false)
    Optional<User> buscarUsuario(@Param("dadosDeEntrada") String dadosDeEntrada);

    @Query(value = UserQueries.EXISTS_USERNAME, nativeQuery = false)
    boolean existsUsername(@Param("username") String username);

    @Query(value = UserQueries.EXISTS_USER_WITH_THIS_LOGIN, nativeQuery = false)
    boolean existsUser(@Param("login") String login);

    @Query(value = UserQueries.EXISTS_USER, nativeQuery = false)
    boolean existsUser(@Param("username") String username, @Param("email") String email, @Param("telefone") String telefone);


    @Query(value = UserQueries.BUSCA_ALEATORIA, nativeQuery = false)
    List<User> buscarAleatoria(@Param("idUser") Long idUser);

    @Query(value = UserQueries.BUSCAR_USUARIO_MAIS_ALEATORIOS, nativeQuery = true)
    List<User> buscarAleatoriaComLogin(@Param("login")String login);

    @Query(value = UserQueries.BUSCAR_USUARIO_POR_USERNAME, nativeQuery = false)
    User buscarUsuarioPorUsername(@Param("username") String username);

    @Query(value = UserQueries.BUSCAR_USUARIO_AUTORIZADOR, nativeQuery = false)
    User buscarAutorizador();
}
