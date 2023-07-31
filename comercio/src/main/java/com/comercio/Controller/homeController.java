package com.comercio.Controller;

import com.comercio.model.DetalleDeOrden;
import com.comercio.model.Orden;
import com.comercio.model.Producto;
import com.comercio.services.ProductoServices;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class homeController {
    private final Logger log= LoggerFactory.getLogger(homeController.class);

    //almacenar la orden
    List<DetalleDeOrden> detalleOrden= new ArrayList<DetalleDeOrden>();

    //datosd e la orden
    Orden orden= new Orden();

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


    @PostMapping("/carrito")
    public String carrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
        DetalleDeOrden detalle= new DetalleDeOrden();
        Producto producto= new Producto();
        double sumaTotal=0;
        Optional<Producto> detalleCar=productoService.obtener(id);
        log.info("el producto es: {}", productoService.obtener(id));
        log.info("cantidad: {}", cantidad);
        producto=detalleCar.get();

        detalle.setCantidad(cantidad);
        detalle.setNombre(producto.getNombre());
        detalle.setPrecio(producto.getPrecio());
        detalle.setTotal(producto.getPrecio()*cantidad);
        detalle.setProducto(producto);

        //validar que el carrito no se agregue mas de una vez
        Integer productoId=producto.getId();
        boolean ingresado=detalleOrden.stream().anyMatch(p ->p.getProducto().getId()==productoId);
        if (!ingresado){
        detalleOrden.add(detalle);
        }


        sumaTotal = detalleOrden.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);

        model.addAttribute("carrito", detalleOrden);
        model.addAttribute("orden",orden);
        return "Usuario/carrito";
    }


    // eliminar producto de carrito

    @GetMapping("delete/carrito/{id}")
    public String borrarIdCarrito(@PathVariable Integer id, Model model){
        //lista nueva de producrtos
        List<DetalleDeOrden> nuevaDetalleOrden= new ArrayList<DetalleDeOrden>();
        for (DetalleDeOrden detalleDeOrden: detalleOrden){
            if (detalleDeOrden.getProducto().getId()!=(id)){
            nuevaDetalleOrden.add(detalleDeOrden);
            }
        }
        //poner la lista de orden actualizada
        detalleOrden=nuevaDetalleOrden;

        double sumaTotal=0;
        sumaTotal = detalleOrden.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);

        model.addAttribute("carrito", detalleOrden);
        model.addAttribute("orden",orden);

        return "Usuario/carrito";
    }

    @GetMapping("/car")
    public String car(Model model){
        model.addAttribute("carrito", detalleOrden);
        model.addAttribute("orden",orden);
        return "Usuario/carrito";
    }



}
