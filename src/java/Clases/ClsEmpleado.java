/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author server
 */
public class ClsEmpleado {
 
    private String id;    
    private String displayName; 
    private String name;
     
    public ClsEmpleado() {}
 
    public ClsEmpleado(String id, String displayName, String name) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getDisplayName() {
        return displayName;
    }
 
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
     
    @Override
    public String toString() {
        return name;
    }
}
