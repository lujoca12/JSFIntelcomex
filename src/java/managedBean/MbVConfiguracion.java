/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTConfiguracion;
import Dao.DaoTEmpresa;
import Pojo.TbEmpresa;
import Pojo.TbTipoalmacenes;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author server
 */
@Named(value = "mbVConfiguracion")
@ViewScoped
public class MbVConfiguracion implements Serializable{

    private List<TbTipotasaiva> lstImpuesto;
    private List<TbTipopago> lstTipoPago;
    private List<TbTipoalmacenes> lstAlmacenes;
    private List<TbTipopedido> lstPedido;
    private List<TbTipounidadmedida> lstUnidadMedida;
    
    private TbTipopago tblTipoPago;
    private TbTipoalmacenes tblAlmacenes;
    private TbTipopedido tblTipoPedido;
    private TbTipotasaiva tblImpuesto;
    private TbTipounidadmedida tblUnidadMedida;
    
    
    private TbTipopago tTipoPago;
    private TbTipoalmacenes tAlmacenes;
    private TbTipopedido tTipoPedido;
    private TbTipotasaiva tImpuesto;
    private TbTipounidadmedida tUnidadMedida;
    
    boolean msg = false;
    public MbVConfiguracion() {
        tTipoPago = new TbTipopago();
        tAlmacenes = new TbTipoalmacenes();
        tTipoPedido = new TbTipopedido();
        tImpuesto = new TbTipotasaiva();
        tUnidadMedida = new TbTipounidadmedida();
        cargarTablaImpuesto();
        cargarTablaAlmacenes();
        cargarTablaTipoPago();
        cargarTablaTipoPedido();
        cargarTablaUnidadMedida();
    }
    
