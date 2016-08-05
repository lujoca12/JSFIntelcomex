/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Pojo.TbUsuarios;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class UsuarioDAO {

    private Session sesion;
    private Transaction tx;

    private void iniciaOperacion() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (HibernateException ex) {

        }

    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public TbUsuarios verificarDatos(TbUsuarios usuario) throws Exception {
        TbUsuarios us = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM TbUsuarios user inner join fetch user.tbTipoUsuario tuser WHERE login='" + usuario.getLogin()+ "' and "
                    + "pass='" + usuario.getPass()+ "' and user.estado = '1'";
            Query query = sesion.createQuery(hql);
            if (!query.list().isEmpty()) {
                us = (TbUsuarios) query.list().get(0);
            }
            sesion.close();
        } catch (Exception ex) {
            throw ex;
        }
        return us;
    }

    public boolean existeNick(String Nick) throws Exception {
        TbUsuarios us = null;
        boolean existe = false;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM TbUsuarios u where u.login='" + Nick + "' ";
            Query query = sesion.createQuery(hql);
            us = (TbUsuarios) query.uniqueResult();
            existe = us != null;
            sesion.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return existe;
    }

    public boolean insertar(TbUsuarios u,String NickViejo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(u);
            String sql = String.format("alter user "+NickViejo+" rename to "+u.getLogin()+" ; "
                    + "alter user "+u.getLogin()+" password '"+u.getPass()+"'");
            sesion.createSQLQuery(sql).executeUpdate();
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

}
