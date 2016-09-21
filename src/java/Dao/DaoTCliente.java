/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.TbPersona;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import Interface.InterfaceCliente;
import Pojo.TbTipopersona;

/**
 *
 * @author server
 */
public class DaoTCliente implements InterfaceCliente{
    
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

    @Override
    public boolean registrarCliente(TbPersona tPersona) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tPersona);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<TbPersona> getCliente() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbPersona as p inner join fetch p.tbTipoempresa tipo inner join fetch p.tbTipopersona tipoPersona inner join fetch p.tbParroquia parroq "
                + "where tipoPersona.descripcion like '%cliente%' order by p.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<TbPersona> persona= (List<TbPersona>) query.list();
        sesion.close();
        return persona;
    }
    
     @Override
    public List<TbPersona> getTblProveedor() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbPersona as p inner join fetch p.tbTipoempresa tipo inner join fetch p.tbTipopersona tipoPersona inner join fetch p.tbParroquia parroq "
                + "where tipoPersona.descripcion like '%proveed%' order by p.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<TbPersona> persona= (List<TbPersona>) query.list();
        sesion.close();
        return persona;
    }
    
    @Override
    public TbTipopersona getTipoCliente(boolean band) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String condicion = "";
        //Si bandera es true... Cliente
        if(band)
            condicion= "cliente";
        else
            condicion= "proveedor";
        String hql="from TbTipopersona as tp where descripcion like '%"+condicion+"%'";
        Query query = sesion.createQuery(hql);
        List<TbTipopersona> tipoPersona= (List<TbTipopersona>) query.list();
        sesion.close();
        return tipoPersona.get(0);
    }
    
    @Override
    public List<TbPersona> getProveedor() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbPersona as p inner join fetch p.tbTipopersona tp where tp.descripcion like '%proveedor%'";
        Query query = sesion.createQuery(hql);
        List<TbPersona> persona= (List<TbPersona>) query.list();
        sesion.close();
        return persona;
    }

    @Override
    public List<TbPersona> getCliente(String cedPersona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateCliente(TbPersona tPersona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
