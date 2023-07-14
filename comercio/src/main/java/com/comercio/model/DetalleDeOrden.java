package com.comercio.model;

public class DetalleDeOrden {

    private Integer id;
    private String nombre;

    private double cantiidad;
    private double precio;
    private double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantiidad() {
        return cantiidad;
    }

    public void setCantiidad(double cantiidad) {
        this.cantiidad = cantiidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
