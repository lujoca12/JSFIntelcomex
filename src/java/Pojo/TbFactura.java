package Pojo;
// Generated 02-ago-2016 20:53:09 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbFactura generated by hbm2java
 */
public class TbFactura  implements java.io.Serializable {


     private Integer id;
     private TbParametrodetalle tbParametrodetalle;
     private TbPersona tbPersona;
     private TbUsuarios tbUsuarios;
     private Date fecha;
     private Set tbDetallefacturas = new HashSet(0);

    public TbFactura() {
    }

	
    public TbFactura(TbParametrodetalle tbParametrodetalle, TbPersona tbPersona, TbUsuarios tbUsuarios) {
        this.tbParametrodetalle = tbParametrodetalle;
        this.tbPersona = tbPersona;
        this.tbUsuarios = tbUsuarios;
    }
    public TbFactura(TbParametrodetalle tbParametrodetalle, TbPersona tbPersona, TbUsuarios tbUsuarios, Date fecha, Set tbDetallefacturas) {
       this.tbParametrodetalle = tbParametrodetalle;
       this.tbPersona = tbPersona;
       this.tbUsuarios = tbUsuarios;
       this.fecha = fecha;
       this.tbDetallefacturas = tbDetallefacturas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public TbParametrodetalle getTbParametrodetalle() {
        return this.tbParametrodetalle;
    }
    
    public void setTbParametrodetalle(TbParametrodetalle tbParametrodetalle) {
        this.tbParametrodetalle = tbParametrodetalle;
    }
    public TbPersona getTbPersona() {
        return this.tbPersona;
    }
    
    public void setTbPersona(TbPersona tbPersona) {
        this.tbPersona = tbPersona;
    }
    public TbUsuarios getTbUsuarios() {
        return this.tbUsuarios;
    }
    
    public void setTbUsuarios(TbUsuarios tbUsuarios) {
        this.tbUsuarios = tbUsuarios;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Set getTbDetallefacturas() {
        return this.tbDetallefacturas;
    }
    
    public void setTbDetallefacturas(Set tbDetallefacturas) {
        this.tbDetallefacturas = tbDetallefacturas;
    }




}


