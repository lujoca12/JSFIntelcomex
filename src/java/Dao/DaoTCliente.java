/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceProveedor;
import Pojo.TbPersona;
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
public class DaoTCliente implements InterfaceProveedor{
    
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
    public boolean registrarProveedor(TbPersona tPersona) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPersona);

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
    public List<TbPersona> getProveedor() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbPersona as p order by p.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<TbPersona> persona= (List<TbPersona>) query.list();
        sesion.close();
        return persona;
    }

    @Override
    public List<TbPersona> getProveedor(String cedPersona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProveedor(TbPersona tPersona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
