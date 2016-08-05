package Pojo;
// Generated 04-ago-2016 23:19:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TbProvincia generated by hbm2java
 */
public class TbProvincia  implements java.io.Serializable {


     private String id;
     private TbPais tbPais;
     private String nombre;
     private Set tbCantons = new HashSet(0);

    public TbProvincia() {
    }

	
    public TbProvincia(String id, TbPais tbPais) {
        this.id = id;
        this.tbPais = tbPais;
    }
    public TbProvincia(String id, TbPais tbPais, String nombre, Set tbCantons) {
       this.id = id;
       this.tbPais = tbPais;
       this.nombre = nombre;
       this.tbCantons = tbCantons;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public TbPais getTbPais() {
        return this.tbPais;
    }
    
    public void setTbPais(TbPais tbPais) {
        this.tbPais = tbPais;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getTbCantons() {
        return this.tbCantons;
    }
    
    public void setTbCantons(Set tbCantons) {
        this.tbCantons = tbCantons;
    }




}


