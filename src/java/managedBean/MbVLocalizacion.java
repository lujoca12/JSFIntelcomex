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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@Named(value = "mbVLocalizacion")
@ViewScoped
public class MbVLocalizacion implements Serializable{

    private TbPais tPais;
    private TbProvincia tProvincia;
    private TbCanton tCanton;
    private TbParroquia tParroquia;
    
    private String idPaisOrigen;
    private String idProvinciaNac = "";
    private String idCantonNac = "";
    private String idParroquiaNac = "";
    private String idEcuador;
    
    private List<SelectItem> lstPais;
    private List<SelectItem> lstProvincia;
    private List<SelectItem> lstProvinciaTabla;
    private List<SelectItem> lstCanton;
    private List<SelectItem> lstParroquia;
    private List<SelectItem> lstTipoEmpresa;
    
    private List<TbPais> lstPaisTbl;
    private TbPais tblPais;
    
    private List<TbProvincia> lstProvinciaTbl;
    private TbProvincia tblProvincia;
    
    private List<TbCanton> lstCantonTbl;
    private TbCanton tblCanton;
    
    private List<TbParroquia> lstParroquiaTbl;
    private TbParroquia tblParroquia;
    
    private Map<String, String> provincias;
    private Map<String, String> cantones;
    private Map<String, String> parroquias;
    
    private Map<String, Map<String, String>> dataProCan = new HashMap<>();
    private Map<String, Map<String, String>> dataCanPar = new HashMap<>();
    
    boolean msg = false;
    
    public MbVLocalizacion() {
        cargarPaises();
        //cargarProvCantParroq();
    }
    
