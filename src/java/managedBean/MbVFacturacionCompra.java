/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsEmpleado;
import Clases.ClsFactura;
import Clases.ClsProducto;
import Dao.DaoFacturacion;
import Dao.DaoProducto;
import Dao.DaoTCliente;
import Dao.DaoTConfiguracion;
import Dao.DaoTUsuario;
import Pojo.TbDetallefactura;
import Pojo.TbFactura;
import Pojo.TbInventario;
import Pojo.TbPersona;
import Pojo.TbProducto;
import Pojo.TbTipopago;
import Pojo.TbUsuarios;
import java.io.Serializable;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author server
 */
@Named(value = "mbVFacturacionCompra")
@ViewScoped
public class MbVFacturacionCompra implements Serializable{

    /**
     * Creates a new instance of MbVFacturacionCompra
     */
    private TbPersona tbPersona;
    private TbFactura tbFactura;
    private TbProducto tbProducto;
    private TbDetallefactura tbDetallefactura;
    private TbTipopago tbTipopago;
    
    private List<TbFactura> lstTblFactura;
    private List<SelectItem> lstTipoPago;
    
    private ClsFactura clsFactura;
    private List<ClsFactura> lstFactura;
    
    private Double Subtotal;
    private Double tarifa0;
    private Double tarifa14;
    private Double Total;
    private String cantidad;
    
    private boolean msg;
    private Date fecha;
    private int cont = 0;
    
    private ClsEmpleado themeUsuarios;
    private List<ClsEmpleado> lstThemeUsuarios;
    
    private ClsProducto themeProductos;
    private List<ClsProducto> lstThemeProductos;
    private int stock = 0;
    
    public MbVFacturacionCompra() {
        tbPersona = new TbPersona();
        tbFactura = new TbFactura();
        tbProducto = new TbProducto();
        tbDetallefactura = new TbDetallefactura();
        tbTipopago = new TbTipopago();
        fecha = new Date();
        Subtotal = 0.0;
        tarifa0= 0.0;
        tarifa14= 0.0;
        Total= 0.0;
        cantidad = "";
        llenarCboProducto();
        llenarCboUsuarios();
        llenarCboProveedor();
        cargarTipoPago();
        lstFactura = new ArrayList<>();
    }
    
