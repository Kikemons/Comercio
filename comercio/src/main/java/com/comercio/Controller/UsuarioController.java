package com.comercio.Controller;

import com.comercio.model.Usuario;
import com.comercio.services.UsuarioServices;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioServices usuarioServices;


    //se crea el mapping del aparatdo registro
    @GetMapping("/registro")
    public String crear(){
    return "Usuario/registro";
    }

    @PostMapping("/crear")
    public String subirUsusario(Usuario usuario, Model model){
        logger.info("usuario registro es: {}",usuario);
        usuario.setTipo("USER");
        usuarioServices.crear(usuario);
        return"redirect:/";
    }

    @GetMapping ("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/Acceder")
    public String acceder(Usuario usuario, HttpSession session){
        Optional<Usuario> user=usuarioServices.findByEmail(usuario.getEmail());
        logger.info("usuario de bd:: {}", user.get());

        if(user.isPresent()){
          session.setAttribute("idUsuario", user.get().getId());
           if(user.get().getTipo().equals("ADMIN")){
            return "redirect:/administrador";
           }else {
               return"redirect:/";
           }
        }else{
            logger.info("usuario no registrado");
        }
        return"redirect:/";
    }

}
