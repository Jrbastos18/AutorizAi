/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import config.ConectaDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Responsavel;

/**
 * Document : Classe Escola DAO - Data Access Object
 * Created on : 18 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class ResponsavelDAO {
    //Atributos

    //Métodos
    //+ cadastrar( responsavel, idLogin, idEndereco, ra ): boolean
    public boolean cadastrar(Responsavel responsavel, int idLogin, int idEndereco, String ra) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement preStmt = null;
        PreparedStatement preAluno = null;
        PreparedStatement preAssociativa = null;
        ResultSet rs = null;
        try {
            conn = ConectaDB.conectar();
            conn.setAutoCommit(false); // Garantindo que tudo seja salvo junto

            //String sql = "INSERT INTO responsavel (nome, cpf, telefone, email, fk_login, fk_endereco) VALUES (?, ?, ?, ?, ?, ?);";  
            String sql = "INSERT INTO responsavel (nome, cpf, telefone, email, fk_login, fk_endereco) VALUES (?, ?, ?, ?, ?, ?);";
            // Faz a inserção do Responsável e vai retornar a chave primária gerada
            preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preStmt.setString(1, responsavel.getNome());
            preStmt.setString(2, responsavel.getCpf());
            preStmt.setString(3, responsavel.getTelefone());
            preStmt.setString(4, responsavel.getEmail());
            preStmt.setInt(5, idLogin); // Vai receber o id do Login cadastrado pelo responsavel
            preStmt.setInt(6, idEndereco); // Vai receber o id do Endereço cadastrado pelo responsavel

            int result = preStmt.executeUpdate();

            if (result == 0) {
                return false;
            }

            // Pegando pk_id gerada do responsável
            rs = preStmt.getGeneratedKeys();
            int idResponsavel = -1;

            if (rs.next()) {
                idResponsavel = rs.getInt(1);
            }

            rs.close();
            preStmt.close();

            // Busca o pk_id do Aluno pelo RA
            String sql_busca_aluno = "SELECT pk_id FROM aluno WHERE ra = ?";
            preAluno = conn.prepareStatement(sql_busca_aluno);
            preAluno.setString(1, ra);

            ResultSet rsAluno = preAluno.executeQuery();

            int idAluno = -1;
            if (rsAluno.next()) {
                idAluno = rsAluno.getInt("pk_id");
            } else {
                return false;
            }

            rsAluno.close();
            preAluno.close();

            // Inserindo na tabela associativa aluno_responsavel
            String sql_associativa = "INSERT INTO aluno_responsavel (fk_responsavel, fk_aluno) VALUES (?, ?)";

            preAssociativa = conn.prepareStatement(sql_associativa);
            preAssociativa.setInt(1, idResponsavel);
            preAssociativa.setInt(2, idAluno);

            int resultAssoc = preAssociativa.executeUpdate();

            if (resultAssoc == 0) {
                return false;
            }

            // Confirmando tudo
            conn.commit();

            return true;
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return false;
        }
    }

    //+ buscarPorIdLogin( idLogin ): Responsavel
    public Responsavel buscarPorIdLogin(int idLogin) {
        Connection conn = null;
        try {
            conn = ConectaDB.conectar();

            String sql = "SELECT * FROM responsavel WHERE fk_login = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idLogin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Responsavel responsavel = new Responsavel();
                responsavel.setId(rs.getInt("pk_id"));
                responsavel.setNome(rs.getString("nome"));
                responsavel.setCpf(rs.getString("cpf"));
                responsavel.setEmail(rs.getString("email"));
                responsavel.setTelefone(rs.getString("telefone"));
                responsavel.setIdLogin(rs.getInt("fk_login"));
                
                return responsavel;
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro - SQL: " + ex);
        }
        return null;
    }
    
    //+ buscarPorAluno(int idAluno): List<Aluno> 
    public List<Responsavel> buscarPorAluno(int idAluno) throws ClassNotFoundException{
        // String sql = "SELECT r.* FROM responsavel AS r ";
        //            + "JOIN aluno_responsavel AS ar ON ar.fk_responsavel = r.pk_id "
        //            + "WHERE ar.fk_aluno = ?";
        String sql = "SELECT r.* FROM responsavel AS r "
                   + "JOIN aluno_responsavel AS ar ON ar.fk_responsavel = r.pk_id "
                   + "WHERE ar.fk_aluno = ?";
        
        List<Responsavel> lista = new ArrayList<>();
        
        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
            preStmt.setInt(1, idAluno);
            
            ResultSet rs = preStmt.executeQuery();
            
            while (rs.next()){
                Responsavel responsavel = new Responsavel();
                responsavel.setId(rs.getInt("pk_id"));
                responsavel.setNome(rs.getString("nome"));
                responsavel.setCpf(rs.getString("cpf"));
                responsavel.setEmail(rs.getString("email"));
                responsavel.setTelefone(rs.getString("telefone"));
                
                lista.add(responsavel);
            }      
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }
        
        return lista;
    }
}
