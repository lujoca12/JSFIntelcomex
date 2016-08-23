/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTEmpresa;
import Dao.DaoTParametro;
import Pojo.TbEmpresa;
import Pojo.TbParametro;
import Pojo.TbParametrodetalle;
import Pojo.TbParroquia;
import Pojo.TbTipoempresa;
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

/**
 *
 * @author server
 */
@Named(value = "mbVParametro")
@ViewScoped
public class MbVParametro implements Serializable{

    /**
     * Creates a new instance of MbVParametro
     */
    private TbParametro tbParametro;
    private TbParametrodetalle tbParametroDetalle;
    private boolean msg;
    
    private List<SelectItem> lstParametro;
    
    public MbVParametro() {
        tbParametro = new TbParametro();
        tbParametroDetalle = new TbParametrodetalle();
        cargarParametrosOrd();
    }
    
    public void cargarParametrosOrd(){
        try {
            lstParametro = new ArrayList<>();
            DaoTParametro daoParametros = new DaoTParametro();
            List<TbParametro> parametro = daoParametros.getParametro();
            for (TbParametro p : parametro) {
                SelectItem item = new SelectItem(p.getId(), p.getCategoria());
                lstParametro.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SelectItem> getLstParametro() {
        return lstParametro;
    }

    public void setLstParametro(List<SelectItem> lstParametro) {
        this.lstParametro = lstParametro;
    }
    
    public TbParametro getTbParametro() {
        return tbParametro;
    }

    public void setTbParametro(TbParametro tbParametro) {
        this.tbParametro = tbParametro;
    }

    public TbParametrodetalle getTbParametroDetalle() {
        return tbParametroDetalle;
    }

    public void setTbParametroDetalle(TbParametrodetalle tbParametroDetalle) {
        this.tbParametroDetalle = tbParametroDetalle;
    }
    
    public void registrarParametro(){
        try {
            
            DaoTParametro daoParametro = new DaoTParametro();
            msg = daoParametro.registrarParametro(tbParametro);
            
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
    
    public void registrarParametroDetalle(){
        try {
            
            DaoTParametro daoParametro = new DaoTParametro();
            tbParametroDetalle.setTbParametro(tbParametro);
            msg = daoParametro.registrarParametroDetalle(tbParametroDetalle);
            
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
        tbParametro = new TbParametro();
        tbParametroDetalle = new TbParametrodetalle();
    }
}
