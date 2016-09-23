/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Clases.ClsFactura;
import Pojo.TbDetallefactura;
import Pojo.TbFactura;
import Pojo.TbInventario;
import Pojo.TbProducto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
/**
 *
 * @author server
 */
public class DaoFacturacion {
    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        }catch(HibernateException ex){
            
        }
        
    }
     
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    public boolean registrarFactura(TbFactura factura, List<ClsFactura> lstFactura ){
        boolean band = false;
        try {
            iniciaOperacion();
            //Primero guardo la factura
            sesion.saveOrUpdate(factura);
            tx.commit();
            sesion.close();
            iniciaOperacion();
            TbDetallefactura detalleFact;
            TbProducto producto;
            BigDecimal bigdec;
            TbInventario inventario;
            for (int i = 0; i < lstFactura.size(); i++) {
                detalleFact = new TbDetallefactura();
                producto = new TbProducto();
                detalleFact.setTbFactura(factura);
                detalleFact.setTbDetallefacturacol("usuario "+factura.getTbUsuarios().getApellidos());
                bigdec = new BigDecimal(lstFactura.get(i).getTotalxProducto());
                detalleFact.setPrecioDetalle(bigdec);
                producto.setId(lstFactura.get(i).getIdProducto());
                detalleFact.setTbProducto(producto);
                bigdec = new BigDecimal(lstFactura.get(i).getIva());
                detalleFact.setIva(bigdec);
                bigdec = new BigDecimal(lstFactura.get(i).getPvp());
                detalleFact.setPrecioxund(bigdec);
                sesion.saveOrUpdate(detalleFact);
                
            }
            tx.commit();
            sesion.close();
            band = actualizarStockProducto(lstFactura);
            
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    public boolean actualizarStockProducto(List<ClsFactura> lstFactura){
        boolean band = false;
        try {
            iniciaOperacion();
            TbProducto producto;
            TbInventario inventario;
            for (int i = 0; i < lstFactura.size(); i++) {
                inventario = new TbInventario();
                producto = new TbProducto();
                producto.setId(lstFactura.get(i).getIdProducto());
                inventario.setId(lstFactura.get(i).getIdInventario());
                inventario.setStock(lstFactura.get(i).getCantidadAnterior());
                inventario.setStockactual(lstFactura.get(i).getStock() - lstFactura.get(i).getCantidad().intValue());
                inventario.setTbProducto(producto);
                inventario.setCantidadMinima(lstFactura.get(i).getCantMinima());
                inventario.setPrecioStock(lstFactura.get(i).getPrecioStock());
                inventario.setFecharegistro(lstFactura.get(i).getFecharegistro());
                inventario.setProveedorid(lstFactura.get(i).getIdProveedor());
                inventario.setProveedornombres(lstFactura.get(i).getProveedor());
                sesion.saveOrUpdate(inventario);
            }
            tx.commit();
            sesion.close();

            band = true;
            

        } catch (Exception e) {
            tx.rollback();
            band = false;
        }

        return band;
    }
    public List<TbInventario> getPrductoInventario(int id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbInventario inv inner join fetch inv.tbProducto p";
       
        Query query = sesion.createQuery(hql);
        List<TbInventario> lst=(List<TbInventario>) query.list();
        sesion.close();
        return lst; 
    }
    public boolean registrarCompra(TbInventario inventario, List<ClsFactura> lstFactura ){
        boolean band = false;
        try {
            iniciaOperacion();
           String nombreProveedor = inventario.getProveedornombres();
           String idProveedor = inventario.getProveedorid();
           Date fecha = inventario.getFecharegistro();
            
            TbProducto producto;
            BigDecimal bigdec;
            for (int i = 0; i < lstFactura.size(); i++) {
                inventario = new TbInventario();
                producto = new TbProducto();
                producto.setId(lstFactura.get(i).getIdProducto());
                //inventario.setId(lstFactura.get(i).getIdInventario());
                inventario.setTbProducto(producto);
                inventario.setStock(lstFactura.get(i).getCantidad().intValue());
                inventario.setStockactual(lstFactura.get(i).getCantidad().intValue()+lstFactura.get(i).getStock());
                inventario.setCantidadMinima(5);
                bigdec = new BigDecimal(lstFactura.get(i).getCosto());
                inventario.setPrecioStock(bigdec);
                inventario.setFecharegistro(fecha);
                inventario.setProveedorid(idProveedor);
                inventario.setProveedornombres(nombreProveedor);
                sesion.saveOrUpdate(inventario);
            }
            tx.commit();
            sesion.close();
            band = true;
            
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    
    
    public List<TbFactura> getFacturasxFecha(Date fecha){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbFactura f inner join fetch f.tbDetallefacturas df inner join fetch f.tbPersona persona inner join fetch"
                + "f.tbTipopago tipoPago inner join fetch f.tbUsuarios user where f.fecha = '"+fecha+"' order by f.fecha asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbFactura> lst=(List<TbFactura>) query.list();
        sesion.close();
        return lst; 
    }
    
    public List<TbFactura> getAllFacturas(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbFactura f inner join fetch f.tbDetallefacturas df inner join fetch f.tbPersona persona "
                + "f.tbTipopago tipoPago inner join fetch f.tbUsuarios user order by f.fecha asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbFactura> lst=(List<TbFactura>) query.list();
        sesion.close();
        return lst; 
    }
    
    
}
