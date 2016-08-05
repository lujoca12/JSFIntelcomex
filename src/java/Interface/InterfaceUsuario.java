/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbDetallePermiso;
import Pojo.TbPersona;
import Pojo.TbUsuarios;

import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceUsuario {
    public boolean registrar(TbUsuarios tUsuario) throws Exception;
    public boolean verificarUsuarioNick(String nick) throws Exception;
    public List<TbUsuarios> getTodosUsuarios(boolean mostrar) throws Exception;
    public List<TbUsuarios> getDocentes(boolean mostrar) throws Exception;
    public TbUsuarios getUsuario(String idUsuario) throws Exception;
    public boolean update(TbUsuarios tUsuario) throws Exception;
    public TbUsuarios getUsuarioEdicion(String idUsuario) throws Exception;
    public boolean registrarPersona(TbPersona tPersona) throws Exception;
    public List<TbPersona> getPersonas() throws Exception;
}
