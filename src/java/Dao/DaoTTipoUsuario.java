/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceTipoUsuario;
import Pojo.TbTipousuario;
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
public class DaoTTipoUsuario implements InterfaceTipoUsuario{
    
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
    public boolean registrar(TbTipousuario tTipoUsuario) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            //sesion.save(tPromocion);

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
    public List<TbTipousuario> getTodosTipoUsuarios() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbTipousuario as t order by t.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<TbTipousuario> tipoUsuario= (List<TbTipousuario>) query.list();
        sesion.close();
        return tipoUsuario;
    }

    @Override
    public boolean update(TbTipousuario tTipoUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TbTipousuario getTipoUsuarios(String rol) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbTipousuario as t where t.descripcion like '%"+rol+"%' order by t.descripcion asc";
        Query query = sesion.createQuery(hql);
        TbTipousuario tipoUsuario= (TbTipousuario) query.uniqueResult();
        sesion.close();
        return tipoUsuario;
    }
    
}