    private void cargarTipoPago(){
        lstTipoPago = new ArrayList<>();
        DaoTConfiguracion daoConfig = new DaoTConfiguracion();
        List<TbTipopago> tipoPago = daoConfig.getTipoPago();
        for (TbTipopago p : tipoPago) {
            SelectItem item = new SelectItem(p.getId(), p.getNombre());
            lstTipoPago.add(item);
        }
    }
    ///Agregando Productos a la tabla de factura
    public void agregarProducto(){
        //if(lstFactura.size() <= 0){
        if(this.themeUsuarios == null || this.themeProductos == null || tbTipopago.getId() <= 0 ){
                mensajesError("Faltan campos por seleccionar");
                return;
            } else {
                boolean band = false;
                double temp = 0.0;
                //Condicion para saber si hay mas de un registro en la lista
                if(lstFactura.size() > 0){
                    for (int i = 0; i < lstFactura.size(); i++) {
                        //Condicion para comparar productos repetidos y actualizarlos
                        if(lstFactura.get(i).getIdProducto().equals(this.themeProductos.getId().toString())){
                            if((lstFactura.get(i).getCantidad()+ Double.parseDouble(cantidad)) > this.themeProductos.getCantidad()){
                                temp = lstFactura.get(i).getCantidad() + (this.themeProductos.getCantidad()-lstFactura.get(i).getCantidad());
                                lstFactura.get(i).setCantidad(temp);
                                lstFactura.get(i).setTotalxProducto(lstFactura.get(i).getCantidad()*lstFactura.get(i).getPvp());
                                band = true;
                            }else{
                                temp = lstFactura.get(i).getCantidad() + Double.parseDouble(cantidad);
                                lstFactura.get(i).setCantidad(temp);
                                lstFactura.get(i).setTotalxProducto(lstFactura.get(i).getCantidad()*lstFactura.get(i).getPvp());
                                band = true;
                            }
                        }
                    }
                }else if(band == false){
                    lstFactura.add(new ClsFactura((cont + 1),
                        this.themeProductos.getId(),
                        this.themeProductos.getNombre(),
                        Double.parseDouble(cantidad),
                        (this.themeProductos.getPvp() * Double.parseDouble(cantidad)),
                        this.themeProductos.getCosto(),
                        0.0,
                        this.themeProductos.getPvp(),
                        this.themeProductos.getIva()));
                }
                
                if(band){
                    tarifa14 = 0.0;Total=0.0;Subtotal = 0.0;tarifa0=0.0;
                    for (int i = 0; i < lstFactura.size(); i++) {
                        
                        if (lstFactura.get(i).getIva() == 12.0) {
                            tarifa14 += (lstFactura.get(i).getPvp() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getPvp() * lstFactura.get(i).getCantidad()) / 1.12);
                            Total += (lstFactura.get(i).getPvp() * lstFactura.get(i).getCantidad());
                            Subtotal = Total / 1.12;
                        } else if (lstFactura.get(i).getIva() == 14.0) {
                            tarifa14 += (lstFactura.get(i).getPvp() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getPvp() * lstFactura.get(i).getCantidad()) / 1.14);
                            Total += (lstFactura.get(i).getPvp() * lstFactura.get(i).getCantidad());
                            Subtotal = Total / 1.14;
                        } else if (lstFactura.get(i).getIva() == 0) {
                            tarifa0 += lstFactura.get(i).getPvp();
                            Total += tarifa0;
                        }
                    }
                }else{
                    if (this.themeProductos.getIva() == 12.0) {
                        tarifa14 += (this.themeProductos.getPvp() * Double.parseDouble(cantidad)) - ((this.themeProductos.getPvp() * Double.parseDouble(cantidad)) / 1.12);
                        Total += (this.themeProductos.getPvp() * Double.parseDouble(cantidad));
                        Subtotal = Total / 1.12;
                    } else if (this.themeProductos.getIva() == 14.0) {
                        tarifa14 += (this.themeProductos.getPvp() * Double.parseDouble(cantidad)) - ((this.themeProductos.getPvp() * Double.parseDouble(cantidad)) / 1.14);
                        Total += (this.themeProductos.getPvp() * Double.parseDouble(cantidad));
                        Subtotal = Total / 1.14;
                    } else if (this.themeProductos.getIva() == 0) {
                        tarifa0 += this.themeProductos.getPvp();
                        Total += tarifa0;
                    }
                    cont++;
                }
                
                
                
            
        }
            
        //}else{
            
        //}
        vaciarEncabezadoFactura();
    }
    private void vaciarEncabezadoFactura(){
        cantidad = "";
        llenarCboProducto();
    }
    private void cargarTablFactxfechaActual(){
        try {
            lstTblFactura = new ArrayList<>();
            DaoFacturacion daofactura = new DaoFacturacion();
            lstTblFactura = daofactura.getFacturasxFecha(fecha);
        } catch (Exception ex) {
            Logger.getLogger(MbVFacturacionCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarCboUsuarios() {
        this.lstThemeUsuarios = new ArrayList<ClsEmpleado>();
        try {
            DaoTCliente daoCliente = new DaoTCliente();

            List<TbPersona> lstCliente = daoCliente.getCliente();
            if(this.lstThemeUsuarios.size() > 0)
                this.lstThemeUsuarios.clear();
            
            this.lstThemeUsuarios.add(new ClsEmpleado("-1", "Ninguno", "Ninguno"));
            for (TbPersona cliente : lstCliente) {
                this.lstThemeUsuarios.add(new ClsEmpleado(cliente.getCedula(), 
                        cliente.getCedula()+" - "+cliente.getApellidos() + " " + cliente.getNombres(), 
                        cliente.getApellidos() + " " + cliente.getNombres()));
            }
        } catch (Exception ex) {

        }
    }
    public void llenarCboProveedor() {
        this.lstThemeUsuarios = new ArrayList<ClsEmpleado>();
        try {
            DaoTCliente daoCliente = new DaoTCliente();

            List<TbPersona> lstCliente = daoCliente.getProveedor();
            if(this.lstThemeUsuarios.size() > 0)
                this.lstThemeUsuarios.clear();
            
            this.lstThemeUsuarios.add(new ClsEmpleado("-1", "Ninguno", "Ninguno"));
            for (TbPersona cliente : lstCliente) {
                this.lstThemeUsuarios.add(new ClsEmpleado(cliente.getCedula(), 
                        cliente.getCedula()+" - "+cliente.getApellidos() + " " + cliente.getNombres(), 
                        cliente.getApellidos() + " " + cliente.getNombres()));
            }
        } catch (Exception ex) {

        }
    }
    public void llenarCboProducto() {
        this.lstThemeProductos = new ArrayList<ClsProducto>();
        try {
            DaoProducto daoProducto = new DaoProducto();
            TbInventario tbInv = new TbInventario();
            Double iva = 0.0;
            List<TbProducto> lstProducto = daoProducto.getProducto();
            if(this.lstThemeProductos.size() > 0)
                this.lstThemeProductos.clear();
            
            this.lstThemeProductos.add(new ClsProducto("-1", "Ninguno", 0.0, 0.0,0.0,"",0));
            for (TbProducto prod : lstProducto) {
                tbInv = daoProducto.getStockProducto(prod.getId());
                stock = tbInv.getStock();
                iva = (double)prod.getTbTipotasaiva().getValor();
                this.lstThemeProductos.add(new ClsProducto(prod.getId(), 
                        prod.getId() +" - "+prod.getNombre(), //Codigo + nombre del producto
                        prod.getPrecio2().doubleValue(),//Pvp
                        prod.getPrecio1().doubleValue(),//Costo
                        iva,//iva
                        prod.getNombre(),//Nombre del producto
                        stock));//stock
            }
        } catch (Exception ex) {

        }
    }
    
    public void registrarFactura(){
        try {
            DaoFacturacion daoFactura = new DaoFacturacion();
            tbFactura.setTbPersona(tbPersona);
            tbFactura.setTbTipopago(tbTipopago);
            TbUsuarios usuario = (TbUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            tbFactura.setTbUsuarios(usuario);
            tbFactura.setFecha(fecha);
            if (this.themeUsuarios != null) {
                tbPersona.setCedula(this.themeUsuarios.getId());
            }
            msg = daoFactura.registrarFactura(tbFactura, lstFactura);
        } catch (Exception ex) {

        }

        if (msg) {
            mensajesOk("Datos procesados correctamente");
            vaciarCajas();
        } else {
            mensajesError("Error al procesar los Datos");
        }

    }
    
    public void registrarCompra(){
        try {
            DaoFacturacion daoFactura = new DaoFacturacion();
            TbInventario inventario = new TbInventario();
            inventario.setFecharegistro(fecha);
            if (this.themeUsuarios != null) {
                inventario.setProveedorid(this.themeUsuarios.getId());
                inventario.setProveedornombres(this.themeUsuarios.getDisplayName());
            }
            msg = daoFactura.registrarCompra(inventario, lstFactura);
        } catch (Exception ex) {

        }

        if (msg) {
            mensajesOk("Datos procesados correctamente");
            vaciarCajas();
        } else {
            mensajesError("Error al procesar los Datos");
        }

    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    public List<SelectItem> getLstTipoPago() {
        return lstTipoPago;
    }

    public void setLstTipoPago(List<SelectItem> lstTipoPago) {
        this.lstTipoPago = lstTipoPago;
    }
    
    public TbPersona getTbPersona() {
        return tbPersona;
    }

    public void setTbPersona(TbPersona tbPersona) {
        this.tbPersona = tbPersona;
    }

    public TbFactura getTbFactura() {
        return tbFactura;
    }

    public void setTbFactura(TbFactura tbFactura) {
        this.tbFactura = tbFactura;
    }

    public TbProducto getTbProducto() {
        return tbProducto;
    }

    public void setTbProducto(TbProducto tbProducto) {
        this.tbProducto = tbProducto;
    }

    public TbDetallefactura getTbDetallefactura() {
        return tbDetallefactura;
    }

    public void setTbDetallefactura(TbDetallefactura tbDetallefactura) {
        this.tbDetallefactura = tbDetallefactura;
    }

    public TbTipopago getTbTipopago() {
        return tbTipopago;
    }

    public void setTbTipopago(TbTipopago tbTipopago) {
        this.tbTipopago = tbTipopago;
    }

    public List<TbFactura> getLstTblFactura() {
        return lstTblFactura;
    }

    public void setLstTblFactura(List<TbFactura> lstTblFactura) {
        this.lstTblFactura = lstTblFactura;
    }

    public ClsFactura getClsFactura() {
        return clsFactura;
    }

    public void setClsFactura(ClsFactura clsFactura) {
        this.clsFactura = clsFactura;
    }

    public List<ClsFactura> getLstFactura() {
        return lstFactura;
    }

    public void setLstFactura(List<ClsFactura> lstFactura) {
        this.lstFactura = lstFactura;
    }

    public Double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public Double getTarifa0() {
        return tarifa0;
    }

    public void setTarifa0(Double tarifa0) {
        this.tarifa0 = tarifa0;
    }

    public Double getTarifa14() {
        return tarifa14;
    }

    public void setTarifa14(Double tarifa14) {
        this.tarifa14 = tarifa14;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClsEmpleado getThemeUsuarios() {
        return themeUsuarios;
    }

    public void setThemeUsuarios(ClsEmpleado themeUsuarios) {
        this.themeUsuarios = themeUsuarios;
    }

    public List<ClsEmpleado> getLstThemeUsuarios() {
        return lstThemeUsuarios;
    }

    public void setLstThemeUsuarios(List<ClsEmpleado> lstThemeUsuarios) {
        this.lstThemeUsuarios = lstThemeUsuarios;
    }

    public ClsProducto getThemeProductos() {
        return themeProductos;
    }

    public void setThemeProductos(ClsProducto themeProductos) {
        this.themeProductos = themeProductos;
    }

    public List<ClsProducto> getLstThemeProductos() {
        return lstThemeProductos;
    }

    public void setLstThemeProductos(List<ClsProducto> lstThemeProductos) {
        this.lstThemeProductos = lstThemeProductos;
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
        tbPersona = new TbPersona();
        tbFactura = new TbFactura();
        tbProducto = new TbProducto();
        tbDetallefactura = new TbDetallefactura();
        tbTipopago = new TbTipopago();
        
        Subtotal = 0.0;
        tarifa0= 0.0;
        tarifa14= 0.0;
        Total= 0.0;
    }
    public void onRowEditFactura(RowEditEvent event) {
        try {
//            DaoTConfiguracion daoConfig = new DaoTConfiguracion();
//            tblImpuesto = ((TbTipotasaiva) event.getObject());
//            msg = daoConfig.registrarImpuersto(tblImpuesto);
//            if (msg) {
//                mensajesOk("Datos procesados correctamente");
//                // vaciarCajas();
//                cargarTablaImpuesto();
//            } else {
//                mensajesError("Error al procesar los Datos");
//            }
        } catch (Exception ex) {
            Logger.getLogger(MbVEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancelFactura(RowEditEvent event) {

    }
}
