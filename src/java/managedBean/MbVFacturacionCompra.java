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
import java.math.BigDecimal;
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
    private String costo;
    
    private boolean msg;
    private Date fecha;
    private int cont = 0;
    
    private ClsEmpleado themeUsuarios;
    private List<ClsEmpleado> lstThemeUsuarios;
    
    private ClsEmpleado themeProveedor;
    private List<ClsEmpleado> lstThemeProveedor;
    
    private ClsProducto themeProductos;
    private List<ClsProducto> lstThemeProductos;
    
    private ClsProducto themeProductosCompra;
    private List<ClsProducto> lstThemeProductosCompra;
    
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
        llenarCboProductoCompras();
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
        double temp = 0.0;boolean band = false;
        if(this.themeUsuarios == null || this.themeProductos == null || tbTipopago.getId() <= 0 ){
                mensajesError("Faltan campos por seleccionar");
                return;
            } else {
                
                
                //Condicion para saber si hay mas de un registro en la lista
                if(lstFactura.size() > 0){
                    if (Double.parseDouble(cantidad) > 0) {
                        for (int i = 0; i < lstFactura.size(); i++) {
                            //Condicion para comparar productos repetidos y actualizarlos
                            if (lstFactura.get(i).getIdProducto().equals(this.themeProductos.getId().toString())) {
                                if ((lstFactura.get(i).getCantidad() + Double.parseDouble(cantidad)) > this.themeProductos.getCantidad()) {
                                    temp = lstFactura.get(i).getCantidad() + (this.themeProductos.getCantidad() - lstFactura.get(i).getCantidad());
                                    lstFactura.get(i).setCantidad(temp);
                                    lstFactura.get(i).setTotalxProducto(lstFactura.get(i).getCantidad() * lstFactura.get(i).getPvp());
                                    band = true;
                                    mensajesError("El Stock maximo es "+temp);
                        
                                } else {
                                    temp = lstFactura.get(i).getCantidad() + Double.parseDouble(cantidad);
                                    lstFactura.get(i).setCantidad(temp);
                                    lstFactura.get(i).setTotalxProducto(lstFactura.get(i).getCantidad() * lstFactura.get(i).getPvp());
                                    band = true;
                                }
                            }
                        }
                    }
                }
                if(band == false){
                    if (Double.parseDouble(cantidad) > 0) {
                        if (Double.parseDouble(cantidad) > this.themeProductos.getCantidad()) {
                            temp = this.themeProductos.getCantidad();
                            mensajesError("El Stock maximo es " + temp);
                        } else {
                            temp = Double.parseDouble(cantidad);
                        }
                        lstFactura.add(new ClsFactura((cont + 1),
                                this.themeProductos.getId(),
                                this.themeProductos.getNombre(),
                                temp,
                                (this.themeProductos.getPvp() * temp),
                                this.themeProductos.getCosto(),
                                0.0,
                                this.themeProductos.getPvp(),
                                this.themeProductos.getIva(),
                                this.themeProductos.getCantidad(),
                                this.themeProductos.getIdInventario(),
                                this.themeProductos.getCantMinima(),
                                this.themeProductos.getPrecioStock(),
                                this.themeProductos.getFecharegistro(),
                                this.themeProductos.getCantidadAnterior(),
                                this.themeProductos.getIdProveedor(),
                                this.themeProductos.getProveedor()));
                        cont++;
                    }
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
                        tarifa14 += (this.themeProductos.getPvp() * temp) - ((this.themeProductos.getPvp() * temp) / 1.12);
                        Total += (this.themeProductos.getPvp() * temp);
                        Subtotal = Total / 1.12;
                    } else if (this.themeProductos.getIva() == 14.0) {
                        tarifa14 += (this.themeProductos.getPvp() * temp) - ((this.themeProductos.getPvp() * temp) / 1.14);
                        Total += (this.themeProductos.getPvp() * temp);
                        Subtotal = Total / 1.14;
                    } else if (this.themeProductos.getIva() == 0) {
                        tarifa0 += this.themeProductos.getPvp();
                        Total += tarifa0;
                    }
                    
                }
                
                
                
            
        }
            
        //}else{
            
        //}
        vaciarEncabezadoFactura();
    }
    ///Agregando Productos a la tabla Compra
    public void agregarProductoCompra(){
        //if(lstFactura.size() <= 0){
        double temp = 0.0, costoPerson=0.0;
        boolean band = false;
        if(this.themeProveedor == null || this.themeProductosCompra == null){
                mensajesError("Faltan campos por seleccionar");
                return;
            } else {
                
                
                //Condicion para saber si hay mas de un registro en la lista
                if(lstFactura.size() > 0){
                    if (Double.parseDouble(cantidad) > 0) {
                        for (int i = 0; i < lstFactura.size(); i++) {
                            //Condicion para comparar productos repetidos y actualizarlos
                            if (lstFactura.get(i).getIdProducto().equals(this.themeProductosCompra.getId().toString())) {
                                temp = lstFactura.get(i).getCantidad() + Double.parseDouble(cantidad);
                                lstFactura.get(i).setCantidad(temp);
                                lstFactura.get(i).setTotalxProducto(lstFactura.get(i).getCantidad() * lstFactura.get(i).getCosto());
                                band = true;
                            }
                        }
                    }
                } 
                if(band == false){
                    if (Double.parseDouble(cantidad) > 0) {
                        if(costo.equals("")){
                            costoPerson = this.themeProductosCompra.getCosto();
                        
                    }else{
                            if (Double.parseDouble(costo) > 0) {
                                costoPerson = Double.parseDouble(costo);
                            } else {
                                costoPerson = this.themeProductosCompra.getCosto();
                            }    
                        }
                            
                        
                    lstFactura.add(new ClsFactura((cont + 1),
                        this.themeProductosCompra.getId(),
                        this.themeProductosCompra.getNombre(),
                        Double.parseDouble(cantidad),
                        (costoPerson * Double.parseDouble(cantidad)),
                        costoPerson,
                        0.0,
                        this.themeProductosCompra.getPvp(),
                        this.themeProductosCompra.getIva(),
                        this.themeProductosCompra.getCantidad(),//Stock
                        this.themeProductosCompra.getIdInventario(),
                        this.themeProductosCompra.getCantMinima(),
                        this.themeProductosCompra.getPrecioStock(),
                        this.themeProductosCompra.getFecharegistro(),
                        0,
                        "",
                        ""));
                    }
                    cont++;
                }
                
                if(band){
                    tarifa14 = 0.0;Total=0.0;Subtotal = 0.0;tarifa0=0.0;
                    for (int i = 0; i < lstFactura.size(); i++) {
                        
                        if (lstFactura.get(i).getIva() == 12.0) {
                            tarifa14 += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) / 1.12);
                            Total += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad());
                            Subtotal = Total / 1.12;
                        } else if (lstFactura.get(i).getIva() == 14.0) {
                            tarifa14 += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) / 1.14);
                            Total += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad());
                            Subtotal = Total / 1.14;
                        } else if (lstFactura.get(i).getIva() == 0) {
                            tarifa0 += lstFactura.get(i).getCosto();
                            Total += tarifa0;
                        }
                    }
                }else{
                    if (this.themeProductosCompra.getIva() == 12.0) {
                        tarifa14 += (costoPerson * Double.parseDouble(cantidad)) - ((costoPerson * Double.parseDouble(cantidad)) / 1.12);
                        Total += (costoPerson * Double.parseDouble(cantidad));
                        Subtotal = Total / 1.12;
                    } else if (this.themeProductosCompra.getIva() == 14.0) {
                        tarifa14 += (costoPerson * Double.parseDouble(cantidad)) - ((costoPerson * Double.parseDouble(cantidad)) / 1.14);
                        Total += (costoPerson * Double.parseDouble(cantidad));
                        Subtotal = Total / 1.14;
                    } else if (this.themeProductosCompra.getIva() == 0) {
                        tarifa0 += costoPerson;
                        Total += tarifa0;
                    }
                    
                }
                
                
                
            
        }
            
        //}else{
            band = false;
        //}
        vaciarEncabezadoCompra();
    }
    private void vaciarEncabezadoFactura(){
        cantidad = "";
        llenarCboProducto();
        costo = "";
    }
    private void vaciarEncabezadoCompra(){
        cantidad = "";
        costo = "";
        llenarCboProductoCompras();
        
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
        this.lstThemeProveedor = new ArrayList<ClsEmpleado>();
        try {
            DaoTCliente daoCliente = new DaoTCliente();

            List<TbPersona> lstCliente = daoCliente.getProveedor();
            if(this.lstThemeProveedor.size() > 0)
                this.lstThemeProveedor.clear();
            
            this.lstThemeProveedor.add(new ClsEmpleado("-1", "Ninguno", "Ninguno"));
            for (TbPersona cliente : lstCliente) {
                this.lstThemeProveedor.add(new ClsEmpleado(cliente.getCedula(), 
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
            BigDecimal bigdec = null;
            this.lstThemeProductos.add(new ClsProducto("-1", "Ninguno", 0.0, 0.0,0.0,"",0,0,0,bigdec,null,0,"",""));
            for (TbProducto prod : lstProducto) {
                tbInv = daoProducto.getStockProducto(prod.getId());
                stock = tbInv.getStockactual();
                iva = (double)prod.getTbTipotasaiva().getValor();
                this.lstThemeProductos.add(new ClsProducto(prod.getId(), 
                        prod.getId() +" - "+prod.getNombre(), //Codigo + nombre del producto
                        prod.getPrecio2().doubleValue(),//Pvp
                        prod.getPrecio1().doubleValue(),//Costo
                        iva,//iva
                        prod.getNombre(),//Nombre del producto
                        stock,//stock
                        tbInv.getId(),
                        tbInv.getCantidadMinima() == null ?0:tbInv.getCantidadMinima(),
                        tbInv.getPrecioStock(),
                        tbInv.getFecharegistro(),
                        tbInv != null ? tbInv.getStock():0,
                        tbInv != null ? tbInv.getProveedorid():null,
                        tbInv != null ? tbInv.getProveedornombres():null));
            }
        } catch (Exception ex) {

        }
    }
    
    public void llenarCboProductoCompras() {
        this.lstThemeProductosCompra = new ArrayList<ClsProducto>();
        int temp = 0;
        try {
            DaoProducto daoProducto = new DaoProducto();
            TbInventario tbInv = new TbInventario();
            Double iva = 0.0;
            List<TbProducto> lstProducto = daoProducto.getProducto();
            if(this.lstThemeProductosCompra.size() > 0)
                this.lstThemeProductosCompra.clear();
            BigDecimal bigdec = null;
            this.lstThemeProductosCompra.add(new ClsProducto("-1", "Ninguno", 0.0, 0.0,0.0,"",0,0,0,bigdec,null,0,"",""));
            for (TbProducto prod : lstProducto) {
                tbInv = daoProducto.getStockProducto(prod.getId());
                if(tbInv != null){
                    stock = tbInv.getStockactual();
                }else if(tbInv == null){
                    stock = 0;
                }
                
                iva = (double)prod.getTbTipotasaiva().getValor();
                
                if(tbInv != null){
                  if(tbInv.getCantidadMinima() == null){
                      temp=0;
                  }else{
                      temp=tbInv.getCantidadMinima();
                  }
                }else
                    temp=0;
                this.lstThemeProductosCompra.add(new ClsProducto(prod.getId(), 
                        prod.getId() +" - "+prod.getNombre(), //Codigo + nombre del producto
                        prod.getPrecio2().doubleValue(),//Pvp
                        prod.getPrecio1().doubleValue(),//Costo
                        iva,//iva
                        prod.getNombre(),//Nombre del producto
                        stock,//stock
                        tbInv != null ? tbInv.getId():0,
                        temp,
                        tbInv != null ? tbInv.getPrecioStock():bigdec,
                        tbInv != null ? tbInv.getFecharegistro():null,
                        tbInv != null ? tbInv.getStock():0,
                        tbInv != null ? tbInv.getProveedorid():null,
                        tbInv != null ? tbInv.getProveedornombres():null));
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
            if (this.themeProveedor != null) {
                inventario.setProveedorid(this.themeProveedor.getId());
                inventario.setProveedornombres(this.themeProveedor.getDisplayName());
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

    public ClsProducto getThemeProductosCompra() {
        return themeProductosCompra;
    }

    public void setThemeProductosCompra(ClsProducto themeProductosCompra) {
        this.themeProductosCompra = themeProductosCompra;
    }

    public List<ClsProducto> getLstThemeProductosCompra() {
        return lstThemeProductosCompra;
    }

    public void setLstThemeProductosCompra(List<ClsProducto> lstThemeProductosCompra) {
        this.lstThemeProductosCompra = lstThemeProductosCompra;
    }
    
    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
    
    public ClsEmpleado getThemeProveedor() {
        return themeProveedor;
    }

    public void setThemeProveedor(ClsEmpleado themeProveedor) {
        this.themeProveedor = themeProveedor;
    }

    public List<ClsEmpleado> getLstThemeProveedor() {
        return lstThemeProveedor;
    }

    public void setLstThemeProveedor(List<ClsEmpleado> lstThemeProveedor) {
        this.lstThemeProveedor = lstThemeProveedor;
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
        
        lstFactura = new ArrayList<>();
        llenarCboProveedor();
        llenarCboUsuarios();
        llenarCboProducto();
        llenarCboProductoCompras();
        
        Subtotal = 0.0;
        tarifa0= 0.0;
        tarifa14= 0.0;
        Total= 0.0;
        cont = 0;
        cantidad = "";
        costo = "";
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
    public void onDelete(ClsFactura factura) {
        tarifa14 = 0.0;Total=0.0;Subtotal = 0.0;tarifa0=0.0;
        if (lstFactura.size() > 0) {
            for (int i = 0; i < lstFactura.size(); i++) {
                if(lstFactura.get(i).getIdProducto().equals(factura.getIdProducto())){
                    lstFactura.remove(i);
                }
                
                if (lstFactura.size() <= 0) {
                    cont = i;
                    return;
                } else {
                    lstFactura.get(i).setNumero((i + 1));
                    cont = (i + 1);
                }
                if (lstFactura.get(i).getIva() == 12.0) {
                    tarifa14 += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) / 1.12);
                    Total += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad());
                    Subtotal = Total / 1.12;
                } else if (lstFactura.get(i).getIva() == 14.0) {
                    tarifa14 += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) / 1.14);
                    Total += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad());
                    Subtotal = Total / 1.14;
                } else if (lstFactura.get(i).getIva() == 0) {
                    tarifa0 += lstFactura.get(i).getCosto();
                    Total += tarifa0;
                }
            }
        }
    }
    
    public void onDeleteTblCompra(ClsFactura factura) {
        tarifa14 = 0.0;Total=0.0;Subtotal = 0.0;tarifa0=0.0;
        if (lstFactura.size() > 0) {
            for (int i = 0; i < lstFactura.size(); i++) {
                if(lstFactura.get(i).getIdProducto().equals(factura.getIdProducto())){
                    lstFactura.remove(i);
                }
                
                if (lstFactura.size() <= 0) {
                    cont = i;
                    return;
                } else {
                    lstFactura.get(i).setNumero((i + 1));
                    cont = (i + 1);
                }
                if (lstFactura.get(i).getIva() == 12.0) {
                    tarifa14 += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) / 1.12);
                    Total += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad());
                    Subtotal = Total / 1.12;
                } else if (lstFactura.get(i).getIva() == 14.0) {
                    tarifa14 += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) - ((lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad()) / 1.14);
                    Total += (lstFactura.get(i).getCosto() * lstFactura.get(i).getCantidad());
                    Subtotal = Total / 1.14;
                } else if (lstFactura.get(i).getIva() == 0) {
                    tarifa0 += lstFactura.get(i).getCosto();
                    Total += tarifa0;
                }
            }
        }
    }
}
