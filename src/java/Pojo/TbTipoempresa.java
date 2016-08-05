package Pojo;
// Generated 04-ago-2016 23:19:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TbTipoempresa generated by hbm2java
 */
public class TbTipoempresa  implements java.io.Serializable {


     private int idTipoEmpresa;
     private String nombre;
     private String descripcion;
     private Set tbEmpresas = new HashSet(0);

    public TbTipoempresa() {
    }

	
    public TbTipoempresa(int idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }
    public TbTipoempresa(int idTipoEmpresa, String nombre, String descripcion, Set tbEmpresas) {
       this.idTipoEmpresa = idTipoEmpresa;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.tbEmpresas = tbEmpresas;
    }
   
    public int getIdTipoEmpresa() {
        return this.idTipoEmpresa;
    }
    
    public void setIdTipoEmpresa(int idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
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
    public Set getTbEmpresas() {
        return this.tbEmpresas;
    }
    
    public void setTbEmpresas(Set tbEmpresas) {
        this.tbEmpresas = tbEmpresas;
    }




}

