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
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.FilenameUtils;

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
    private List<UploadedFile> files = new ArrayList<>();
    private boolean msg;
    
    private TbProducto tbProducto;
    private TbPersona tbPersona;
    private TbTipoalmacenes tbAlmacen;
    private TbTipotasaiva tbImpuesto;
    private TbTipounidadmedida tbUndMedida;
    private TbTipoclasificacion tbClasificacion;
    
    private List<SelectItem> lstImpuesto;
    private List<SelectItem> lstTipoPago;
    private List<SelectItem> lstAlmacenes;
    private List<SelectItem> lstClasificacion;
    private List<SelectItem> lstPedido;
    private List<SelectItem> lstUnidadMedida;
    private List<SelectItem> lstPersona;
    
   private String extension = "";
    
    public MbVProducto() {
        tbProducto = new TbProducto();
        tbPersona = new TbPersona();
        tbAlmacen = new TbTipoalmacenes();
        tbImpuesto = new TbTipotasaiva();
        tbUndMedida = new TbTipounidadmedida();
        tbClasificacion = new TbTipoclasificacion();
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
                SelectItem item = new SelectItem(p.getIdTipoTasa(), p.getNombre()+" "+p.getValor());
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

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }
    
    public TbProducto getTbProducto() {
        return tbProducto;
    }

    public void setTbProducto(TbProducto tbProducto) {
        this.tbProducto = tbProducto;
    }

    public TbTipoalmacenes getTbAlmacen() {
        return tbAlmacen;
    }

    public void setTbAlmacen(TbTipoalmacenes tbAlmacen) {
        this.tbAlmacen = tbAlmacen;
    }

    public TbTipotasaiva getTbImpuesto() {
        return tbImpuesto;
    }

    public void setTbImpuesto(TbTipotasaiva tbImpuesto) {
        this.tbImpuesto = tbImpuesto;
    }

    public TbTipounidadmedida getTbUndMedida() {
        return tbUndMedida;
    }

    public void setTbUndMedida(TbTipounidadmedida tbUndMedida) {
        this.tbUndMedida = tbUndMedida;
    }

    public TbTipoclasificacion getTbClasificacion() {
        return tbClasificacion;
    }

    public void setTbClasificacion(TbTipoclasificacion tbClasificacion) {
        this.tbClasificacion = tbClasificacion;
    }
    
    public void registrarProducto(){
        try {
            
            DaoProducto daoProducto = new DaoProducto();
            tbProducto.setTbPersona(tbPersona);
            tbProducto.setTbTipoalmacenes(tbAlmacen);
            tbProducto.setTbTipoclasificacion(tbClasificacion);
            tbProducto.setTbTipotasaiva(tbImpuesto);
            tbProducto.setTbTipounidadmedida(tbUndMedida);
            
          //  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          //  Date dateobj = new Date();
          //  String nombreCarpeta = (tbProducto.getId() + "-" + df.format(dateobj).replaceAll(":", "-")).trim();
            File directorio = new File("c:/Productos/");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }     
               extension = FilenameUtils.getExtension(file.getFileName());
                Path ruta = Paths.get(directorio + "/" + tbProducto.getId()+"-"+ tbProducto.getNombre() + "." + extension);
                InputStream input = file.getInputstream();
                Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
                tbProducto.setImagendir(ruta.toString());
            
            
            
            
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
    public boolean errorArchivos() {
        int x = 0;
        boolean error = false;
//        for (UploadedFile f : files) {
//            extension = FilenameUtils.getExtension(f.getFileName());
//            if (!reqPro.get(x).getRequisitos().getTipoArchivo().contains(extension)) {
////                error = true;
//                files.clear();
//                break;
//            }
//            x++;
//        }
        return error;
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
