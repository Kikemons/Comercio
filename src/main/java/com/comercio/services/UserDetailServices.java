package com.comercio.services;

import com.comercio.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServices implements UserDetailsService {

    @Autowired
    private UsuarioServices usuarioServices;


    @Autowired
    private PasswordEncoder passwordEncoder;


    private Logger log= LoggerFactory.getLogger(UserDetailServices.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario=usuarioServices.findByEmail(username);
        log.info("este es el username: ",optionalUsuario.get().getUsername());
        if (optionalUsuario.isPresent()){
        Usuario user=optionalUsuario.get();
        log.info("el usuario es ", user);
        return User.withUsername(user.getEmail()).password(passwordEncoder.encode(user.getPassword())).roles(user.getTipo()).build();
        }else{
            throw new UsernameNotFoundException("User no encontrado");
        }
    }
}
