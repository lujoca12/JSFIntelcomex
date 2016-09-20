/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTEmpresa;
import Dao.DaoTProveedor;
import Dao.LocalizacionDao;
import Pojo.TbCanton;
import Pojo.TbEmpresa;
import Pojo.TbPais;
import Pojo.TbParroquia;
import Pojo.TbPersona;
import Pojo.TbProvincia;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author server
 */
@Named(value = "mbVProveedor")
@ViewScoped
public class MbVProveedor implements Serializable{

    /**
     * Creates a new instance of MbVProveedor
     */
    
    private List<SelectItem> lstPais;
    private List<SelectItem> lstCanton;
    private List<SelectItem> lstProvincia;
    private List<SelectItem> lstParroquia;
    private List<SelectItem> lstTipoEmpresa;
    private List<TbEmpresa> lstEmpresa;
    private TbEmpresa tblEmpresa;
    
    private String idPaisOrigen;
    private String idProvinciaNac = "";
    private String idCantonNac = "";
    private String idParroquiaNac = "";
    private String idEcuador;
    
    
    private TbPersona tbPersona;
//    private TbParametrodetalle tipoEmpresa;
//    private TbParametrodetalle tipoPersona;
    
    boolean msg = false;
    
    public MbVProveedor() {
        tbPersona = new TbPersona();
//        tipoEmpresa = new TbParametrodetalle();
//        tipoPersona = new TbParametrodetalle();
        cargarPaises();
        cargarTablaProveedores();
    }
    private void cargarTablaProveedores(){
        lstEmpresa = new ArrayList<>();
        DaoTEmpresa daoEmpresa = new DaoTEmpresa();
        try {
            lstEmpresa = daoEmpresa.getEmpresa();
        } catch (Exception ex) {
            Logger.getLogger(MbVLocalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TbEmpresa> getLstEmpresa() {
        return lstEmpresa;
    }

    public void setLstEmpresa(List<TbEmpresa> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }

    public TbEmpresa getTblEmpresa() {
        return tblEmpresa;
    }

    public void setTblEmpresa(TbEmpresa tblEmpresa) {
        this.tblEmpresa = tblEmpresa;
    }
    
    public TbPersona getTbPersona() {
        return tbPersona;
    }

    public void setTbPersona(TbPersona tbPersona) {
        this.tbPersona = tbPersona;
    }

//    public TbParametrodetalle getTipoEmpresa() {
//        return tipoEmpresa;
//    }
//
//    public void setTipoEmpresa(TbParametrodetalle tipoEmpresa) {
//        this.tipoEmpresa = tipoEmpresa;
//    }
//
//    public TbParametrodetalle getTipoPersona() {
//        return tipoPersona;
//    }
//
//    public void setTipoPersona(TbParametrodetalle tipoPersona) {
//        this.tipoPersona = tipoPersona;
//    }
    
    public String getIdPaisOrigen() {
        return idPaisOrigen;
    }

    public void setIdPaisOrigen(String idPaisOrigen) {
        this.idPaisOrigen = idPaisOrigen;
    }

    public String getIdProvinciaNac() {
        return idProvinciaNac;
    }

    public void setIdProvinciaNac(String idProvinciaNac) {
        this.idProvinciaNac = idProvinciaNac;
    }

    public String getIdCantonNac() {
        return idCantonNac;
    }

    public void setIdCantonNac(String idCantonNac) {
        this.idCantonNac = idCantonNac;
    }

    public String getIdParroquiaNac() {
        return idParroquiaNac;
    }

    public void setIdParroquiaNac(String idParroquiaNac) {
        this.idParroquiaNac = idParroquiaNac;
    }

    public String getIdEcuador() {
        return idEcuador;
    }

    public void setIdEcuador(String idEcuador) {
        this.idEcuador = idEcuador;
    }
    
    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
    }

    public List<SelectItem> getLstCanton() {
        return lstCanton;
    }

    public void setLstCanton(List<SelectItem> lstCanton) {
        this.lstCanton = lstCanton;
    }

    public List<SelectItem> getLstProvincia() {
        return lstProvincia;
    }

    public void setLstProvincia(List<SelectItem> lstProvincia) {
        this.lstProvincia = lstProvincia;
    }

    public List<SelectItem> getLstParroquia() {
        return lstParroquia;
    }

    public void setLstParroquia(List<SelectItem> lstParroquia) {
        this.lstParroquia = lstParroquia;
    }

    public List<SelectItem> getLstTipoEmpresa() {
        return lstTipoEmpresa;
    }

    public void setLstTipoEmpresa(List<SelectItem> lstTipoEmpresa) {
        this.lstTipoEmpresa = lstTipoEmpresa;
    }
    
    private void cargarPaises(){
        lstPais = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        List<TbPais> paises = locDao.getPaises();
        for (TbPais p : paises) {
            SelectItem item = new SelectItem(p.getId(), p.getNombre());
            lstPais.add(item);
            if (p.getNombre().equalsIgnoreCase("ecuador")) {
                idEcuador = String.valueOf(p.getId());
            }
        }
    }
    
   
   public void onPaisChange() {
        lstProvincia = new ArrayList<>();
        lstCanton = new ArrayList<>();
        lstParroquia = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        List<TbProvincia> provincia = locDao.getProvincias(idPaisOrigen);
        for (TbProvincia p : provincia) {
            SelectItem item = new SelectItem(p.getId(), p.getNombre());
            lstProvincia.add(item);
        }
    }
    
    public void onProvinciaChange() {
        try {
            lstCanton = new ArrayList<>();
            lstParroquia = new ArrayList<>();
            LocalizacionDao locDao = new LocalizacionDao();
            List<TbCanton> canton = locDao.getCantonProvicia(idProvinciaNac);
            for (TbCanton c : canton) {
                SelectItem item = new SelectItem(c.getIdCanton(), c.getNombre());
                lstCanton.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void onCantonChange(){
        try {
            lstParroquia = new ArrayList<>();
            LocalizacionDao locDao = new LocalizacionDao();
            List<TbParroquia> parroquia = locDao.getParroquiaCanton(idCantonNac);
            for (TbParroquia p : parroquia) {
                SelectItem item = new SelectItem(p.getId(), p.getNombre());
                lstParroquia.add(item);
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
    
    public void registrar() {
        
        try {
            DaoTProveedor daoProveedor = new DaoTProveedor();
            //tbPersona.setTbParametrodetalleByTbParametrodetalleTipoempresa(tipoEmpresa);
            //tbPersona.setTbParametrodetalleByTbParametrodetalleTipopersona(tipoPersona);
            TbParroquia tParroquia = new TbParroquia();
            tParroquia.setId(idParroquiaNac);
            tbPersona.setTbParroquia(tParroquia);
            tbPersona.setTelefono(tbPersona.getTelefono().replaceAll("[()-]", ""));
            msg = daoProveedor.registrarProveedor(tbPersona);
            
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
        idParroquiaNac = "";
        idPaisOrigen = "";
        idProvinciaNac = "";
        idCantonNac = "";
        
    }
    public void onRowEdit(RowEditEvent event) {

        
    }
    
    public void onRowCancel(RowEditEvent event) {

    }
}
