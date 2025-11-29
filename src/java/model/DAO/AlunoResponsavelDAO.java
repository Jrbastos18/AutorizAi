/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import config.ConectaDB;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * Document : Classe de Associação AlunoResponsavel DAO - Data Access Object
 * Created on : 26 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class AlunoResponsavelDAO {
    // Atributos

    //+ buscarTodosPorEscola( int idEscola ): List<int[]>
    public List<int[]> buscarTodosPorEscola(int idEscola) throws ClassNotFoundException {
        String sql = "SELECT a.pk_id AS idAluno, r.pk_id AS idResponsavel "
                + "FROM responsavel r "
                + "JOIN aluno_responsavel ar ON ar.fk_responsavel = r.pk_id "
                + "JOIN aluno a ON a.pk_id = ar.fk_aluno "
                + "WHERE a.fk_escola = ?";

        List<int[]> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setInt(1, idEscola);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                int[] registro = new int[2];
                registro[0] = rs.getInt("idAluno");        
                registro[1] = rs.getInt("idResponsavel"); 

                lista.add(registro);
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }

        return lista;
    }

    //+ buscarPorTurma(String turma, int idEscola): List<int[]>
    public List<int[]> buscarPorTurma(String turma, int idEscola) throws ClassNotFoundException {
        String sql = "SELECT a.pk_id AS idAluno, r.pk_id AS idResponsavel "
                + "FROM aluno AS a "
                + "JOIN aluno_responsavel AS ar ON ar.fk_aluno = a.pk_id "
                + "JOIN responsavel AS r ON r.pk_id = ar.fk_responsavel "
                + "WHERE a.turma = ? AND a.fk_escola = ?";

        List<int[]> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setString(1, turma);
            preStmt.setInt(2, idEscola); 
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                int[] registro = new int[2];
                registro[0] = rs.getInt("idAluno");        
                registro[1] = rs.getInt("idResponsavel"); 

                lista.add(registro);
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }

        return lista;
    }

}
