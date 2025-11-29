/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import config.ConectaDB;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Aluno;

/**
 * Document : Classe Aluno DAO - Data Access Object Created on : 19 de nov. de
 * 2025 Author : Zenalvo Junior
 */
public class AlunoDAO {
    //Atributos

    //Métodos
    // geraRA de 11 dígitos e retorna como string
    public static String gerarRA() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            sb.append(rand.nextInt(10));
        }

        return sb.toString();
    }

    // Gera um RA único verificando no banco de dados se já existe
    public String gerarRAUnico() {
        String ra;
        do {
            ra = gerarRA();
        } while (verificaRA(ra));
        return ra;
    }

    //+ cadastrar( Aluno, idEscola int ): boolean
    public boolean cadastrar(Aluno aluno, int idEscola) throws ClassNotFoundException {
        Connection conn = null;

        try {
            conn = ConectaDB.conectar();

            String raGerado = gerarRAUnico();
            aluno.setRa(raGerado);

            //String sql = "INSERT INTO aluno (nome, cpf, ra, turma, fk_escola) VALUES (?, ?, ?, ?, ?)"
            String sql = "INSERT INTO aluno (nome, cpf, ra, turma, fk_escola) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preStmt = conn.prepareStatement(sql);

            preStmt.setString(1, aluno.getNome());
            preStmt.setString(2, aluno.getCpf());
            preStmt.setString(3, aluno.getRa());
            preStmt.setString(4, aluno.getTurma());
            preStmt.setInt(5, idEscola);

            //Executando a inserção
            int resultado = preStmt.executeUpdate();

            // Fechando a conexão e o PreparedStatement
            conn.close();
            preStmt.close();

            return resultado > 0; // Se o resultado for maior que 0 ele retorna "true", caso contrário, retorna "false"         
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return false;
        }
    }

    //+ verificaRA( String ra ): boolean
    public boolean verificaRA(String ra) {
        String sql = "SELECT COUNT(*) FROM aluno WHERE ra = ?";

        // Foi necessário utilizar try-with-resources para garantir que as conexões sejam fechadas
        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setString(1, ra);

            try (ResultSet rs = preStmt.executeQuery()) {
                if (rs.next()) {
                    int qtd = rs.getInt(1);
                    return qtd > 0;
                }
            }

        } catch (Exception ex) {
            System.out.println("Erro - SQL: " + ex);
        }

        return false;
    }

    //+ buscarPorEscola(int idEscola): List<Aluno> 
    public List<Aluno> buscarPorEscola(int idEscola) throws ClassNotFoundException {
        // String sql = "SELECT * FROM aluno WHERE fk_escola = ?";
        String sql = "SELECT * FROM aluno WHERE fk_escola = ?";
        List<Aluno> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
            preStmt.setInt(1, idEscola);

            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("pk_id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setRa(rs.getString("ra"));
                aluno.setTurma(rs.getString("turma"));
                aluno.setIdEscola(idEscola);

                lista.add(aluno);
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }

        return lista;
    }

    //+ buscarPorTurma(int idEscola): List<Aluno> 
    public List<Aluno> buscarPorTurma(int idEscola, String turma) throws ClassNotFoundException {
        // String sql = "SELECT * FROM aluno WHERE fk_escola = ? AND turma = ?";
        String sql = "SELECT * FROM aluno WHERE fk_escola = ? AND turma = ?";
        List<Aluno> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
            preStmt.setInt(1, idEscola);
            preStmt.setString(2, turma);

            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("pk_id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setRa(rs.getString("ra"));
                aluno.setTurma(rs.getString("turma"));
                aluno.setIdEscola(idEscola);

                lista.add(aluno);
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }

        return lista;
    }

    //+ buscarPorResponsavel(int idEscola): List<Aluno> 
    public List<Aluno> buscarPorResponsavel(int idResponsavel) throws ClassNotFoundException {
        // String sql = "SELECT * FROM aluno WHERE fk_escola = ?";
        String sql = "SELECT al.* "
                + "FROM aluno AS al "
                + "JOIN aluno_responsavel AS ar "
                + "ON al.pk_id = ar.fk_aluno "
                + "WHERE ar.fk_responsavel = ?";
        List<Aluno> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
            preStmt.setInt(1, idResponsavel);

            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("pk_id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setRa(rs.getString("ra"));
                aluno.setTurma(rs.getString("turma"));
                aluno.setIdEscola(rs.getInt("fk_escola"));

                lista.add(aluno);
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }

        return lista;
    }

    // + consultaGeral(int idEscola): List<Aluno>
    public List<Aluno> consultaGeral(int idEscola) throws ClassNotFoundException {
        String sql = "SELECT * FROM aluno WHERE fk_escola = ?";

        List<Aluno> lista = new ArrayList<>();

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {
            preStmt.setInt(1, idEscola);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("pk_id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setRa(rs.getString("ra"));
                aluno.setTurma(rs.getString("turma"));

                lista.add(aluno);
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }

        return lista;
    }

    // + alterar(Aluno aluno): boolean
    public boolean alterar(Aluno aluno) throws ClassNotFoundException {
        // Não alteramos o RA, pois é um identificador único gerado pelo sistema
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, turma = ? WHERE pk_id = ?";

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setString(1, aluno.getNome());
            preStmt.setString(2, aluno.getCpf());
            preStmt.setString(3, aluno.getTurma());
            preStmt.setInt(4, aluno.getId()); 

            int resultado = preStmt.executeUpdate();
            return resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return false;
        }
    }

    // + buscarPorId(int id): Aluno
    public Aluno buscarPorId(int id) throws ClassNotFoundException {
        String sql = "SELECT * FROM aluno WHERE pk_id = ?";

        try (Connection conn = ConectaDB.conectar(); PreparedStatement preStmt = conn.prepareStatement(sql)) {

            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("pk_id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setRa(rs.getString("ra"));
                aluno.setTurma(rs.getString("turma"));
                aluno.setIdEscola(rs.getInt("fk_escola"));

                return aluno;
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }
        return null;
    }

}
