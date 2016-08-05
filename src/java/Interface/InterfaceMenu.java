/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbDetallePermiso;
import Pojo.TbPermiso;
import Pojo.TbUsuarios;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceMenu {
    public boolean registrar(TbPermiso tPermiso) throws Exception;
    public List<TbPermiso> getPadres() throws Exception;
    public List<TbPermiso> getTodosPermisos(boolean mostrar) throws Exception;
    public List<TbPermiso> getMenuNavxUsuarios(TbUsuarios usuario) throws Exception;
    public TbPermiso getPermiso(String idUsuario) throws Exception;
    public boolean update(TbPermiso tPermiso) throws Exception;
    public boolean updatePadres(TbPermiso tPermiso) throws Exception;
    public List<TbDetallePermiso> getUltimoIdDetallePermiso() throws Exception;
}
