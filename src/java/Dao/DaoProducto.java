/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceProducto;
import Pojo.TbInventario;
import Pojo.TbProducto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author server
 */
public class DaoProducto implements InterfaceProducto {
    
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
    public boolean registrarProducto(TbProducto tProducto) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tProducto);

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
    public List<TbProducto> getProducto() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbProducto as p inner join fetch p.tbTipotasaiva iva inner join fetch p.tbTipounidadmedida undMedida "
                + "inner join fetch p.tbTipoclasificacion clasif inner join fetch p.tbTipoalmacenes almac inner join fetch p.tbPersona person  order by p.nombre asc";
        Query query = sesion.createQuery(hql);
        List<TbProducto> producto= (List<TbProducto>) query.list();
        sesion.close();
        return producto;
    }
    public TbInventario getStockProducto(String idProducto) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbInventario as inv inner join fetch inv.tbProducto p where p.id ='"+idProducto+"' and inv.stock > 0 order by inv.id desc";
        Query query = sesion.createQuery(hql);
        List<TbInventario> inv= (List<TbInventario>) query.list();
        sesion.close();
        
        return inv.size() > 0 ? inv.get(0):null;
    }

    @Override
    public List<TbProducto> getProducto(String idProducto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProducto(TbProducto tProducto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
