/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import java.sql.*;
import config.ConectaDB;
import java.util.ArrayList;
import java.util.List;
import model.DTO.AutorizacaoPendenteDTO;
import model.DTO.HistoricoDTO;

/**
 * Document : Classe Resposta Autorização DAO - Data Access Object Created on :
 * 27 de nov. de 2025 Author : Zenalvo Junior
 */
public class RespostaAutorizacaoDAO {
    // Atributos

    //Métodos
    //+ criarResposta( int idAutorizacao, int idResponsavel, int idAluno): void
    //INSERT automático para gerar a resposta enviada ao responsável assim que uma autorizacao for criada pela escola
    public void criarResposta(int idAutorizacao, int idResponsavel, int idAluno) throws ClassNotFoundException {
        // String sql = "INSERT INTO resposta_autorizacao (status_resposta, fk_autorizacao, fk_responsavel, fk_aluno) "
        //            + "VALUES ('Pendente', ?, ?, ?)";
        String sql = "INSERT INTO resposta_autorizacao (status_resposta, fk_autorizacao, fk_responsavel, fk_aluno) "
                + "VALUES ('Pendente', ?, ?, ?)";

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setInt(1, idAutorizacao);
            preStmt.setInt(2, idResponsavel);
            preStmt.setInt(3, idAluno);
            preStmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }

    }

    // + buscarPendentesPorResponsavel(int idResponsavel): List<AutorizacaoPendenteDTO>
    // Busca apenas as autorizações que estão com status 'Pendente' e data limite válida
    public List<AutorizacaoPendenteDTO> buscarPendentesPorResponsavel(int idResponsavel) throws ClassNotFoundException {
        // Faz o JOIN entre Resposta, Autorização e Aluno
        String sql = "SELECT ra.pk_id AS id_resposta, a.titulo, a.descricao, a.data_limite, al.nome AS nome_aluno "
                + "FROM resposta_autorizacao ra "
                + "JOIN autorizacao a ON ra.fk_autorizacao = a.pk_id "
                + "JOIN aluno al ON ra.fk_aluno = al.pk_id "
                + "WHERE ra.fk_responsavel = ? "
                + "AND ra.status_resposta = 'Pendente' "
                + "AND a.status_global = 'ABERTA' "
                + "ORDER BY a.data_limite ASC";

        List<AutorizacaoPendenteDTO> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setInt(1, idResponsavel);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                AutorizacaoPendenteDTO dto = new AutorizacaoPendenteDTO(
                        rs.getInt("id_resposta"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("nome_aluno"),
                        rs.getDate("data_limite")
                );
                lista.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar pendentes: " + ex);
        }
        return lista;
    }

    // + atualizarStatus(int idResposta, String novoStatus): boolean
    // Atualiza para 'Autorizado' ou 'Negado' e define a data da resposta como hoje
    public boolean atualizarStatus(int idResposta, String novoStatus) throws ClassNotFoundException {
        String sql = "UPDATE resposta_autorizacao SET status_resposta = ?, data_resposta = CURRENT_DATE WHERE pk_id = ?";

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setString(1, novoStatus);
            preStmt.setInt(2, idResposta);

            int linhasAfetadas = preStmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar status: " + ex);
            return false;
        }
    }

    // + contarPendentes(int idResponsavel): int
    // Retorna a quantidade de autorizações pendentes para exibir no Dashboard
    public int contarPendentes(int idResponsavel) throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) "
                + "FROM resposta_autorizacao ra "
                + "JOIN autorizacao a ON ra.fk_autorizacao = a.pk_id "
                + "WHERE ra.fk_responsavel = ? "
                + "AND ra.status_resposta = 'Pendente' "
                + "AND a.status_global = 'ABERTA'";

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setInt(1, idResponsavel);
            ResultSet rs = preStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao contar pendentes: " + ex);
        }
        return 0;
    }
    
    // + buscarHistoricoCompleto(int idResponsavel): List<HistoricoDTO>
    public List<HistoricoDTO> buscarHistoricoCompleto(int idResponsavel) throws ClassNotFoundException {
        // Faz o JOIN para pegar dados da Autorização, da Resposta e do Aluno
        String sql = "SELECT a.titulo, a.data_limite, ra.data_resposta, ra.status_resposta, al.nome AS nome_aluno, al.turma "
                   + "FROM resposta_autorizacao ra "
                   + "JOIN autorizacao a ON ra.fk_autorizacao = a.pk_id "
                   + "JOIN aluno al ON ra.fk_aluno = al.pk_id "
                   + "WHERE ra.fk_responsavel = ? "
                   + "ORDER BY a.data_limite DESC"; // Ordena do mais recente para o mais antigo

        List<HistoricoDTO> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setInt(1, idResponsavel);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                HistoricoDTO dto = new HistoricoDTO(
                    rs.getString("titulo"),
                    rs.getDate("data_limite"),
                    rs.getDate("data_resposta"),
                    rs.getString("status_resposta"),
                    rs.getString("nome_aluno"),
                    rs.getString("turma")
                );
                lista.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar histórico: " + ex);
        }
        return lista;
    }
}
