/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Pojo.TbCanton;
import Pojo.TbPais;
import Pojo.TbParroquia;
import Pojo.TbProvincia;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
/**
 *
 * @author chiti
 */
public class LocalizacionDao {
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
    
    public boolean registrarPaises(TbPais tPais){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPais);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public boolean registrarProvincia(TbProvincia tProvincia){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tProvincia);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public boolean registrarCanton(TbCanton tCanton){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tCanton);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public boolean registrarParroquia(TbParroquia tParroquia){
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tParroquia);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public List<TbPais> getPaises(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbPais p order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbPais> lst=(List<TbPais>) query.list();
        sesion.close();
        return lst; 
    }
    public TbParroquia getParroquia(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbParroquia p where p.id = '"+Id+"' order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbParroquia> lst=(List<TbParroquia>) query.list();
        TbParroquia pa = null;
        for(TbParroquia p : lst)
            pa=p;
        sesion.close();
        return pa; 
    }
    public String getNombrePais(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbPais p where p.id = '"+Id+"' order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbPais> lst=(List<TbPais>) query.list();
        String pais="" ;
        for(TbPais p : lst)
            pais=p.getNombre();
        sesion.close();
        return pais; 
    }
    public TbPais getPais(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbPais p where p.id = '"+Id+"' order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbPais> lst=(List<TbPais>) query.list();
        TbPais pais=null;
        for(TbPais p : lst)
            pais=p;
        sesion.close();
        return pais; 
    }
    public List<TbProvincia> getProvincias(String idPais){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TbProvincia p inner join fetch p.tbPais pais where pais.id='"+idPais+"' order by p.nombre asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbProvincia> lst=(List<TbProvincia>) query.list();
        sesion.close();
        return lst; 
    }
    public List<TbProvincia> getProvincias(int orden){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String condicion = "";
        //desordenada
        if(orden == 0)
            condicion = "";
        //ordenada
        else
            condicion = "order by p.nombre asc";
        
        String hql="from TbProvincia p inner join fetch p.tbPais pais "+condicion+" ";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TbProvincia> lst=(List<TbProvincia>) query.list();
        sesion.close();
        return lst; 
    }
    public List<TbCanton> getCantonProvicia(String IdProvincia) throws Exception {
        List<TbCanton> lst = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from TbCanton c inner join fetch c.tbProvincia p where p.id='"+IdProvincia+"' order by c.nombre asc";
            Query query = sesion.createQuery(hql);
            lst = (List<TbCanton>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lst;
    }
    
    public List<TbCanton> getCanton(int orden) throws Exception {
        List<TbCanton> lst = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String condicion = "";
            
            if(orden == 0)
                condicion = "";
            else
                condicion = "order by c.nombre asc";
            
            String hql = "from TbCanton c inner join fetch c.tbProvincia p inner join fetch p.tbPais pais "+condicion+"";
            Query query = sesion.createQuery(hql);
            lst = (List<TbCanton>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lst;
    }
    
     public List<TbParroquia> getParroquiaCanton(String IdCanton) throws Exception {
        List<TbParroquia> lst = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from TbParroquia p inner join fetch p.tbCanton c where c.id='"+IdCanton+"' order by p.nombre asc";
            Query query = sesion.createQuery(hql);
            lst = (List<TbParroquia>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lst;
    }
     
    public List<TbParroquia> getParroquia(int orden) throws Exception {
        List<TbParroquia> lst = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String condicion = "";
            if(orden == 0)
                condicion = "";
            else
                condicion = "order by p.nombre asc";
            
            String hql = "from TbParroquia p inner join fetch p.tbCanton c inner join fetch c.tbProvincia prov inner join fetch prov.tbPais pais "+condicion+"";
            Query query = sesion.createQuery(hql);
            lst = (List<TbParroquia>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lst;
    } 
    
}
