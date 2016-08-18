/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbEmpresa;
import Pojo.TbTipoempresa;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceEmpresa {
    public boolean registrar(TbEmpresa tEmpresa) throws Exception;
    public List<TbEmpresa> getEmpresa() throws Exception;
    public List<TbEmpresa> getTodasEmpresas(boolean mostrar) throws Exception;
    public TbEmpresa getEmpresa(String idEmpresa) throws Exception;
    public boolean update(TbEmpresa tbEmpresa) throws Exception;
}
