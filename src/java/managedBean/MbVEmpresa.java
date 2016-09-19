/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTEmpresa;
import Dao.LocalizacionDao;
import Pojo.TbCanton;
import Pojo.TbEmpresa;
import Pojo.TbPais;
import Pojo.TbParroquia;
import Pojo.TbProvincia;
import Pojo.TbTipoempresa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
 * @author user
 */
@Named(value = "mbVEmpresa")
@ViewScoped
public class MbVEmpresa implements Serializable{

    /**
     * Creates a new instance of MbVEmpresa
     */
    private TbEmpresa tbEmpresa;
    
    private String idPaisOrigen;
    private String idProvinciaNac = "";
    private String idCantonNac = "";
    private String idParroquiaNac = "";
    private String idEcuador;
    private String tipoEmpresa = "";
    
    private Map<String, String> provincias;
    private Map<String, String> cantones;
    private Map<String, String> parroquias;
    
    private Map<String, Map<String, String>> dataProCan = new HashMap<>();
    private Map<String, Map<String, String>> dataCanPar = new HashMap<>();
    
    private List<SelectItem> lstPais;
    private List<SelectItem> lstCanton;
    private List<SelectItem> lstProvincia;
    private List<SelectItem> lstParroquia;
    private List<SelectItem> lstTipoEmpresa;
    private List<TbEmpresa> lstEmpresa;
    private TbEmpresa tblEmpresa;
    
    boolean msg = false;
    
    public MbVEmpresa() {
        tbEmpresa = new TbEmpresa();
        cargarPaises();
        cargarTablaProveedores();
       // cargarProvCantParroq();
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
    
    public List<SelectItem> getLstCanton() {
        return lstCanton;
    }

    public void setLstCanton(List<SelectItem> lstCanton) {
        this.lstCanton = lstCanton;
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
    
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }
    
    public List<SelectItem> getLstProvincia() {
        return lstProvincia;
    }

    public void setLstProvincia(List<SelectItem> lstProvincia) {
        this.lstProvincia = lstProvincia;
    }
    
    public TbEmpresa getTbEmpresa() {
        return tbEmpresa;
    }

    public void setTbEmpresa(TbEmpresa tbEmpresa) {
        this.tbEmpresa = tbEmpresa;
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

    public Map<String, String> getProvincias() {
        return provincias;
    }

    public void setProvincias(Map<String, String> provincias) {
        this.provincias = provincias;
    }

    public Map<String, String> getCantones() {
        return cantones;
    }

    public void setCantones(Map<String, String> cantones) {
        this.cantones = cantones;
    }

    public Map<String, String> getParroquias() {
        return parroquias;
    }

    public void setParroquias(Map<String, String> parroquias) {
        this.parroquias = parroquias;
    }

    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
    }

    public Map<String, Map<String, String>> getDataProCan() {
        return dataProCan;
    }

    public void setDataProCan(Map<String, Map<String, String>> dataProCan) {
        this.dataProCan = dataProCan;
    }

    public Map<String, Map<String, String>> getDataCanPar() {
        return dataCanPar;
    }

    public void setDataCanPar(Map<String, Map<String, String>> dataCanPar) {
        this.dataCanPar = dataCanPar;
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

            DaoTEmpresa daoEmpresa = new DaoTEmpresa();
            TbTipoempresa tbTipoEmpresa = new TbTipoempresa();
            tbTipoEmpresa.setId(Integer.valueOf(tipoEmpresa));
            tbEmpresa.setTbTipoempresa(tbTipoEmpresa);
            TbParroquia tParroquia = new TbParroquia();
            tParroquia.setId(idParroquiaNac);
            tbEmpresa.setTbParroquia(tParroquia);
            msg = daoEmpresa.registrar(tbEmpresa);
            
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
        tbEmpresa = new TbEmpresa();
        idParroquiaNac = "";
        tipoEmpresa = "";
        idPaisOrigen = "";
        idProvinciaNac = "";
        idCantonNac = "";
    }
    public void onRowEdit(RowEditEvent event) {

        
    }
    
    public void onRowCancel(RowEditEvent event) {

    }
}
