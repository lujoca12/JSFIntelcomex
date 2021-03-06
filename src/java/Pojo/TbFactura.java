package Pojo;
// Generated 17-sep-2016 17:20:14 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbFactura generated by hbm2java
 */
public class TbFactura  implements java.io.Serializable {


     private Integer id;
     private TbPersona tbPersona;
     private TbTipopago tbTipopago;
     private TbUsuarios tbUsuarios;
     private Date fecha;
     private Set tbDetallefacturas = new HashSet(0);

    public TbFactura() {
    }

	
    public TbFactura(TbPersona tbPersona, TbTipopago tbTipopago, TbUsuarios tbUsuarios) {
        this.tbPersona = tbPersona;
        this.tbTipopago = tbTipopago;
        this.tbUsuarios = tbUsuarios;
    }
    public TbFactura(TbPersona tbPersona, TbTipopago tbTipopago, TbUsuarios tbUsuarios, Date fecha, Set tbDetallefacturas) {
       this.tbPersona = tbPersona;
       this.tbTipopago = tbTipopago;
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
    public TbPersona getTbPersona() {
        return this.tbPersona;
    }
    
    public void setTbPersona(TbPersona tbPersona) {
        this.tbPersona = tbPersona;
    }
    public TbTipopago getTbTipopago() {
        return this.tbTipopago;
    }
    
    public void setTbTipopago(TbTipopago tbTipopago) {
        this.tbTipopago = tbTipopago;
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


