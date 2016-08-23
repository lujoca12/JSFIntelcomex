/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoProducto;
import Dao.DaoTParametro;
import Pojo.TbParametro;
import Pojo.TbParametrodetalle;
import Pojo.TbPersona;
import Pojo.TbProducto;
import Pojo.TbProvincia;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author server
 */
@Named(value = "mbVProducto")
@ViewScoped
public class MbVProducto implements Serializable{

    /**
     * Creates a new instance of MbVProducto
     */
    private UploadedFile file;
    private boolean msg;
    
    private TbProducto tbProducto;
    private TbPersona tbPersona;
    private TbParametrodetalle undMedida;
    private TbParametrodetalle iva;
    private TbParametrodetalle clasificacion;
    private TbParametrodetalle almacen;
    
    private int idUndMedida;
    private int idIva;
    private int idClasificacion;
    private int idAlmacen;
    
    public MbVProducto() {
        tbProducto = new TbProducto();
        tbPersona = new TbPersona();
        undMedida = new TbParametrodetalle();
        iva = new TbParametrodetalle();
        clasificacion = new TbParametrodetalle();
        almacen = new TbParametrodetalle();
    }

    public TbParametrodetalle getUndMedida() {
        return undMedida;
    }

    public void setUndMedida(TbParametrodetalle undMedida) {
        this.undMedida = undMedida;
    }

    public TbParametrodetalle getIva() {
        return iva;
    }

    public void setIva(TbParametrodetalle iva) {
        this.iva = iva;
    }

    public TbParametrodetalle getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(TbParametrodetalle clasificacion) {
        this.clasificacion = clasificacion;
    }

    public TbParametrodetalle getAlmacen() {
        return almacen;
    }

    public void setAlmacen(TbParametrodetalle almacen) {
        this.almacen = almacen;
    }
    
    public TbPersona getTbPersona() {
        return tbPersona;
    }

    public void setTbPersona(TbPersona tbPersona) {
        this.tbPersona = tbPersona;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public TbProducto getTbProducto() {
        return tbProducto;
    }

    public void setTbProducto(TbProducto tbProducto) {
        this.tbProducto = tbProducto;
    }

    public int getIdUndMedida() {
        return idUndMedida;
    }

    public void setIdUndMedida(int idUndMedida) {
        this.idUndMedida = idUndMedida;
    }

    public int getIdIva() {
        return idIva;
    }

    public void setIdIva(int idIva) {
        this.idIva = idIva;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
    
    public void registrarProducto(){
        try {
            
            DaoProducto daoProducto = new DaoProducto();
            tbProducto.setTbParametrodetalleByTbParametrodetalleAlmacenes(almacen);
            tbProducto.setTbParametrodetalleByTbParametrodetalleClasificacion(clasificacion);
            tbProducto.setTbParametrodetalleByTbParametrodetalleTasaiva(iva);
            tbProducto.setTbParametrodetalleByTbParametrodetalleUnidadmedida(undMedida);
            tbProducto.setTbPersona(tbPersona);
            msg = daoProducto.registrarProducto(tbProducto);
            
            if(msg){
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
            }else{
                mensajesError("Error al procesar los Datos");
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private void vaciarCajas(){
        tbProducto = new TbProducto();
    }
}
