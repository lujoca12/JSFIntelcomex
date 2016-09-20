/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbPersona;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceProveedor {
    public boolean registrarProveedor(TbPersona tPersona) throws Exception;
    public List<TbPersona> getProveedor() throws Exception;
    public List<TbPersona> getProveedor(String cedPersona) throws Exception;
    public boolean updateProveedor(TbPersona tPersona) throws Exception;
}
