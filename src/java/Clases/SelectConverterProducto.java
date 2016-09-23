/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author server
 */
@FacesConverter("converterProd")
public class SelectConverterProducto implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        int valor = 0;
        if (value == null) {
            return null;
        } else {
            if(value.equals("-1")){
                
            }else{
               for (ClsProducto item : getSelectItems(uic)) {
                   if(item.getId().equals(value))
                        return item;
                } 
            }
            
            return null;
        }
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    protected Collection<ClsProducto> getSelectItems(UIComponent component) {
        Collection<ClsProducto> collection = new ArrayList<ClsProducto>();

        for (UIComponent child : component.getChildren()) {
            if (child instanceof UISelectItem) {
                UISelectItem ui = (UISelectItem) child;
                ClsProducto item = (ClsProducto) ui.getValue();
                collection.add(item);
            } else 
                if (child instanceof UISelectItems) {
                UISelectItems ui = (UISelectItems) child;
                Object value = ui.getValue();
                collection.addAll((Collection<ClsProducto>) value);
            }
        }

        return collection;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((ClsProducto) object).getId());
        }
        else {
            return null;
        }
    }  
}
