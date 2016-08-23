/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbParametro;
import Pojo.TbParametrodetalle;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceParametro {
    public boolean registrarParametro(TbParametro tParametro) throws Exception;
    public List<TbParametro> getParametro() throws Exception;
    public boolean registrarParametroDetalle(TbParametrodetalle tParametroDetalle) throws Exception;
    public List<TbParametrodetalle> getParametroDetalle(String descripcionParametro) throws Exception;
    public boolean updateParametro(TbParametro tParametro) throws Exception;
    public boolean updateParametrodetalle(TbParametrodetalle tParametroDetalle) throws Exception;
}
