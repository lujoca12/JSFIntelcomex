/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbTipoUsuario;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceTipoUsuario {
    public boolean registrar(TbTipoUsuario tTipoUsuario) throws Exception;
    public List<TbTipoUsuario> getTodosTipoUsuarios() throws Exception;
    public TbTipoUsuario getTipoUsuarios(String rol) throws Exception;
    public boolean update(TbTipoUsuario tTipoUsuario) throws Exception;
}
