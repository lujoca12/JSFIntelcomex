/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.LocalizacionDao;
import Pojo.TbCanton;
import Pojo.TbEmpresa;
import Pojo.TbPais;
import Pojo.TbParroquia;
import Pojo.TbProvincia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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
    
    private Map<String, String> provincias;
    private Map<String, String> cantones;
    private Map<String, String> parroquias;
    
    private Map<String, Map<String, String>> dataProCan = new HashMap<>();
    private Map<String, Map<String, String>> dataCanPar = new HashMap<>();
    
    private List<SelectItem> lstPais;
    private List<SelectItem> lstProvincia;
    
    boolean msg = false;
    
    public MbVEmpresa() {
        tbEmpresa = new TbEmpresa();
        cargarPaises();
        cargarProvCantParroq();
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
    
    private void cargarProvCantParroq() {
        //prov canton parroq
        try {
            provincias = new LinkedHashMap<>();
            lstProvincia = new ArrayList<>();
            LocalizacionDao locDao = new LocalizacionDao();
            List<SelectItem> itemsProv = new ArrayList<>();
            List<TbProvincia> prov = locDao.getProvincias();
            for (TbProvincia p : prov) {
                SelectItem item = new SelectItem(p.getId(), p.getNombre());
                itemsProv.add(item);
            }
            for (SelectItem i : itemsProv) {
                provincias.put(i.getLabel(), i.getValue().toString());
                List<TbCanton> lstC = locDao.getCantonProvicia(Integer.valueOf(i.getValue().toString()));

                Map<String, String> map3 = new LinkedHashMap<>();
                for (TbCanton c : lstC) {
                    map3.put(c.getNombre(), String.valueOf(c.getId()));
                    List<TbParroquia> lstPa = locDao.getParroquiaCanton(Integer.valueOf(c.getId()));
                    Map<String, String> map4 = new LinkedHashMap<>();
                    for (TbParroquia pa : lstPa) {
                        map4.put(pa.getNombre(), String.valueOf(pa.getId()));
                    }
                    dataCanPar.put(String.valueOf(c.getId()), map4);
                }
                dataProCan.put(i.getValue().toString(), map3);
            }
        } catch (Exception e) {
        }

    }
    
    public void onProvinciaNacChange() {
        try {
            cantones = new HashMap<>();
            parroquias = new HashMap<>();
            if (idProvinciaNac != null && !idProvinciaNac.equals("")) {
                cantones = dataProCan.get(idProvinciaNac);
            } else {
                cantones = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onCantonNacChange() {
        try {
            parroquias = new HashMap<>();
            if (idCantonNac != null && !idCantonNac.equals("")) {
                parroquias = dataCanPar.get(idCantonNac);
            } else {
                parroquias = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
