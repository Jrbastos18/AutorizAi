/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import config.ConectaDB;
import java.sql.*;
import model.Endereco;

/**
 * Document : Classe Endereço DAO - Data Access Object 
 * Created on : 19 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class EnderecoDAO {
    //Atributos

    //Métodos
    //+ cadastrar( Endereco ): int
    public int cadastrar(Endereco endereco) throws ClassNotFoundException {
        Connection conn = null;
        try {
            conn = ConectaDB.conectar();
            //String sql = "INSERT INTO endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
            String sql = "INSERT INTO endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            // Faz a inserção do endereço e vai retornar a chave primária gerada
            PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preStmt.setString(1, endereco.getCep());
            preStmt.setString(2, endereco.getLogradouro());
            preStmt.setString(3, endereco.getNumero());
            preStmt.setString(4, endereco.getComplemento());
            preStmt.setString(5, endereco.getBairro());
            preStmt.setString(6, endereco.getCidade());
            preStmt.setString(7, endereco.getUf().trim()); // Trim para evitar que tenha espaço na String, pois no banco de dados é um CHAR(2)
            preStmt.setString(8, endereco.getEstado());

            //Executando a inserção
            preStmt.executeUpdate();

            // Pegar o resultado do retorno da pk_id gerada
            ResultSet rs = preStmt.getGeneratedKeys();
            
            int idGerado = -1;
            if (rs.next()) {
                idGerado = rs.getInt(1); // Retorna a pk_id do endereço cadastrado
            }

            // Fechando a conexão e o PreparedStatement
            rs.close();
            preStmt.close();
            conn.close();

            return idGerado;
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
            return -1;
        }
    }
}
