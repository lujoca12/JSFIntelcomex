/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceDetallePermiso;
import Pojo.TbDetallePermiso;
import Pojo.TbPermiso;
import Pojo.TbUsuarios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Clases.Document;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.TreeNode;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTDetallePermiso implements InterfaceDetallePermiso{

    private Session sesion;
    private Transaction tx;
    private Document selectedDocPadre;
    private Document selectedDocHijo;
    
    private TbDetallePermiso dtp;
    private TbPermiso permiso = null;
    private TbUsuarios user = null;
    private int idDetPermHijo=0;
    private int idDetPermPadre=0;
    private int idPadre=0;
    private List<TbDetallePermiso> lstDetalleP= null;
    
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        }catch(HibernateException ex){
            
        }
    }

    public Document getSelectedDocPadre() {
        return selectedDocPadre;
    }

    public void setSelectedDocPadre(Document selectedDocPadre) {
        this.selectedDocPadre = selectedDocPadre;
    }

    public Document getSelectedDocHijo() {
        return selectedDocHijo;
    }

    public void setSelectedDocHijo(Document selectedDocHijo) {
        this.selectedDocHijo = selectedDocHijo;
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    @Override
    public String registrar(List<TreeNode> node, String idUsuario){
        
     //   tx.commit();
        char estado = ' ';
        String msg="";
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        try {
           iniciaOperacion();
           
            for (int i = 0; i < node.size(); i++) {
                selectedDocPadre = (Document) node.get(i).getData();
                
                
                //recorriendo los hijos
                for (int j = 0; j < node.get(i).getChildCount(); j++) {
                    selectedDocHijo = (Document) node.get(i).getChildren().get(j).getData();
                    
                    if(node.get(i).getChildren().get(j).isSelected()){
                        band = true;
                        estado = '1';
                    }else{
                        estado = '0';
                    }
                    
                       idDetPermHijo = existeDetallePermiso(idUsuario, selectedDocHijo.getId());
                    if(idDetPermHijo > 0){
                        asignarPermisosxUsuario(selectedDocHijo, estado, idUsuario, idDetPermHijo);
                    }else{
                       // idDetallePermiso = getUltimoIdDetallePermiso();
                        asignarPermisosxUsuario(selectedDocHijo, estado, idUsuario, 0);
                    }
                }
                if(band){
                    estado = '1';
                }else{
                    estado = '0';
                }
                idDetPermPadre = existeDetallePermiso(idUsuario, selectedDocPadre.getId());
                if(idDetPermPadre > 0){
                    asignarPermisosxUsuario(selectedDocPadre, estado, idUsuario, idDetPermPadre);
                }else{
//                    idDetallePermiso = getUltimoIdDetallePermiso();
                    asignarPermisosxUsuario(selectedDocPadre, estado, idUsuario, 0);
                }
                band = false;
            }
            
            tx.commit();
            msg = "Datos procesados correctamente";
            sesion.close();
        } catch (Exception e) {
            msg= e.getMessage();
            tx.rollback();
        }
        return msg;
    }
    
    private void asignarPermisosxUsuario(Document selectedDocument, char estado, String idUsuario, int idDtp){
        //iniciaOperacion();
        permiso = new TbPermiso();
        dtp = new TbDetallePermiso();
        user = new TbUsuarios();

        permiso.setId(selectedDocument.getId());
        user.setCedula(idUsuario);
        //dtp.setId(null);
        if(idDtp > 0)
            dtp.setId(idDtp);
        
        dtp.setEstado(estado);
        dtp.setTbPermiso(permiso);
        dtp.setTbUsuarios(user);
        sesion.saveOrUpdate(dtp);
        
        //sesion.close();
    }
    
    private int existeDetallePermiso(String usuarioId, int permisoId){
        int idDtp = 0;
        DaoTMenu daoTmenu = new DaoTMenu();
        lstDetalleP = daoTmenu.getEstadoPermisoUsuario(usuarioId, permisoId);
        if(!lstDetalleP.isEmpty()){
            idDtp = lstDetalleP.get(0).getId();
        }
        return idDtp;
    }
    private int getUltimoIdDetallePermiso(){
        int ultimoRegistro = 0;
        try {
            
            DaoTMenu daoTmenu = new DaoTMenu();
            
            lstDetalleP = daoTmenu.getUltimoIdDetallePermiso();
            if(!lstDetalleP.isEmpty()){
                ultimoRegistro = lstDetalleP.get(0).getId();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DaoTDetallePermiso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultimoRegistro;
    }

    @Override
    public List<TbDetallePermiso> getTodosDetPermisos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TbDetallePermiso getPermiso(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(TbDetallePermiso tDetPermiso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
