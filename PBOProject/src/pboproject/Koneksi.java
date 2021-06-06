/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pboproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Muhammad Hafidz A
 */
public class Koneksi {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    public Integer id;
 
    public Integer getIdAdmin() {
        return id;
    }
 
    public void setIdAdmin(Integer id) {
        this.id = id;
    }
    
    
    
    public Connection getConnection() {
        return connection;
    }
 
    public void getCon() {
        
        try {
            String urlDatabase = "jdbc:mysql://localhost/absensi"; //alamat database
            String user = "root"; //user yang dipakai utk akses database
            String password = ""; //password yang digunakan utk akses database
            connection = DriverManager.getConnection(urlDatabase, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + ex.getMessage());
        }   
       
    }
 
    public void Eksekusi(String Query, String Pesan){
        try {
            statement=connection.createStatement();
            statement.execute(Query);
            statement.close();
            JOptionPane.showMessageDialog(null, Pesan);
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
   
    public void tampil(javax.swing.JTable table, String Query) {
        try {
     
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Query);
            
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            statement.close();
            resultSet.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
 
    
    public void combo(String Query, String ID,String Nama, javax.swing.JComboBox cb) {
 
        try {
 
            String showDataQuery = Query;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(showDataQuery);
          
            while (resultSet.next()) {
                cb.addItem(resultSet.getString(ID) + "-" + resultSet.getString(Nama));
                
            }
 
            statement.close();
            resultSet.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan di combobox " + ex.getMessage());
        }
    }
}
