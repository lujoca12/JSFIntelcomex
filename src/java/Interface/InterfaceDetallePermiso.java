/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbDetallePermiso;
import java.util.List;
import org.primefaces.model.TreeNode;

/**
 *
 * @author server
 */
public interface InterfaceDetallePermiso {
    public String registrar(List<TreeNode> nodes, String idUsuario) throws Exception;
    
    public List<TbDetallePermiso> getTodosDetPermisos() throws Exception;
    public TbDetallePermiso getPermiso(String idUsuario) throws Exception;
    public boolean update(TbDetallePermiso tDetPermiso) throws Exception;
}
