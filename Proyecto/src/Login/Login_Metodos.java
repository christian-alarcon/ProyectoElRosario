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
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login_Metodos {
    
 public static String Usuario;
    public static String Password;  
    
    public void IniciarSesion(JTextField txtUsuario,JTextField txtContrasena,JLabel lblError) {
        Login l=new Login();
        Usuario = txtUsuario.getText();
        Password = txtContrasena.getText();
        Connection con;
        con = Conexion.GetConnection();
        String consulta = "CALL SP_UsuariosAcceso ('" + txtUsuario.getText() + "','" + txtContrasena.getText() + "') ";
        
        try {
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
           
            if (rs.first()) {
                System.out.println(rs.getString(1)+" "+rs.getString(2));
                Menu ob = new Menu();
                ob.setVisible(true);
                l.dispose();
            }
            else {
                txtUsuario.setText("");
                txtContrasena.setText("");
                txtUsuario.requestFocus();
                lblError.setVisible(true);
            }
            rs.close();
            con.close();
        } catch (Exception SQL) {
            System.out.println(SQL.getMessage());
        }
    }
}