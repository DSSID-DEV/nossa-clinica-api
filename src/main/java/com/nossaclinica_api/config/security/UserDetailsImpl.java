package com.nossaclinica_api.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nossaclinica_api.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long idUser;
    private String nome;
    private String username;
    private String avatar;
    @JsonIgnore
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails build(User user) {
        List<GrantedAuthority> autorizacoes = Arrays.asList(user.getPermissao())
                .stream().map(permissao -> new SimpleGrantedAuthority(permissao))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getIdUser(),
                user.getNome(),
                user.getUsername(),
                user.getAvatar(),
                user.getSenha(),
                autorizacoes
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
