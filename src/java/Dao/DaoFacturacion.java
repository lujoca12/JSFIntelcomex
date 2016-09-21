/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Pojo.TbFactura;
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
    
    public boolean registrarFactura(TbFactura factura ){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(factura);

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
       
        String hql="from TbFactura f inner join fetch f.tbDetallefacturas df inner join fetch f.tbPersona persona "
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
