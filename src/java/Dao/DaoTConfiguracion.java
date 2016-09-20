/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.TbTipoalmacenes;
import Pojo.TbTipopago;
import Pojo.TbTipopedido;
import Pojo.TbTipotasaiva;
import Pojo.TbTipounidadmedida;
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
public class DaoTConfiguracion {
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
    
    public boolean registrarTipoPago(TbTipopago tTipoPago ){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tTipoPago);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public boolean registrarTipoAlmacenes(TbTipoalmacenes tAlmacenes){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tAlmacenes);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public boolean registrarTipoPedido(TbTipopedido tTipoPedido){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tTipoPedido);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public boolean registrarImpuersto(TbTipotasaiva tImpuesto){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tImpuesto);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public boolean registrarUnidadMedida(TbTipounidadmedida tUnidadMedida){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tUnidadMedida);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public List<TbTipopago> getTipoPago(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbTipopago p order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbTipopago> lst=(List<TbTipopago>) query.list();
        sesion.close();
        return lst; 
    }
    
    public List<TbTipoalmacenes> getTipoAlmacenes(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbTipoalmacenes p order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbTipoalmacenes> lst=(List<TbTipoalmacenes>) query.list();
        sesion.close();
        return lst; 
    }
    
    public List<TbTipopedido> getTipoPedido(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbTipopedido p order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbTipopedido> lst=(List<TbTipopedido>) query.list();
        sesion.close();
        return lst; 
    }
    
    public List<TbTipotasaiva> getImpuesto(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbTipotasaiva p order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbTipotasaiva> lst=(List<TbTipotasaiva>) query.list();
        sesion.close();
        return lst; 
    }
    
    public List<TbTipounidadmedida> getTipoUndMedida(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbTipounidadmedida p order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbTipounidadmedida> lst=(List<TbTipounidadmedida>) query.list();
        sesion.close();
        return lst; 
    }
}
