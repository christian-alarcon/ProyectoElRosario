/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

/**
 *
 * @author Diego
 */

import Conexion.Conexion;
import Menu.Menu;
import java.sql.Connection;
import java.sql.ResultSet;

public class Login_Metodos {
    
 public static String Usuario;
    public static String Password;  
    
    public void IniciarSesion() {
        Login l=new Login();
        Usuario = l.txtUsuario.getText();
        Password = l.txtContrasena.getText();
        Connection con;
        con = Conexion.GetConnection();
        String consulta = "CALL SP_UsuariosAcceso ('" + Usuario + "','" + Password + "') ";
        try {
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
           
            if (rs.next()) {
                Menu ob = new Menu();
                ob.setVisible(true);
                l.dispose();
                
                  }
              else {
                l.txtUsuario.setText("");
                l.txtContrasena.setText("");
                l.txtUsuario.requestFocus();
                l.lblError.setVisible(true);
            }
            rs.close();

        } catch (Exception SQL) {
            System.out.println(SQL.getMessage());
        }
    }
}