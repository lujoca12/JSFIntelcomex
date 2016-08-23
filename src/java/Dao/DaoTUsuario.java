/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceUsuario;
import Pojo.TbDetallePermiso;
import Pojo.TbPersona;
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
public class DaoTUsuario implements InterfaceUsuario {

    private Session sesion;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    @Override
    public boolean registrar(TbUsuarios tUsuario) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tUsuario);
            //Para crear usuarios de bd
//            String sql = String.format("create user " + tUsuario.getLogin()+ " superuser password '" + tUsuario.getPass()+ "' ;");
//            sesion.createSQLQuery(sql).executeUpdate();

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
            System.out.println(e.toString());
        }

        return band;
    }

    @Override
    public List<TbUsuarios> getTodosUsuarios(boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String estado = "";
        if (mostrar) {
            estado = "0";
        } else {
            estado = "1";
        }
        String hql = "FROM TbUsuarios user inner join fetch user.tbTipoUsuario tuser where user.estado='" + estado + "' order by user.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<TbUsuarios> lstUsuarios = (List<TbUsuarios>) query.list();
        sesion.close();
        return lstUsuarios;
    }

    @Override
    public TbUsuarios getUsuario(String idUsuario) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "FROM TbUsuarios user where user.cedula=" + idUsuario + " and user.estado='1'";
        Query query = sesion.createQuery(hql);
        TbUsuarios usuario = (TbUsuarios) query.uniqueResult();
        sesion.close();
        return usuario;
    }

    @Override
    public TbUsuarios getUsuarioEdicion(String idUsuario) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "FROM TbUsuarios user where user.cedula=" + idUsuario + "";
        Query query = sesion.createQuery(hql);
        TbUsuarios usuario = (TbUsuarios) query.uniqueResult();
        sesion.close();
        return usuario;
    }

    @Override
    public boolean update(TbUsuarios tUsuario) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tUsuario);            
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
    public List<TbUsuarios> getDocentes(boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String estado = "";
        if (mostrar) {
            estado = "0";
        } else {
            estado = "1";
        }
        String hql = "from TbUsuarios as u inner join fetch u.tbTipoUsuario as tu where tu.descripcion like '%empleado%' and "
                + "u.estado='" + estado + "' order by u.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<TbUsuarios> lstUsuarios = (List<TbUsuarios>) query.list();
        sesion.close();
        return lstUsuarios;
    }

    @Override
    public boolean verificarUsuarioNick(String nick) throws Exception {
        boolean band = false;

        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from TbUsuarios as u where u.login='" + nick + "' and u.estado='1'";
        Query query = sesion.createQuery(hql);
        List<TbUsuarios> lstUsuarios = (List<TbUsuarios>) query.list();
        if (!lstUsuarios.isEmpty()) {
            band = false;
        } else {
            band = true;
        }
        sesion.close();

        return band;
    }
    @Override
    public boolean registrarPersona(TbPersona tPersona) throws Exception {
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
    public List<TbPersona> getPersonas() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from TbPersona p\n"
                + "inner join fetch p.cargo";
        Query query = sesion.createQuery(hql);
        List<TbPersona> lstUsuarios = (List<TbPersona>) query.list();
        sesion.close();
        return lstUsuarios;
    }

}