    private void cargarTablaImpuesto(){
        lstImpuesto = new ArrayList<>();
        DaoTConfiguracion daoConfig = new DaoTConfiguracion();
        try {
            lstImpuesto = daoConfig.getImpuesto();
        } catch (Exception ex) {
            Logger.getLogger(MbVConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaTipoPago(){
        lstTipoPago = new ArrayList<>();
        DaoTConfiguracion daoConfig = new DaoTConfiguracion();
        try {
            lstTipoPago = daoConfig.getTipoPago();
        } catch (Exception ex) {
            Logger.getLogger(MbVConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaAlmacenes(){
        lstAlmacenes = new ArrayList<>();
        DaoTConfiguracion daoConfig = new DaoTConfiguracion();
        try {
            lstAlmacenes = daoConfig.getTipoAlmacenes();
        } catch (Exception ex) {
            Logger.getLogger(MbVConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaTipoPedido(){
        lstPedido = new ArrayList<>();
        DaoTConfiguracion daoConfig = new DaoTConfiguracion();
        try {
            lstPedido = daoConfig.getTipoPedido();
        } catch (Exception ex) {
            Logger.getLogger(MbVConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaUnidadMedida(){
        lstUnidadMedida = new ArrayList<>();
        DaoTConfiguracion daoConfig = new DaoTConfiguracion();
        try {
            lstUnidadMedida = daoConfig.getTipoUndMedida();
        } catch (Exception ex) {
            Logger.getLogger(MbVConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TbTipopago getTblTipoPago() {
        return tblTipoPago;
    }

    public void setTblTipoPago(TbTipopago tblTipoPago) {
        this.tblTipoPago = tblTipoPago;
    }

    public TbTipoalmacenes getTblAlmacenes() {
        return tblAlmacenes;
    }

    public void setTblAlmacenes(TbTipoalmacenes tblAlmacenes) {
        this.tblAlmacenes = tblAlmacenes;
    }

    public TbTipopedido getTblTipoPedido() {
        return tblTipoPedido;
    }

    public void setTblTipoPedido(TbTipopedido tblTipoPedido) {
        this.tblTipoPedido = tblTipoPedido;
    }

    public TbTipotasaiva getTblImpuesto() {
        return tblImpuesto;
    }

    public void setTblImpuesto(TbTipotasaiva tblImpuesto) {
        this.tblImpuesto = tblImpuesto;
    }

    public TbTipounidadmedida getTblUnidadMedida() {
        return tblUnidadMedida;
    }

    public void setTblUnidadMedida(TbTipounidadmedida tblUnidadMedida) {
        this.tblUnidadMedida = tblUnidadMedida;
    }
    
    public List<TbTipotasaiva> getLstImpuesto() {
        return lstImpuesto;
    }

    public void setLstImpuesto(List<TbTipotasaiva> lstImpuesto) {
        this.lstImpuesto = lstImpuesto;
    }

    public List<TbTipopago> getLstTipoPago() {
        return lstTipoPago;
    }

    public void setLstTipoPago(List<TbTipopago> lstTipoPago) {
        this.lstTipoPago = lstTipoPago;
    }

    public List<TbTipoalmacenes> getLstAlmacenes() {
        return lstAlmacenes;
    }

    public void setLstAlmacenes(List<TbTipoalmacenes> lstAlmacenes) {
        this.lstAlmacenes = lstAlmacenes;
    }

    public List<TbTipopedido> getLstPedido() {
        return lstPedido;
    }

    public void setLstPedido(List<TbTipopedido> lstPedido) {
        this.lstPedido = lstPedido;
    }

    public List<TbTipounidadmedida> getLstUnidadMedida() {
        return lstUnidadMedida;
    }

    public void setLstUnidadMedida(List<TbTipounidadmedida> lstUnidadMedida) {
        this.lstUnidadMedida = lstUnidadMedida;
    }
    
    public TbTipopago gettTipoPago() {
        return tTipoPago;
    }

    public void settTipoPago(TbTipopago tTipoPago) {
        this.tTipoPago = tTipoPago;
    }

    public TbTipoalmacenes gettAlmacenes() {
        return tAlmacenes;
    }

    public void settAlmacenes(TbTipoalmacenes tAlmacenes) {
        this.tAlmacenes = tAlmacenes;
    }

    public TbTipopedido gettTipoPedido() {
        return tTipoPedido;
    }

    public void settTipoPedido(TbTipopedido tTipoPedido) {
        this.tTipoPedido = tTipoPedido;
    }

    public TbTipotasaiva gettImpuesto() {
        return tImpuesto;
    }

    public void settImpuesto(TbTipotasaiva tImpuesto) {
        this.tImpuesto = tImpuesto;
    }

    public TbTipounidadmedida gettUnidadMedida() {
        return tUnidadMedida;
    }

    public void settUnidadMedida(TbTipounidadmedida tUnidadMedida) {
        this.tUnidadMedida = tUnidadMedida;
    }
    
    public void registrarImpuestos() {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            msg = daoConfig.registrarImpuersto(tImpuesto);
            
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
    public void registrarTipoPago() {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            msg = daoConfig.registrarTipoPago(tTipoPago);
            
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
    
    public void registrarTipoAlmacenes() {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            msg = daoConfig.registrarTipoAlmacenes(tAlmacenes);
            
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
    
    public void registrarTipoPedido() {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            msg = daoConfig.registrarTipoPedido(tTipoPedido);
            
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
    
    public void registrarTipoUnidMedida() {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            msg = daoConfig.registrarUnidadMedida(tUnidadMedida);
            
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
    
    private void vaciarCajas(){
        tTipoPago = new TbTipopago();
        tAlmacenes = new TbTipoalmacenes();
        tTipoPedido = new TbTipopedido();
        tImpuesto = new TbTipotasaiva();
        tUnidadMedida = new TbTipounidadmedida();
    }
    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void onRowEditImpuesto(RowEditEvent event) {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            tblImpuesto = ((TbTipotasaiva) event.getObject());
            msg = daoConfig.registrarImpuersto(tblImpuesto);
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                // vaciarCajas();
                cargarTablaImpuesto();
            } else {
                mensajesError("Error al procesar los Datos");
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancelImpuesto(RowEditEvent event) {

    }
    
    public void onRowEditAlmacenes(RowEditEvent event) {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            tblAlmacenes = ((TbTipoalmacenes) event.getObject());
            msg = daoConfig.registrarTipoAlmacenes(tblAlmacenes);
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                // vaciarCajas();
                cargarTablaImpuesto();
            } else {
                mensajesError("Error al procesar los Datos");
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancelAlmacenes(RowEditEvent event) {

    }
    
    public void onRowEditTipoPago(RowEditEvent event) {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            tblTipoPago = ((TbTipopago) event.getObject());
            msg = daoConfig.registrarTipoPago(tblTipoPago);
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                // vaciarCajas();
                cargarTablaImpuesto();
            } else {
                mensajesError("Error al procesar los Datos");
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancelTipoPago(RowEditEvent event) {

    }
    
    public void onRowEditTipoPedido(RowEditEvent event) {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            tblTipoPedido = ((TbTipopedido) event.getObject());
            msg = daoConfig.registrarTipoPedido(tblTipoPedido);
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                // vaciarCajas();
                cargarTablaImpuesto();
            } else {
                mensajesError("Error al procesar los Datos");
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancelTipoPedido(RowEditEvent event) {

    }
    
    public void onRowEditUnidadMedida(RowEditEvent event) {
        try {
            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
            tblUnidadMedida = ((TbTipounidadmedida) event.getObject());
            msg = daoConfig.registrarUnidadMedida(tUnidadMedida);
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                // vaciarCajas();
                cargarTablaImpuesto();
            } else {
                mensajesError("Error al procesar los Datos");
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancelUnidadMedida(RowEditEvent event) {

    }
}
