/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author server
 */
public class ClsProducto {
    private String id;    
    private String displayName; 
    private Double pvp;
    private Double costo;
    private Double otroprecio;
     
    public ClsProducto() {}

    public ClsProducto(String id, String displayName, Double pvp, Double costo, Double otroprecio) {
        this.id = id;
        this.displayName = displayName;
        this.pvp = pvp;
        this.costo = costo;
        this.otroprecio = otroprecio;
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

    public Double getOtroprecio() {
        return otroprecio;
    }

    public void setOtroprecio(Double otroprecio) {
        this.otroprecio = otroprecio;
    }
 
    
}
