/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author carlo
 */
public class Kata5P1 {
    
    static Connection con;
    static String ID, Name, Apellidos, Departamento;
    public static void main(String[] args) {
        connection();
        
    }

    private static void connection() {
        String url = "jdbc:sqlite:KATA5.db";
        
        con = null;
        
        try{
          con = DriverManager.getConnection(url);
          System.out.println("Conexion estabelcida");
          
          showFields();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void showFields() {
        selectAll();
    }

    private static void selectAll() {
        String sql = "SELECT * FROM PEOPLE";
            
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            // Bucle sobre el conjunto de registros.
            while (rs.next()) {
                ID = rs.getInt("ID")+"";
                Name = rs.getString("Name");
                Apellidos = rs.getString("Apellidos");
                Departamento = rs.getString("Departamento");
                System.out.printf("%-3s  %-20s  %-20s  %-3s%n", ID, Name, Apellidos, Departamento);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());                
        }
    }
    
}