    @PostConstruct
    public void load() {
        tPais = new TbPais();
        tProvincia = new TbProvincia();
        tCanton = new TbCanton();
        tParroquia = new TbParroquia();
        cargarPaises();
        cargarTablaProvincias();
        cargarTablaCanton();
        cargarTablaParroquia();
        //cargarProvCantParroq();
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
    
    public void onPaisChangeTabla(String idPais) {
        lstProvinciaTabla = new ArrayList<>();
        //lstCanton = new ArrayList<>();
        //lstParroquia = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        List<TbProvincia> provincia = locDao.getProvincias(idPais);
        for (TbProvincia p : provincia) {
            SelectItem item = new SelectItem(p.getId(), p.getNombre());
            lstProvinciaTabla.add(item);
        }
    }
    
    public void onProvinciaChange() {
        try {
            lstCanton = new ArrayList<>();
            lstParroquia = new ArrayList<>();
            LocalizacionDao locDao = new LocalizacionDao();
            List<TbCanton> canton = locDao.getCantonProvicia(idProvinciaNac);
            for (TbCanton c : canton) {
                SelectItem item = new SelectItem(c.getId(), c.getNombre());
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
    
    
    
    private void cargarPaises(){
        lstPais = new ArrayList<>();
        lstPaisTbl = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        List<TbPais> paises = locDao.getPaises();
        lstPaisTbl = paises;
        for (TbPais p : paises) {
            SelectItem item = new SelectItem(p.getId(), p.getNombre());
            lstPais.add(item);
            if (p.getNombre().equalsIgnoreCase("ecuador")) {
                idEcuador = String.valueOf(p.getId());
            }
        }
    }
    
    private void cargarTablaProvincias(){
        lstProvinciaTbl = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        try {
                lstProvinciaTbl = locDao.getProvincias(0);
            } catch (Exception ex) {
                Logger.getLogger(MbVLocalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaCanton(){
        lstCantonTbl = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        try {
            lstCantonTbl = locDao.getCanton(0);
        } catch (Exception ex) {
            Logger.getLogger(MbVLocalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaParroquia(){
        lstParroquiaTbl = new ArrayList<>();
        LocalizacionDao locDao = new LocalizacionDao();
        try {
            lstParroquiaTbl = locDao.getParroquia(0);
        } catch (Exception ex) {
            Logger.getLogger(MbVLocalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SelectItem> getLstProvinciaTabla() {
        return lstProvinciaTabla;
    }

    public void setLstProvinciaTabla(List<SelectItem> lstProvinciaTabla) {
        this.lstProvinciaTabla = lstProvinciaTabla;
    }
    
    public List<TbProvincia> getLstProvinciaTbl() {
        return lstProvinciaTbl;
    }

    public void setLstProvinciaTbl(List<TbProvincia> lstProvinciaTbl) {
        this.lstProvinciaTbl = lstProvinciaTbl;
    }

    public TbProvincia getTblProvincia() {
        return tblProvincia;
    }

    public void setTblProvincia(TbProvincia tblProvincia) {
        this.tblProvincia = tblProvincia;
    }
    
    public List<TbPais> getLstPaisTbl() {
        return lstPaisTbl;
    }

    public void setLstPaisTbl(List<TbPais> lstPaisTbl) {
        this.lstPaisTbl = lstPaisTbl;
    }

    public TbPais getTblPais() {
        return tblPais;
    }

    public void setTblPais(TbPais tblPais) {    
        this.tblPais = tblPais;
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
    
    public String getIdEcuador() {
        return idEcuador;
    }

    public void setIdEcuador(String idEcuador) {
        this.idEcuador = idEcuador;
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

    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
    }

    public List<SelectItem> getLstProvincia() {
        return lstProvincia;
    }

    public void setLstProvincia(List<SelectItem> lstProvincia) {
        this.lstProvincia = lstProvincia;
    }

    public List<SelectItem> getLstTipoEmpresa() {
        return lstTipoEmpresa;
    }

    public void setLstTipoEmpresa(List<SelectItem> lstTipoEmpresa) {
        this.lstTipoEmpresa = lstTipoEmpresa;
    }
    
    public TbPais gettPais() {
        return tPais;
    }

    public void settPais(TbPais tPais) {
        this.tPais = tPais;
    }

    public TbProvincia gettProvincia() {
        return tProvincia;
    }

    public void settProvincia(TbProvincia tProvincia) {
        this.tProvincia = tProvincia;
    }

    public TbCanton gettCanton() {
        return tCanton;
    }

    public void settCanton(TbCanton tCanton) {
        this.tCanton = tCanton;
    }

    public TbParroquia gettParroquia() {
        return tParroquia;
    }

    public void settParroquia(TbParroquia tParroquia) {
        this.tParroquia = tParroquia;
    }

    public List<TbCanton> getLstCantonTbl() {
        return lstCantonTbl;
    }

    public void setLstCantonTbl(List<TbCanton> lstCantonTbl) {
        this.lstCantonTbl = lstCantonTbl;
    }

    public TbCanton getTblCanton() {
        return tblCanton;
    }

    public void setTblCanton(TbCanton tblCanton) {
        this.tblCanton = tblCanton;
    }

    public List<TbParroquia> getLstParroquiaTbl() {
        return lstParroquiaTbl;
    }

    public void setLstParroquiaTbl(List<TbParroquia> lstParroquiaTbl) {
        this.lstParroquiaTbl = lstParroquiaTbl;
    }

    public TbParroquia getTblParroquia() {
        return tblParroquia;
    }

    public void setTblParroquia(TbParroquia tblParroquia) {
        this.tblParroquia = tblParroquia;
    }
    
    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void registrarPais() {
        
        try {

            LocalizacionDao localizacion = new LocalizacionDao();           
            msg = localizacion.registrarPaises(tPais);
            
            if(msg){
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarPaises();
            }else{
                mensajesError("Error al procesar los Datos");
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarProvincia() {
        
        try {

            LocalizacionDao localizacion = new LocalizacionDao();  
            TbPais tbPais = new TbPais();
            tbPais.setId(idPaisOrigen);
            tProvincia.setTbPais(tbPais);
            msg = localizacion.registrarProvincia(tProvincia);
            
            if(msg){
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaProvincias();
            }else{
                mensajesError("Error al procesar los Datos");
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarCanton() {
        
        try {

            LocalizacionDao localizacion = new LocalizacionDao();  
            TbProvincia tbProvincia = new TbProvincia();
            tbProvincia.setId(idProvinciaNac);
            tCanton.setTbProvincia(tbProvincia);
            msg = localizacion.registrarCanton(tCanton);
            
            if(msg){
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaCanton();
            }else{
                mensajesError("Error al procesar los Datos");
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarParroquia() {
        
        try {

            LocalizacionDao localizacion = new LocalizacionDao();  
            TbCanton tbCanton = new TbCanton();
            tbCanton.setId(idCantonNac);
            tParroquia.setTbCanton(tbCanton);
            msg = localizacion.registrarParroquia(tParroquia);
            
            if(msg){
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaParroquia();
            }else{
                mensajesError("Error al procesar los Datos");
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void vaciarCajas(){
        tPais = new TbPais();
        tProvincia = new TbProvincia();
        tCanton = new TbCanton();
        tParroquia = new TbParroquia();
        idPaisOrigen = "";
        idProvinciaNac = "";
    }
    
    public void onRowEditPais(RowEditEvent event) {

        
    }
    
    public void onRowCancelPais(RowEditEvent event) {

    }

    public void onDeletePais(TbPais tblPais) {

        
    }
    
    public void onRecuperarPais(TbPais tblPais) {

        
    }
    
    public void onRowEditProvincia(RowEditEvent event) {

        
    }
    
    public void onRowCancelProvincia(RowEditEvent event) {

    }
    
    public void onRowEditCanton(RowEditEvent event) {

        
    }
    
    public void onRowCancelCanton(RowEditEvent event) {

    }
    
    public void onRowEditParroquia(RowEditEvent event) {

        
    }
    
    public void onRowCancelParroquia(RowEditEvent event) {

    }
}
