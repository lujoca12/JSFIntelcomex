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
public interface InterfaceCliente {
    public boolean registrarCliente(TbPersona tPersona) throws Exception;
    public List<TbPersona> getCliente() throws Exception;
    public List<TbPersona> getCliente(String cedPersona) throws Exception;
    public boolean updateCliente(TbPersona tPersona) throws Exception;
}