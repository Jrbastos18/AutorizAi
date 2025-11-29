/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import config.ConectaDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.DestinoAutorizacao;

/**
 * Document : Classe Destino Autorizacao DAO - Data Access Object
 * Created on : 27 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class DestinoAutorizacaoDAO {
    // ATRIBUTOS
    
    // MÉTODOS
    // + cadastrar(Autorizacao autorizacao, int idEscola): int
    public boolean cadastrar(DestinoAutorizacao destinoAutorizacao) throws ClassNotFoundException {
        // String sql = "INSERT INTO destino_autorizacao (fk_autorizacao, turma_destino, enviar_para_todos) VALUES (?, ?, ?)";
        String sql = "INSERT INTO destino_autorizacao (fk_autorizacao, turma_destino, enviar_para_todos) VALUES (?, ?, ?)";
        
        // Utilizando try-with-resources para garantir que a conexão e o PreparedStatement seja fechado
        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
  
           preStmt.setInt(1, destinoAutorizacao.getIdAutorizacao());
           preStmt.setString(2, destinoAutorizacao.getTurmaDestino());
           preStmt.setBoolean(3, destinoAutorizacao.isEnviarParaTodos());
           
           preStmt.executeUpdate();
           return true;      
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return false;
        }
    }
}
