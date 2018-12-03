/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p.pkg1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author carlo
 */
public class Kata5P1 {
    
    static Connection con;
    static String ID, Name, Apellidos, Departamento;
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        ReadFile rd = new ReadFile("email.txt");
        if(connection()) putMails(rd);//createTable(); //showFields();        
    }

    private static boolean connection() {
        String url = "jdbc:sqlite:KATA5.db";
        
        con = null;
        
        try{
          con = DriverManager.getConnection(url);
          System.out.println("Conexion estabelcida");
          
        } catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
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

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL(\n"
                + "ID integer PRIMARY KEY AUTOINCREMENT, \n"
                + "Mail text NOT NULL);";
                
        try (Statement stmt = con.createStatement()) {
            // Se crea la nueva tabla
            if(stmt.execute(sql)) System.out.println("Tabla creada");
            else System.out.println("Tabla ya creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void putMails(ReadFile rd) throws IOException, SQLException {
        Iterator<String> iterator = rd.readAll().iterator();
        String sql = "INSERT INTO EMAIL(Mail) VALUES(?)";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        while(iterator.hasNext()){
            pstm.setString(1, iterator.next());
            pstm.executeUpdate();
        }
    }
    
}