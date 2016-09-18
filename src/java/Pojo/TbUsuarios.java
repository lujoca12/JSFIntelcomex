package Pojo;
// Generated 17-sep-2016 17:20:14 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbUsuarios generated by hbm2java
 */
public class TbUsuarios  implements java.io.Serializable {


     private String cedula;
     private TbEmpresa tbEmpresa;
     private TbParroquia tbParroquia;
     private TbTipousuario tbTipousuario;
     private String nombres;
     private String apellidos;
     private String sexo;
     private String telefono;
     private String estadoCivil;
     private String direccion;
     private Date fechanacimiento;
     private String email;
     private String login;
     private String pass;
     private Character estado;
     private Set tbDetallePermisos = new HashSet(0);
     private Set tbAccesos = new HashSet(0);
     private Set tbFacturas = new HashSet(0);
     private Set tbPedidoses = new HashSet(0);

    public TbUsuarios() {
    }

	
    public TbUsuarios(String cedula, TbEmpresa tbEmpresa, TbParroquia tbParroquia, TbTipousuario tbTipousuario) {
        this.cedula = cedula;
        this.tbEmpresa = tbEmpresa;
        this.tbParroquia = tbParroquia;
        this.tbTipousuario = tbTipousuario;
    }
    public TbUsuarios(String cedula, TbEmpresa tbEmpresa, TbParroquia tbParroquia, TbTipousuario tbTipousuario, String nombres, String apellidos, String sexo, String telefono, String estadoCivil, String direccion, Date fechanacimiento, String email, String login, String pass,Character estado, Set tbDetallePermisos, Set tbAccesos, Set tbFacturas, Set tbPedidoses) {
       this.cedula = cedula;
       this.tbEmpresa = tbEmpresa;
       this.tbParroquia = tbParroquia;
       this.tbTipousuario = tbTipousuario;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.sexo = sexo;
       this.telefono = telefono;
       this.estadoCivil = estadoCivil;
       this.direccion = direccion;
       this.fechanacimiento = fechanacimiento;
       this.email = email;
       this.tbDetallePermisos = tbDetallePermisos;
       this.tbAccesos = tbAccesos;
       this.tbFacturas = tbFacturas;
       this.tbPedidoses = tbPedidoses;
       this.estado = estado;
       this.login = login;
       this.pass = pass;
    }
   
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public TbEmpresa getTbEmpresa() {
        return this.tbEmpresa;
    }
    
    public void setTbEmpresa(TbEmpresa tbEmpresa) {
        this.tbEmpresa = tbEmpresa;
    }
    public TbParroquia getTbParroquia() {
        return this.tbParroquia;
    }
    
    public void setTbParroquia(TbParroquia tbParroquia) {
        this.tbParroquia = tbParroquia;
    }
    public TbTipousuario getTbTipousuario() {
        return this.tbTipousuario;
    }
    
    public void setTbTipousuario(TbTipousuario tbTipousuario) {
        this.tbTipousuario = tbTipousuario;
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
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEstadoCivil() {
        return this.estadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Date getFechanacimiento() {
        return this.fechanacimiento;
    }
    
    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Set getTbDetallePermisos() {
        return this.tbDetallePermisos;
    }
    
    public void setTbDetallePermisos(Set tbDetallePermisos) {
        this.tbDetallePermisos = tbDetallePermisos;
    }
    public Set getTbAccesos() {
        return this.tbAccesos;
    }
    
    public void setTbAccesos(Set tbAccesos) {
        this.tbAccesos = tbAccesos;
    }
    public Set getTbFacturas() {
        return this.tbFacturas;
    }
    
    public void setTbFacturas(Set tbFacturas) {
        this.tbFacturas = tbFacturas;
    }
    public Set getTbPedidoses() {
        return this.tbPedidoses;
    }
    
    public void setTbPedidoses(Set tbPedidoses) {
        this.tbPedidoses = tbPedidoses;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
    



}


