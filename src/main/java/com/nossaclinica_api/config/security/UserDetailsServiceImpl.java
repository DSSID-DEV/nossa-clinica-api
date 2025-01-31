package com.nossaclinica_api.config.security;

import com.nossaclinica_api.repositories.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepositoryUser repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.buscarUsuarioPorUsernameOrEmailOrTelefone(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com esse login!"));
        return UserDetailsImpl.build(user);
    }

    public UserDetails loadUserByIdUser(String idUser) {
        var user = repository.findById(Long.parseLong(idUser))
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Usuário não encontrado com esse userId: " +  idUser));
        return UserDetailsImpl.build(user);
    }

}
