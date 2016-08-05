/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceMenu;
import Pojo.TbDetallePermiso;
import Pojo.TbPermiso;
import Pojo.TbDetallePermiso;
import Pojo.TbPermiso;
import Pojo.TbUsuarios;
import Pojo.TbUsuarios;
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
public class DaoTMenu implements InterfaceMenu{

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
    public boolean registrar(TbPermiso tPermiso){
        
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPermiso);

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
    public List<TbPermiso> getTodosPermisos(boolean mostrar) throws Exception {
        //Metodo para obtener el menu de usuariosActualizado
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        String consulta = "";
        String estado = "";
        if(mostrar)
            estado = "";
        else
            estado = "where p.estado = '1'";
        
        String hql="from TbPermiso as p "+estado+" order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<TbPermiso> lstPermiso=(List<TbPermiso>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public TbPermiso getPermiso(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(TbPermiso tPermiso) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPermiso);

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
    public boolean updatePadres(TbPermiso tPermiso) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPermiso);

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
    public List<TbPermiso> getMenuNavxUsuarios(TbUsuarios usuario) throws Exception {
        this.sesion = null;
        this.tx = null;
        //int codigo = 1;
        iniciaOperacion();
        //from Usuario as u inner join  u.detallePermisos as dp where u.id=1
        //String hql = "FROM DetallePermiso";
        String hql = "from TbPermiso as p inner join fetch  p.tbDetallePermisos as dp where dp.tbUsuarios=:codigo and p.estado='1' and dp.estado='1' order by p.orden, p.descripcion asc";
        
        Query query = sesion.createQuery(hql).setString("codigo", usuario.getCedula());
        //query.setParameter("id", tx)
        List<TbPermiso> lstPermiso=(List<TbPermiso>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    public List<TbDetallePermiso> getEstadoPermisoUsuario(String usuarioId, int permisoId){
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        iniciaOperacion();
        String hql = "from TbDetallePermiso as dp where dp.usuario.id =:usuario and dp.permiso.id=:permiso";
        Query query = sesion.createQuery(hql);
        query.setString("usuario", usuarioId);
        query.setInteger("permiso", permisoId);
        List<TbDetallePermiso> lstDetalleP = (List<TbDetallePermiso>) query.list();
        sesion.close();
        
        return lstDetalleP;
    }

    @Override
    public List<TbDetallePermiso> getUltimoIdDetallePermiso() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from TbDetallePermiso as dp order by dp.id desc";
        Query query = sesion.createQuery(hql);
        List<TbDetallePermiso> lstDetallePermiso=(List<TbDetallePermiso>) query.list();
        sesion.close();
        
        return lstDetallePermiso;
    }

    @Override
    public List<TbPermiso> getPadres() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TbPermiso as p where p.padre =0 and p.estado='1' order by p.descripcion ASC";
        Query query = sesion.createQuery(hql);
        List<TbPermiso> lstPermiso=(List<TbPermiso>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    
}
