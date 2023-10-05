package com.comercio.config;


import com.comercio.services.UserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class config {

    @Autowired
    UserDetailServices userDetailServices;


    @Bean

    public AuthenticationProvider aut(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailServices);
        provider.setPasswordEncoder(getEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


       return http
                .authorizeRequests()
                .requestMatchers("/administrador/**").hasAnyRole("ADMIN")
                .requestMatchers("/productos/**").hasAnyRole("ADMIN")
                .and()
                .formLogin(form-> form.loginPage("/usuario/login")
                        .permitAll().defaultSuccessUrl("/usuario/Acceder")).build();

}







//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/administrador/**").hasAnyRole("ADMIN")
//                .requestMatchers("/productos/**").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin(form ->form.loginPage("/usuario/login").permitAll());
//
//
//        return http.build();
//
//    }







    @Bean
    public PasswordEncoder getEncoder() {

        return new BCryptPasswordEncoder();



    }


}



