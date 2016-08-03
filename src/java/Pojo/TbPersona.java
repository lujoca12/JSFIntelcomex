package Pojo;
// Generated 02-ago-2016 20:53:09 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TbPersona generated by hbm2java
 */
public class TbPersona  implements java.io.Serializable {


     private String cedula;
     private TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipoempresa;
     private TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipopersona;
     private TbParroquia tbParroquia;
     private String nombres;
     private String apellidos;
     private String telefono;
     private String email;
     private String direccion;
     private String nacionalidad;
     private Set tbPedidoses = new HashSet(0);
     private Set tbFacturas = new HashSet(0);
     private Set tbProductos = new HashSet(0);

    public TbPersona() {
    }

	
    public TbPersona(String cedula, TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipoempresa, TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipopersona, TbParroquia tbParroquia) {
        this.cedula = cedula;
        this.tbParametrodetalleByTbParametrodetalleTipoempresa = tbParametrodetalleByTbParametrodetalleTipoempresa;
        this.tbParametrodetalleByTbParametrodetalleTipopersona = tbParametrodetalleByTbParametrodetalleTipopersona;
        this.tbParroquia = tbParroquia;
    }
    public TbPersona(String cedula, TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipoempresa, TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipopersona, TbParroquia tbParroquia, String nombres, String apellidos, String telefono, String email, String direccion, String nacionalidad, Set tbPedidoses, Set tbFacturas, Set tbProductos) {
       this.cedula = cedula;
       this.tbParametrodetalleByTbParametrodetalleTipoempresa = tbParametrodetalleByTbParametrodetalleTipoempresa;
       this.tbParametrodetalleByTbParametrodetalleTipopersona = tbParametrodetalleByTbParametrodetalleTipopersona;
       this.tbParroquia = tbParroquia;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.telefono = telefono;
       this.email = email;
       this.direccion = direccion;
       this.nacionalidad = nacionalidad;
       this.tbPedidoses = tbPedidoses;
       this.tbFacturas = tbFacturas;
       this.tbProductos = tbProductos;
    }
   
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public TbParametrodetalle getTbParametrodetalleByTbParametrodetalleTipoempresa() {
        return this.tbParametrodetalleByTbParametrodetalleTipoempresa;
    }
    
    public void setTbParametrodetalleByTbParametrodetalleTipoempresa(TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipoempresa) {
        this.tbParametrodetalleByTbParametrodetalleTipoempresa = tbParametrodetalleByTbParametrodetalleTipoempresa;
    }
    public TbParametrodetalle getTbParametrodetalleByTbParametrodetalleTipopersona() {
        return this.tbParametrodetalleByTbParametrodetalleTipopersona;
    }
    
    public void setTbParametrodetalleByTbParametrodetalleTipopersona(TbParametrodetalle tbParametrodetalleByTbParametrodetalleTipopersona) {
        this.tbParametrodetalleByTbParametrodetalleTipopersona = tbParametrodetalleByTbParametrodetalleTipopersona;
    }
    public TbParroquia getTbParroquia() {
        return this.tbParroquia;
    }
    
    public void setTbParroquia(TbParroquia tbParroquia) {
        this.tbParroquia = tbParroquia;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNacionalidad() {
        return this.nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public Set getTbPedidoses() {
        return this.tbPedidoses;
    }
    
    public void setTbPedidoses(Set tbPedidoses) {
        this.tbPedidoses = tbPedidoses;
    }
    public Set getTbFacturas() {
        return this.tbFacturas;
    }
    
    public void setTbFacturas(Set tbFacturas) {
        this.tbFacturas = tbFacturas;
    }
    public Set getTbProductos() {
        return this.tbProductos;
    }
    
    public void setTbProductos(Set tbProductos) {
        this.tbProductos = tbProductos;
    }




}


