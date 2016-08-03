package Pojo;
// Generated 02-ago-2016 20:53:09 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbProducto generated by hbm2java
 */
public class TbProducto  implements java.io.Serializable {


     private String id;
     private TbParametrodetalle tbParametrodetalleByTbParametrodetalleAlmacenes;
     private TbParametrodetalle tbParametrodetalleByTbParametrodetalleClasificacion;
     private TbParametrodetalle tbParametrodetalleByTbParametrodetalleTasaiva;
     private TbParametrodetalle tbParametrodetalleByTbParametrodetalleUnidadmedida;
     private TbPersona tbPersona;
     private String nombre;
     private BigDecimal precio1;
     private BigDecimal precio2;
     private String marca;
     private Date fechaIngreso;
     private String imagendir;
     private Set tbDetallepedidoses = new HashSet(0);
     private Set tbInventarios = new HashSet(0);
     private Set tbDetallefacturas = new HashSet(0);

    public TbProducto() {
    }

	
    public TbProducto(String id, TbParametrodetalle tbParametrodetalleByTbParametrodetalleAlmacenes, TbParametrodetalle tbParametrodetalleByTbParametrodetalleClasificacion, TbParametrodetalle tbParametrodetalleByTbParametrodetalleTasaiva, TbParametrodetalle tbParametrodetalleByTbParametrodetalleUnidadmedida, TbPersona tbPersona) {
        this.id = id;
        this.tbParametrodetalleByTbParametrodetalleAlmacenes = tbParametrodetalleByTbParametrodetalleAlmacenes;
        this.tbParametrodetalleByTbParametrodetalleClasificacion = tbParametrodetalleByTbParametrodetalleClasificacion;
        this.tbParametrodetalleByTbParametrodetalleTasaiva = tbParametrodetalleByTbParametrodetalleTasaiva;
        this.tbParametrodetalleByTbParametrodetalleUnidadmedida = tbParametrodetalleByTbParametrodetalleUnidadmedida;
        this.tbPersona = tbPersona;
    }
    public TbProducto(String id, TbParametrodetalle tbParametrodetalleByTbParametrodetalleAlmacenes, TbParametrodetalle tbParametrodetalleByTbParametrodetalleClasificacion, TbParametrodetalle tbParametrodetalleByTbParametrodetalleTasaiva, TbParametrodetalle tbParametrodetalleByTbParametrodetalleUnidadmedida, TbPersona tbPersona, String nombre, BigDecimal precio1, BigDecimal precio2, String marca, Date fechaIngreso, String imagendir, Set tbDetallepedidoses, Set tbInventarios, Set tbDetallefacturas) {
       this.id = id;
       this.tbParametrodetalleByTbParametrodetalleAlmacenes = tbParametrodetalleByTbParametrodetalleAlmacenes;
       this.tbParametrodetalleByTbParametrodetalleClasificacion = tbParametrodetalleByTbParametrodetalleClasificacion;
       this.tbParametrodetalleByTbParametrodetalleTasaiva = tbParametrodetalleByTbParametrodetalleTasaiva;
       this.tbParametrodetalleByTbParametrodetalleUnidadmedida = tbParametrodetalleByTbParametrodetalleUnidadmedida;
       this.tbPersona = tbPersona;
       this.nombre = nombre;
       this.precio1 = precio1;
       this.precio2 = precio2;
       this.marca = marca;
       this.fechaIngreso = fechaIngreso;
       this.imagendir = imagendir;
       this.tbDetallepedidoses = tbDetallepedidoses;
       this.tbInventarios = tbInventarios;
       this.tbDetallefacturas = tbDetallefacturas;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public TbParametrodetalle getTbParametrodetalleByTbParametrodetalleAlmacenes() {
        return this.tbParametrodetalleByTbParametrodetalleAlmacenes;
    }
    
    public void setTbParametrodetalleByTbParametrodetalleAlmacenes(TbParametrodetalle tbParametrodetalleByTbParametrodetalleAlmacenes) {
        this.tbParametrodetalleByTbParametrodetalleAlmacenes = tbParametrodetalleByTbParametrodetalleAlmacenes;
    }
    public TbParametrodetalle getTbParametrodetalleByTbParametrodetalleClasificacion() {
        return this.tbParametrodetalleByTbParametrodetalleClasificacion;
    }
    
    public void setTbParametrodetalleByTbParametrodetalleClasificacion(TbParametrodetalle tbParametrodetalleByTbParametrodetalleClasificacion) {
        this.tbParametrodetalleByTbParametrodetalleClasificacion = tbParametrodetalleByTbParametrodetalleClasificacion;
    }
    public TbParametrodetalle getTbParametrodetalleByTbParametrodetalleTasaiva() {
        return this.tbParametrodetalleByTbParametrodetalleTasaiva;
    }
    
    public void setTbParametrodetalleByTbParametrodetalleTasaiva(TbParametrodetalle tbParametrodetalleByTbParametrodetalleTasaiva) {
        this.tbParametrodetalleByTbParametrodetalleTasaiva = tbParametrodetalleByTbParametrodetalleTasaiva;
    }
    public TbParametrodetalle getTbParametrodetalleByTbParametrodetalleUnidadmedida() {
        return this.tbParametrodetalleByTbParametrodetalleUnidadmedida;
    }
    
    public void setTbParametrodetalleByTbParametrodetalleUnidadmedida(TbParametrodetalle tbParametrodetalleByTbParametrodetalleUnidadmedida) {
        this.tbParametrodetalleByTbParametrodetalleUnidadmedida = tbParametrodetalleByTbParametrodetalleUnidadmedida;
    }
    public TbPersona getTbPersona() {
        return this.tbPersona;
    }
    
    public void setTbPersona(TbPersona tbPersona) {
        this.tbPersona = tbPersona;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getPrecio1() {
        return this.precio1;
    }
    
    public void setPrecio1(BigDecimal precio1) {
        this.precio1 = precio1;
    }
    public BigDecimal getPrecio2() {
        return this.precio2;
    }
    
    public void setPrecio2(BigDecimal precio2) {
        this.precio2 = precio2;
    }
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getImagendir() {
        return this.imagendir;
    }
    
    public void setImagendir(String imagendir) {
        this.imagendir = imagendir;
    }
    public Set getTbDetallepedidoses() {
        return this.tbDetallepedidoses;
    }
    
    public void setTbDetallepedidoses(Set tbDetallepedidoses) {
        this.tbDetallepedidoses = tbDetallepedidoses;
    }
    public Set getTbInventarios() {
        return this.tbInventarios;
    }
    
    public void setTbInventarios(Set tbInventarios) {
        this.tbInventarios = tbInventarios;
    }
    public Set getTbDetallefacturas() {
        return this.tbDetallefacturas;
    }
    
    public void setTbDetallefacturas(Set tbDetallefacturas) {
        this.tbDetallefacturas = tbDetallefacturas;
    }




}


