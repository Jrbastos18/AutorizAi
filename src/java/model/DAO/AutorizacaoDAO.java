/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import config.ConectaDB;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Autorizacao;

/**
 * Document : Classe Autorizacao DAO - Data Access Object
 * Created on : 27 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class AutorizacaoDAO {
    // ATRIBUTOS
    
    // MÉTODOS
    // + cadastrar(Autorizacao autorizacao, int idEscola): int
    public int cadastrar(Autorizacao autorizacao, int idEscola) throws ClassNotFoundException {
        // String sql = "INSERT INTO autorizacao (titulo, descricao, data_limite, status_global, fk_escola) VALUES (?, ?, ?, 'ABERTA', ?)";
        String sql = "INSERT INTO autorizacao (titulo, descricao, data_limite, status_global, fk_escola) VALUES (?, ?, ?, 'ABERTA', ?)";
        
        // Utilizando try-with-resources para garantir que a conexão e o PreparedStatement seja fechado
        try (Connection conn = ConectaDB.conectar();PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
  
           preStmt.setString(1, autorizacao.getTitulo());
           preStmt.setString(2, autorizacao.getDescricao());
           preStmt.setDate(3, new java.sql.Date(autorizacao.getData_limite().getTime()));
           preStmt.setInt(4, idEscola);
           
           preStmt.executeUpdate();
           
           ResultSet rs = preStmt.getGeneratedKeys();
           if (rs.next()){
               return rs.getInt(1);
           }       
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }      
        return -1;
    }
    
    // + buscarPorId(int idAutorizacao): Autorizacao
    public Autorizacao buscarPorId(int idAutorizacao) throws ClassNotFoundException {
        // String sql = "SELECT * FROM autorizacao WHERE id = ?";
        String sql = "SELECT * FROM autorizacao WHERE pk_id = ?";
        
        try (Connection conn = ConectaDB.conectar();
                PreparedStatement preStmt = conn.prepareStatement(sql)){
            
            preStmt.setInt(1, idAutorizacao);
            
            ResultSet rs = preStmt.executeQuery();
            
            if (rs.next()){
                Autorizacao autorizacao = new Autorizacao();
                
                autorizacao.setId(rs.getInt("pk_id"));
                autorizacao.setTitulo(rs.getString("titulo"));
                autorizacao.setDescricao(rs.getString("descricao"));
                autorizacao.setData_envio(rs.getDate("data_envio"));
                autorizacao.setData_limite(rs.getDate("data_limite"));
                autorizacao.setStatus_global(rs.getString("status_global"));
                
                return autorizacao;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }
        
        
        return null;
    }
    
    // + consultaGeral(int idEscola): List<Autorizacao>
    public List<Autorizacao> consultaGeral(int idEscola) throws ParseException, ClassNotFoundException{
        String sql = "SELECT * FROM autorizacao WHERE fk_escola = ?";
        
        List<Autorizacao> lista = new ArrayList<>();
        
        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
            preStmt.setInt(1, idEscola);
            ResultSet rs = preStmt.executeQuery();
            
            while (rs.next()){
                Autorizacao autorizacao = new Autorizacao();
                autorizacao.setId(rs.getInt("pk_id"));
                autorizacao.setTitulo(rs.getString("titulo"));
                autorizacao.setDescricao(rs.getString("descricao"));
                autorizacao.setData_envio(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data_envio")));
                autorizacao.setData_limite(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data_limite")));
                autorizacao.setStatus_global(rs.getString("status_global"));

                lista.add(autorizacao);
            }
   
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }
        
        return lista;
    }
    
    // + alterar(Autorizacao autorizacao): boolean
    public boolean alterar(Autorizacao autorizacao) throws ClassNotFoundException {
        // Atualiza Título, Descrição e Data Limite
        String sql = "UPDATE autorizacao SET titulo = ?, descricao = ?, data_limite = ? WHERE pk_id = ?";
        
        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
            
            preStmt.setString(1, autorizacao.getTitulo());
            preStmt.setString(2, autorizacao.getDescricao());
            preStmt.setDate(3, new java.sql.Date(autorizacao.getData_limite().getTime()));
            preStmt.setInt(4, autorizacao.getId());
            
            int resultado = preStmt.executeUpdate();
            return resultado > 0;
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return false;
        }
    }
}
