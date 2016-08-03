package Pojo;
// Generated 02-ago-2016 20:53:09 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TbParametrodetalle generated by hbm2java
 */
public class TbParametrodetalle  implements java.io.Serializable {


     private Integer id;
     private TbParametro tbParametro;
     private String parametro;
     private String detalle;
     private Set tbPedidosesForTbParametrodetalleTipopedido = new HashSet(0);
     private Set tbPedidosesForTbParametrodetalleTipopago = new HashSet(0);
     private Set tbPersonasForTbParametrodetalleTipoempresa = new HashSet(0);
     private Set tbEmpresas = new HashSet(0);
     private Set tbPersonasForTbParametrodetalleTipopersona = new HashSet(0);
     private Set tbProductosForTbParametrodetalleAlmacenes = new HashSet(0);
     private Set tbProductosForTbParametrodetalleClasificacion = new HashSet(0);
     private Set tbProductosForTbParametrodetalleTasaiva = new HashSet(0);
     private Set tbFacturas = new HashSet(0);
     private Set tbProductosForTbParametrodetalleUnidadmedida = new HashSet(0);
     private Set tbUsuarioses = new HashSet(0);

    public TbParametrodetalle() {
    }

	
    public TbParametrodetalle(TbParametro tbParametro, String parametro) {
        this.tbParametro = tbParametro;
        this.parametro = parametro;
    }
    public TbParametrodetalle(TbParametro tbParametro, String parametro, String detalle, Set tbPedidosesForTbParametrodetalleTipopedido, Set tbPedidosesForTbParametrodetalleTipopago, Set tbPersonasForTbParametrodetalleTipoempresa, Set tbEmpresas, Set tbPersonasForTbParametrodetalleTipopersona, Set tbProductosForTbParametrodetalleAlmacenes, Set tbProductosForTbParametrodetalleClasificacion, Set tbProductosForTbParametrodetalleTasaiva, Set tbFacturas, Set tbProductosForTbParametrodetalleUnidadmedida, Set tbUsuarioses) {
       this.tbParametro = tbParametro;
       this.parametro = parametro;
       this.detalle = detalle;
       this.tbPedidosesForTbParametrodetalleTipopedido = tbPedidosesForTbParametrodetalleTipopedido;
       this.tbPedidosesForTbParametrodetalleTipopago = tbPedidosesForTbParametrodetalleTipopago;
       this.tbPersonasForTbParametrodetalleTipoempresa = tbPersonasForTbParametrodetalleTipoempresa;
       this.tbEmpresas = tbEmpresas;
       this.tbPersonasForTbParametrodetalleTipopersona = tbPersonasForTbParametrodetalleTipopersona;
       this.tbProductosForTbParametrodetalleAlmacenes = tbProductosForTbParametrodetalleAlmacenes;
       this.tbProductosForTbParametrodetalleClasificacion = tbProductosForTbParametrodetalleClasificacion;
       this.tbProductosForTbParametrodetalleTasaiva = tbProductosForTbParametrodetalleTasaiva;
       this.tbFacturas = tbFacturas;
       this.tbProductosForTbParametrodetalleUnidadmedida = tbProductosForTbParametrodetalleUnidadmedida;
       this.tbUsuarioses = tbUsuarioses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public TbParametro getTbParametro() {
        return this.tbParametro;
    }
    
    public void setTbParametro(TbParametro tbParametro) {
        this.tbParametro = tbParametro;
    }
    public String getParametro() {
        return this.parametro;
    }
    
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    public String getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    public Set getTbPedidosesForTbParametrodetalleTipopedido() {
        return this.tbPedidosesForTbParametrodetalleTipopedido;
    }
    
    public void setTbPedidosesForTbParametrodetalleTipopedido(Set tbPedidosesForTbParametrodetalleTipopedido) {
        this.tbPedidosesForTbParametrodetalleTipopedido = tbPedidosesForTbParametrodetalleTipopedido;
    }
    public Set getTbPedidosesForTbParametrodetalleTipopago() {
        return this.tbPedidosesForTbParametrodetalleTipopago;
    }
    
    public void setTbPedidosesForTbParametrodetalleTipopago(Set tbPedidosesForTbParametrodetalleTipopago) {
        this.tbPedidosesForTbParametrodetalleTipopago = tbPedidosesForTbParametrodetalleTipopago;
    }
    public Set getTbPersonasForTbParametrodetalleTipoempresa() {
        return this.tbPersonasForTbParametrodetalleTipoempresa;
    }
    
    public void setTbPersonasForTbParametrodetalleTipoempresa(Set tbPersonasForTbParametrodetalleTipoempresa) {
        this.tbPersonasForTbParametrodetalleTipoempresa = tbPersonasForTbParametrodetalleTipoempresa;
    }
    public Set getTbEmpresas() {
        return this.tbEmpresas;
    }
    
    public void setTbEmpresas(Set tbEmpresas) {
        this.tbEmpresas = tbEmpresas;
    }
    public Set getTbPersonasForTbParametrodetalleTipopersona() {
        return this.tbPersonasForTbParametrodetalleTipopersona;
    }
    
    public void setTbPersonasForTbParametrodetalleTipopersona(Set tbPersonasForTbParametrodetalleTipopersona) {
        this.tbPersonasForTbParametrodetalleTipopersona = tbPersonasForTbParametrodetalleTipopersona;
    }
    public Set getTbProductosForTbParametrodetalleAlmacenes() {
        return this.tbProductosForTbParametrodetalleAlmacenes;
    }
    
    public void setTbProductosForTbParametrodetalleAlmacenes(Set tbProductosForTbParametrodetalleAlmacenes) {
        this.tbProductosForTbParametrodetalleAlmacenes = tbProductosForTbParametrodetalleAlmacenes;
    }
    public Set getTbProductosForTbParametrodetalleClasificacion() {
        return this.tbProductosForTbParametrodetalleClasificacion;
    }
    
    public void setTbProductosForTbParametrodetalleClasificacion(Set tbProductosForTbParametrodetalleClasificacion) {
        this.tbProductosForTbParametrodetalleClasificacion = tbProductosForTbParametrodetalleClasificacion;
    }
    public Set getTbProductosForTbParametrodetalleTasaiva() {
        return this.tbProductosForTbParametrodetalleTasaiva;
    }
    
    public void setTbProductosForTbParametrodetalleTasaiva(Set tbProductosForTbParametrodetalleTasaiva) {
        this.tbProductosForTbParametrodetalleTasaiva = tbProductosForTbParametrodetalleTasaiva;
    }
    public Set getTbFacturas() {
        return this.tbFacturas;
    }
    
    public void setTbFacturas(Set tbFacturas) {
        this.tbFacturas = tbFacturas;
    }
    public Set getTbProductosForTbParametrodetalleUnidadmedida() {
        return this.tbProductosForTbParametrodetalleUnidadmedida;
    }
    
    public void setTbProductosForTbParametrodetalleUnidadmedida(Set tbProductosForTbParametrodetalleUnidadmedida) {
        this.tbProductosForTbParametrodetalleUnidadmedida = tbProductosForTbParametrodetalleUnidadmedida;
    }
    public Set getTbUsuarioses() {
        return this.tbUsuarioses;
    }
    
    public void setTbUsuarioses(Set tbUsuarioses) {
        this.tbUsuarioses = tbUsuarioses;
    }




}


