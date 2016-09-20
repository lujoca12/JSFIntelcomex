/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoProducto;
import Dao.DaoTCliente;
import Dao.DaoTConfiguracion;
import Pojo.TbPersona;
import Pojo.TbProducto;
import Pojo.TbProvincia;
import Pojo.TbTipoalmacenes;
import Pojo.TbTipoclasificacion;
import Pojo.TbTipopago;
import Pojo.TbTipopedido;
import Pojo.TbTipotasaiva;
import Pojo.TbTipounidadmedida;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    
    private List<SelectItem> lstImpuesto;
    private List<SelectItem> lstTipoPago;
    private List<SelectItem> lstAlmacenes;
    private List<SelectItem> lstClasificacion;
    private List<SelectItem> lstPedido;
    private List<SelectItem> lstUnidadMedida;
    private List<SelectItem> lstPersona;
    
    private int idUndMedida;
    private int idIva;
    private int idClasificacion;
    private int idAlmacen;
    
    public MbVProducto() {
        tbProducto = new TbProducto();
        tbPersona = new TbPersona();
        cargarCboImpuesto();
        cargarCboProveedor();
        cargarCboUnidadMedida();
        cargarCboAlmacenes();
        cargarCboClasificacion();
    }
    
    private void cargarCboUnidadMedida(){
        try {
            lstUnidadMedida = new ArrayList<>();
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            List<TbTipounidadmedida> unidad = daoConfig.getTipoUndMedida();
            for (TbTipounidadmedida p : unidad) {
                SelectItem item = new SelectItem(p.getIdTipoUnidadmedida(), p.getNombre());
                lstUnidadMedida.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarCboClasificacion(){
        try {
            lstClasificacion = new ArrayList<>();
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            List<TbTipoclasificacion> unidad = daoConfig.getClasificacion();
            for (TbTipoclasificacion p : unidad) {
                SelectItem item = new SelectItem(p.getIdTipoClasificacion(), p.getNombre());
                lstClasificacion.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarCboAlmacenes(){
        try {
            lstAlmacenes = new ArrayList<>();
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            List<TbTipoalmacenes> almacenes = daoConfig.getTipoAlmacenes();
            for (TbTipoalmacenes p : almacenes) {
                SelectItem item = new SelectItem(p.getIdTipoAlmacenes(), p.getNombre());
                lstAlmacenes.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarCboProveedor(){
        try {
            lstPersona = new ArrayList<>();
            DaoTCliente daoCliente = new DaoTCliente();
            List<TbPersona> persona = daoCliente.getProveedor();
            for (TbPersona p : persona) {
                SelectItem item = new SelectItem(p.getCedula(), p.getApellidos()+" "+p.getNombres());
                lstPersona.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarCboImpuesto(){
        
        try {
            lstImpuesto = new ArrayList<>();
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            List<TbTipotasaiva> iva = daoConfig.getImpuesto();
            for (TbTipotasaiva p : iva) {
                SelectItem item = new SelectItem(p.getIdTipoTasa(), p.getNombre());
                lstImpuesto.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SelectItem> getLstClasificacion() {
        return lstClasificacion;
    }

    public void setLstClasificacion(List<SelectItem> lstClasificacion) {
        this.lstClasificacion = lstClasificacion;
    }
    
    public List<SelectItem> getLstImpuesto() {
        return lstImpuesto;
    }

    public void setLstImpuesto(List<SelectItem> lstImpuesto) {
        this.lstImpuesto = lstImpuesto;
    }

    public List<SelectItem> getLstTipoPago() {
        return lstTipoPago;
    }

    public void setLstTipoPago(List<SelectItem> lstTipoPago) {
        this.lstTipoPago = lstTipoPago;
    }

    public List<SelectItem> getLstAlmacenes() {
        return lstAlmacenes;
    }

    public void setLstAlmacenes(List<SelectItem> lstAlmacenes) {
        this.lstAlmacenes = lstAlmacenes;
    }

    public List<SelectItem> getLstPedido() {
        return lstPedido;
    }

    public void setLstPedido(List<SelectItem> lstPedido) {
        this.lstPedido = lstPedido;
    }

    public List<SelectItem> getLstUnidadMedida() {
        return lstUnidadMedida;
    }

    public void setLstUnidadMedida(List<SelectItem> lstUnidadMedida) {
        this.lstUnidadMedida = lstUnidadMedida;
    }

    public List<SelectItem> getLstPersona() {
        return lstPersona;
    }

    public void setLstPersona(List<SelectItem> lstPersona) {
        this.lstPersona = lstPersona;
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
//            tbProducto.setTbParametrodetalleByTbParametrodetalleAlmacenes(almacen);
//            tbProducto.setTbParametrodetalleByTbParametrodetalleClasificacion(clasificacion);
//            tbProducto.setTbParametrodetalleByTbParametrodetalleTasaiva(iva);
//            tbProducto.setTbParametrodetalleByTbParametrodetalleUnidadmedida(undMedida);
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
