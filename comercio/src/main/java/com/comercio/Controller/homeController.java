package com.comercio.Controller;

import com.comercio.model.Producto;
import com.comercio.services.ProductoServices;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class homeController {
    private final Logger log= LoggerFactory.getLogger(homeController.class);

    @Autowired
    private ProductoServices productoService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("productos", productoService.mostrarProductos());
        return "Usuario/home";
    }

    @GetMapping("productohome/{id}")
    public  String productoHome(@PathVariable Integer id, Model model){
        log.info("el producto a ver es: {}",id);
        Producto producto= new Producto();
        Optional<Producto> ListPro=productoService.obtener(id);
        producto=ListPro.get();
        model.addAttribute("producto", producto);
        return "Usuario/productohome";
    }


}
