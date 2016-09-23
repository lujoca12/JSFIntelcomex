/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author server
 */
public class ClsProducto {
    private String id;    
    private String displayName; 
    private Double pvp;
    private Double costo;
    private Double iva;
    private String nombre;
    private int cantidad;
    private int idInventario;
    private int cantMinima;
    private BigDecimal precioStock;
    private Date fecharegistro;
    public ClsProducto() {}

    public ClsProducto(String id, String displayName, Double pvp, Double costo, Double iva, String nombre, int cantidad, int idInventario, int cantMinima,BigDecimal precioStock,Date fecharegistro) {
        this.id = id;
        this.displayName = displayName;
        this.pvp = pvp;
        this.costo = costo;
        this.iva = iva;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.idInventario = idInventario;
        this.cantMinima = cantMinima;
        this.precioStock = precioStock;
        this.fecharegistro = fecharegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getCantMinima() {
        return cantMinima;
    }

    public void setCantMinima(int cantMinima) {
        this.cantMinima = cantMinima;
    }

    public BigDecimal getPrecioStock() {
        return precioStock;
    }

    public void setPrecioStock(BigDecimal precioStock) {
        this.precioStock = precioStock;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
    
}
