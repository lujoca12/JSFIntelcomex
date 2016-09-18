/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbTipousuario;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceTipoUsuario {
    public boolean registrar(TbTipousuario tTipoUsuario) throws Exception;
    public List<TbTipousuario> getTodosTipoUsuarios() throws Exception;
    public TbTipousuario getTipoUsuarios(String rol) throws Exception;
    public boolean update(TbTipousuario tTipoUsuario) throws Exception;
}
