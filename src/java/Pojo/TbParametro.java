package Pojo;
// Generated 04-ago-2016 23:19:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TbParametro generated by hbm2java
 */
public class TbParametro  implements java.io.Serializable {


     private Integer id;
     private String categoria;
     private String detalle;
     private Set tbParametrodetalles = new HashSet(0);

    public TbParametro() {
    }

    public TbParametro(String categoria, String detalle, Set tbParametrodetalles) {
       this.categoria = categoria;
       this.detalle = detalle;
       this.tbParametrodetalles = tbParametrodetalles;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    public Set getTbParametrodetalles() {
        return this.tbParametrodetalles;
    }
    
    public void setTbParametrodetalles(Set tbParametrodetalles) {
        this.tbParametrodetalles = tbParametrodetalles;
    }




}


