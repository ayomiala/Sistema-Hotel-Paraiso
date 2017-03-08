/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import Datos.vhabitacion;
import Datos.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CARLOS
 */
public class fproducto {
    
   private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","Producto","Descripción","Unidad Medida","Precio Venta"};
       
       String [] registro =new String [5];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select * from producto where nombre like '%"+ buscar + "%' order by idproducto desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idproducto");
               registro [1]=rs.getString("nombre");
               registro [2]=rs.getString("descripcion");
               registro [3]=rs.getString("unidad_medida");
               registro [4]=rs.getString("precio_venta");
               
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (vproducto dts){
       sSQL="insert into producto (nombre,descripcion,unidad_medida,precio_venta)" +
               "values (?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUnidad_medida());
           pst.setDouble(4, dts.getPrecio_venta());
           
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
   public boolean editar (vproducto dts){
       sSQL="update producto set nombre=?,descripcion=?,unidad_medida=?,precio_venta=?"+
               " where idproducto=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUnidad_medida());
           pst.setDouble(4, dts.getPrecio_venta());
           
           pst.setInt(5, dts.getIdproducto());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
  
   public boolean eliminar (vproducto dts){
       sSQL="delete from producto where idproducto=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdproducto());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
    
    
    
    
    
}
