/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import config.ConectaDB;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Login;

/**
 * Document : Classe Login DAO - Data Access Object 
 * Created on : 19 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class LoginDAO {
    //Atributos

    //Métodos
    //+ cadastrar( login ): int
    public int cadastrar(Login login) throws ClassNotFoundException {
        Connection conn = null;
        try {
            conn = ConectaDB.conectar();
            //String sql = "INSERT INTO login (email, senha, tipo_usuario, status_conta) VALUES (?, ?, ?, ?)";  
            String sql = "INSERT INTO login (email, senha, tipo_usuario, status_conta) VALUES (?, ?, ?, ?)";
            // Faz a inserção do login e vai retornar a chave primária gerada
            PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preStmt.setString(1, login.getEmail());
            preStmt.setString(2, login.getSenha());
            preStmt.setString(3, login.getTipo_usuario());
            preStmt.setString(4, login.getStatus_conta());


            //Executando a inserção
            preStmt.executeUpdate();

            // Pegar o resultado do retorno da pk_id gerada
            ResultSet rs = preStmt.getGeneratedKeys();
            
            int idGerado = -1;
            if (rs.next()) {
                idGerado = rs.getInt(1); // Retorna a pk_id do login cadastrado
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
    
    //+ autenticar( String email, String senha ): Login
    public Login autenticar (String email, String senha) throws ClassNotFoundException {
        Connection conn = null;
        
        try {
            conn = ConectaDB.conectar();
            //String sql = "SELECT * FROM login WHERE email = ? AND senha = ?";  
            String sql = "SELECT * FROM login WHERE email = ? AND senha = ?";
            
            PreparedStatement preStmt = conn.prepareStatement(sql);
            
            preStmt.setString(1, email);
            
            //Rotina de criptografia - segurança
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(senha.getBytes(), 0, senha.length());
            String senha_cript = new BigInteger(1, md5.digest()).toString(16);
            
            
            preStmt.setString(2, senha_cript);
            
            ResultSet rs = preStmt.executeQuery();
            
            if (rs.next()) {
                Login login = new Login();
                login.setId(rs.getInt("pk_id"));
                login.setEmail(rs.getString("email"));
                login.setSenha(rs.getString("senha"));
                login.setTipo_usuario(rs.getString("tipo_usuario"));
                return login;
            }
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Erro: " + ex);
        }

        return null;
    }
    
    // + atualizaUltimoLogin( int idLogin ) : void
    public void atualizaUltimoLogin(int idLogin) throws ClassNotFoundException {
        Connection conn = null;
        
        try {
            conn = ConectaDB.conectar();
            // "UPDATE login SET ultimo_login = NOW() WHERE pk_id = ?"
            String sql = "UPDATE login SET ultimo_login = NOW() WHERE pk_id = ?";
            
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, idLogin);
            preStmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex);
        }
    }
}
