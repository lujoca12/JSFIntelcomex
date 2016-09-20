package Pojo;
// Generated 17-sep-2016 17:20:14 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TbTipoalmacenes generated by hbm2java
 */
public class TbTipoalmacenes  implements java.io.Serializable {


     private String idTipoAlmacenes;
     private String nombre;
     private String descripcion;
     private Set tbProductos = new HashSet(0);

    public TbTipoalmacenes() {
    }

	
    public TbTipoalmacenes(String idTipoAlmacenes, String nombre, String descripcion) {
        this.idTipoAlmacenes = idTipoAlmacenes;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public TbTipoalmacenes(String idTipoAlmacenes, String nombre, String descripcion, Set tbProductos) {
       this.idTipoAlmacenes = idTipoAlmacenes;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.tbProductos = tbProductos;
    }
   
    public String getIdTipoAlmacenes() {
        return this.idTipoAlmacenes;
    }
    
    public void setIdTipoAlmacenes(String idTipoAlmacenes) {
        this.idTipoAlmacenes = idTipoAlmacenes;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getTbProductos() {
        return this.tbProductos;
    }
    
    public void setTbProductos(Set tbProductos) {
        this.tbProductos = tbProductos;
    }




}

