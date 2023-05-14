/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Entidades.Usuarios;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UsuariosDAO extends Conexion{
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public boolean create(Usuarios usuario) throws SQLException{
        
        try{
        String SQL = "INSERT INTO usuarios VALUE(null,?,?,?,?)";
        
        boolean result = false;
        
        con=conectar();
        ps=con.prepareStatement(SQL);
        
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getCedula());
        ps.setString(4, usuario.getStatus());
        
        if(ps.executeUpdate() > 0){
            result = true;
            System.err.println("Creados");
        }else{       
        return false;
        }
        }catch(SQLException se){
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, se);
            return false;
        }finally{
            ps.close();
            con.close();
        }
        return false;
    }
    
    public Usuarios findById(int id) throws SQLException{
        
        try{
        String SQL = "SELECT * FROM usuarios WHERE id = "+id+"";
        
        Usuarios usuario = null;
        
        con=conectar();
        ps=con.prepareStatement(SQL);
        rs=ps.executeQuery();
        
        while(rs.next()){
            usuario = new Usuarios(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        return usuario;
        }catch(SQLException se){
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
    }
    
    public boolean update(Usuarios usuario) throws SQLException{
        
        try{
        String SQL = "UPDATE usuarios SET nombre=?,apellido=?,cedula=?,status=? WHERE id=?";
        
        boolean result = false;
        
        con=conectar();
        ps=con.prepareStatement(SQL);
        
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getCedula());
        ps.setString(4, usuario.getStatus());
        
        ps.setInt(5, usuario.getId());
        
        if(ps.executeUpdate() > 0){
            result = true;
            System.err.println("Actualizados");
        }else{
            return false;
        }       
        return false;
        }catch(SQLException se){
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, se);
            return false;
        }finally{
            ps.close();
            con.close();
        }
    }
   
    public List<Usuarios> findAll() throws SQLException{
        
        try{
        String SQL = "SELECT * FROM usuarios";
        
        List<Usuarios> findAll = new ArrayList<>();
        con=conectar();
        ps=con.prepareStatement(SQL);
        rs=ps.executeQuery();
        
        while(rs.next()){
            Usuarios usuario = new Usuarios(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            
            findAll.add(usuario);
        
        }
        return findAll;
        }catch(SQLException se){
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
    }
    
    public boolean delete(int id) throws SQLException{
        
        try{
        String SQL = "DELETE FROM usuarios WHERE id= "+id+"";
        
        boolean result = false;
        
        con=conectar();
        ps=con.prepareStatement(SQL);
        
        if(ps.executeUpdate() > 0){
            result = true;
        }       
        return false;
        }catch(SQLException se){
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, se);
            return false;
        }finally{
            ps.close();
            con.close();
        }
    }
    
}
