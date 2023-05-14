/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package web;

import Entidades.Usuarios;
import controlador.UsuariosDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;



@WebService(serviceName = "ServicioWeb")
public class ServicioWeb {
    
    
    UsuariosDAO usuarioDao = new UsuariosDAO();
    
    @WebMethod(operationName = "create")
    public String create(@WebParam(name="usuario") Usuarios usuario){
        
        try{
        if(usuarioDao.create(usuario)){
            return "Usuarios "+usuario.getNombre()+" Creado con exito";
        }
        return "Creados con exito";
        }catch(SQLException e){
            
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, e);
            return "Error de petición";
        }
    }
    
    @WebMethod(operationName = "findById")
    public Usuarios findById(@WebParam(name="id") int id) {
        
        try {
            return usuarioDao.findById(id);
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
    }
    
    @WebMethod(operationName = "findAll")
    public List<Usuarios> findAll() {
        
        try {
            return usuarioDao.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    @WebMethod(operationName = "update")
    public Usuarios update(@WebParam(name="usuario") Usuarios usuario) {
       
      try{
             
        Usuarios usuarioupdate = null;
             
        if(usuarioDao.update(usuario)){
            usuarioupdate = usuarioDao.findById(usuario.getId());
        }
        return usuarioupdate;
        }catch(SQLException e){
            
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    @WebMethod(operationName = "delete")
    public String delete(@WebParam(name="id") int id) {
        try{
        String m = "";
             
        if(usuarioDao.delete(id)){
            m ="Usuario Eliminado con exito";
        }
        
        return m;
        }catch(SQLException e){
            
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, e);
            return "Error en la consulta";
        }
    }
}




