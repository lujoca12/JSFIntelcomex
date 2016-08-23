/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceParametro;
import Pojo.TbParametro;
import Pojo.TbParametrodetalle;
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
public class DaoTParametro implements InterfaceParametro {
    
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
    public boolean registrarParametro(TbParametro tParametro) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tParametro);

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
    public List<TbParametro> getParametro() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbParametro as p order by p.categoria asc";
        Query query = sesion.createQuery(hql);
        List<TbParametro> parametro= (List<TbParametro>) query.list();
        sesion.close();
        return parametro;
    }

    @Override
    public boolean registrarParametroDetalle(TbParametrodetalle tParametroDetalle) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tParametroDetalle);

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
    public List<TbParametrodetalle> getParametroDetalle(String descripcionParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateParametro(TbParametro tParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateParametrodetalle(TbParametrodetalle tParametroDetalle) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
