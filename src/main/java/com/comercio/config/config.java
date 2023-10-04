package com.comercio.config;


import com.comercio.services.UserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


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
    public PasswordEncoder getEncoder() {

        return new BCryptPasswordEncoder();



    }


}



//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailServices).passwordEncoder(getEncoder());
//    }


//@Bean
//public UserDetailsService userDetailsService(){
//    UserDetails user= User.withUsername("kike")
//            .password(getEncoder().encode("123"))
//            .roles("USER")
//            .build();
//
//    return new InMemoryUserDetailsManager(user);
//}
