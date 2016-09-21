/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTEmpresa;
import Dao.DaoTCliente;
import Dao.LocalizacionDao;
import Pojo.TbCanton;
import Pojo.TbEmpresa;
import Pojo.TbPais;
import Pojo.TbParroquia;
import Pojo.TbPersona;
import Pojo.TbProvincia;
import Pojo.TbTipoempresa;
import Pojo.TbTipopersona;
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
@Named(value = "mbVCliente")
@ViewScoped
public class MbVCliente implements Serializable{

    /**
     * Creates a new instance of MbVCliente
     */
    
    private List<SelectItem> lstPais;
    private List<SelectItem> lstCanton;
    private List<SelectItem> lstProvincia;
    private List<SelectItem> lstParroquia;
    private List<SelectItem> lstTipoEmpresa;
    private List<TbPersona> lstPersona;
    private List<TbPersona> lstProveedor;
    private TbPersona tblPersona;
        
    private String idPaisOrigen;
    private String idProvinciaNac = "";
    private String idCantonNac = "";
    private String idParroquiaNac = "";
    private String idEcuador;
    
    
    private TbPersona tbPersona;
//    private TbParametrodetalle tipoEmpresa;
//    private TbParametrodetalle tipoPersona;
    
    boolean msg = false;
    
    public MbVCliente() {
        tbPersona = new TbPersona();
//        tipoEmpresa = new TbParametrodetalle();
//        tipoPersona = new TbParametrodetalle();
        cargarPaises();
        cargarTablaPersona();
        cargarTablaProveedor();
    }
    private void cargarTablaPersona(){
        lstPersona = new ArrayList<>();
        DaoTCliente daoCliente = new DaoTCliente();
        try {
            lstPersona = daoCliente.getCliente();
        } catch (Exception ex) {
            Logger.getLogger(MbVLocalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaProveedor(){
        lstProveedor = new ArrayList<>();
        DaoTCliente daoCliente = new DaoTCliente();
        try {
            lstProveedor = daoCliente.getTblProveedor();
        } catch (Exception ex) {
            Logger.getLogger(MbVLocalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TbPersona> getLstProveedor() {
        return lstProveedor;
    }

    public void setLstProveedor(List<TbPersona> lstProveedor) {
        this.lstProveedor = lstProveedor;
    }
    
    public TbPersona getTbPersona() {
        return tbPersona;
    }

    public void setTbPersona(TbPersona tbPersona) {
        this.tbPersona = tbPersona;
    }

    public List<TbPersona> getLstPersona() {
        return lstPersona;
    }

    public void setLstPersona(List<TbPersona> lstPersona) {
        this.lstPersona = lstPersona;
    }

    public TbPersona getTblPersona() {
        return tblPersona;
    }

    public void setTblPersona(TbPersona tblPersona) {
        this.tblPersona = tblPersona;
    }

    
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
            DaoTCliente daoCliente = new DaoTCliente();
            //tbPersona.setTbParametrodetalleByTbParametrodetalleTipoempresa(tipoEmpresa);
            //tbPersona.setTbParametrodetalleByTbParametrodetalleTipopersona(tipoPersona);
//            TbParroquia tParroquia = new TbParroquia();
//            tParroquia.setId(idParroquiaNac);

//            tbPersona.setTbParroquia(tParroquia);
            //falta consultar el id del tipoPersona like cliente
            TbTipopersona tipoPersona = new TbTipopersona();
            tipoPersona = (TbTipopersona) daoCliente.getTipoCliente();
            tbPersona.setTelefono(tbPersona.getTelefono().replaceAll("[()-]", ""));
            tbPersona.setTbTipopersona(tipoPersona);
            msg = daoCliente.registrarCliente(tbPersona);
            
            if(msg){
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaPersona();
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
        try {
            DaoTCliente daoCliente = new DaoTCliente();
            tblPersona = ((TbPersona) event.getObject());
            msg = daoCliente.registrarCliente(tblPersona);
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                // vaciarCajas();
                cargarTablaPersona();
            } else {
                mensajesError("Error al procesar los Datos");
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onRowCancel(RowEditEvent event) {

    }
}
