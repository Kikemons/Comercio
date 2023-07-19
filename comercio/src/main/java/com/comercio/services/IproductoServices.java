package com.comercio.services;

import com.comercio.model.Producto;


import java.util.Optional;

public interface IproductoServices {

    public Producto guardar(Producto producto);
    public Optional<Producto> obtener(Integer id);

    public void actualizar(Producto producto);
    public void borrar(Integer id);
}
