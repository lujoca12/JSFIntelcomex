/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;

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

    public ClsFactura() {
    }

    public ClsFactura(int numero,String idProducto, String nombreProducto, Double cantidad, Double TotalxProducto, Double costo, Double otroPrecio, Double pvp, Double iva) {
        this.numero = numero;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.TotalxProducto = TotalxProducto;
        this.costo = costo;
        this.otroPrecio = otroPrecio;
        this.pvp = pvp;
        this.iva = iva;
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
}
