/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TbProducto;
import Pojo.TbInventario;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceProducto {
    public boolean registrarProducto(TbProducto tProducto) throws Exception;
    public List<TbProducto> getProducto() throws Exception;
    public List<TbProducto> getProducto(String idProducto) throws Exception;
    public boolean updateProducto(TbProducto tProducto) throws Exception;
    public TbInventario getStockProducto(String idProducto) throws Exception;
}
