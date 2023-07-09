package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class ConectaDAO {

    Connection conn = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/uc11?useSSL=false";
    String user = "root";
    String password = "C14825927rds";

    public Connection connectDB() {

         try{
            Class.forName(driver);
            conn=DriverManager.getConnection(url, user, password);
            System.out.println("conectado ao banco de dados");
            return conn;
        }catch(ClassNotFoundException e){
            System.out.println("Erro Driver JDBC NÃO ENCONTRADO..."+e.getMessage());
            return null;
        }catch(SQLException e){
            System.out.println("falha na conexao com o banco de dados"+e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        ConectaDAO conexao = new ConectaDAO();
        conexao.connectDB();
    }

}
