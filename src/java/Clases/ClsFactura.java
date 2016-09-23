/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author server
 */
public class ClsFactura implements Serializable {
    private int numero;
    private String idProducto;
    private String nombreProducto;
    private Double cantidad;
    private Double TotalxProducto;
    private Double costo;
    private Double otroPrecio;
    private Double pvp;
    private Double iva;
    private int stock;
    private int idInventario;
    private int cantMinima;
    private BigDecimal precioStock;
    private Date fecharegistro;
    private int cantidadAnterior;
    private String idProveedor;
    private String proveedor;

    public ClsFactura() {
    }

    public ClsFactura(int numero,String idProducto, String nombreProducto, Double cantidad, Double TotalxProducto, Double costo, Double otroPrecio, Double pvp, Double iva, int stock, int idInventario, int cantMinima,BigDecimal precioStock,Date fecharegistro,int cantidadAnterior,String idProveedor,String proveedor) {
        this.numero = numero;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.TotalxProducto = TotalxProducto;
        this.costo = costo;
        this.otroPrecio = otroPrecio;
        this.pvp = pvp;
        this.iva = iva;
        this.stock = stock;
        this.idInventario = idInventario;
        this.cantMinima = cantMinima;
        this.precioStock = precioStock;
        this.fecharegistro = fecharegistro;
        this.cantidadAnterior = cantidadAnterior;
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotalxProducto() {
        return TotalxProducto;
    }

    public void setTotalxProducto(Double TotalxProducto) {
        this.TotalxProducto = TotalxProducto;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getOtroPrecio() {
        return otroPrecio;
    }

    public void setOtroPrecio(Double otroPrecio) {
        this.otroPrecio = otroPrecio;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public int getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(int cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
}
