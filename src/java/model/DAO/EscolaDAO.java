/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import java.sql.*;
import config.ConectaDB;
import model.Escola;

/**
 *  Document   : Classe Escola DAO - Data Access Object
 *  Created on : 18 de nov. de 2025
 *  Author     : Zenalvo Junior
 */
public class EscolaDAO {
    //Atributos
    
    //Métodos
    //+ cadastrar( Escola, idLogin, idEndereco ): boolean
    public boolean cadastrar(Escola escola, int idLogin, int idEndereco) throws ClassNotFoundException{
        Connection conn = null;
        try{
            conn = ConectaDB.conectar();
            //String sql = "INSERT INTO escola (nome, cnpj, telefone, email, fk_login, fk_endereco) VALUES (?, ?, ?, ?, ?, ?);";  
            String sql = "INSERT INTO escola (nome, cnpj, telefone, email, fk_login, fk_endereco) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preStmt = conn.prepareStatement(sql);
            
            preStmt.setString(1, escola.getNome());
            preStmt.setString(2, escola.getCnpj());
            preStmt.setString(3, escola.getTelefone());
            preStmt.setString(4, escola.getEmail());
            preStmt.setInt(5, idLogin); // Vai receber o id do Login cadastrado pela escola
            preStmt.setInt(6, idEndereco); // Vai receber o id do Endereço cadastrado pela escola

            //Executando a inserção
            int resultado = preStmt.executeUpdate();
            
            // Fechando a conexão e o PreparedStatement
            conn.close();
            preStmt.close();

            return resultado > 0; // Se o resultado for maior que 0 ele retorna "true", caso contrário, retorna "false"         
        }catch(SQLException ex){
            System.out.println("Erro - SQL: " + ex);
            return false;
        }
    }

    //+ buscaPorIdLogin( idLogin ): Escola
    public Escola buscarPorIdLogin(int idLogin) {
        Connection conn = null;
        try {
            conn = ConectaDB.conectar();

            String sql = "SELECT * FROM escola WHERE fk_login = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idLogin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Escola escola = new Escola();
                escola.setId(rs.getInt("pk_id"));
                escola.setNome(rs.getString("nome"));
                escola.setCnpj(rs.getString("cnpj"));
                escola.setEmail(rs.getString("email"));
                escola.setTelefone(rs.getString("telefone"));
                escola.setIdLogin(rs.getInt("fk_login"));
                
                return escola;
            }

        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro - SQL: " + ex);
        }
        return null;
    }
}
