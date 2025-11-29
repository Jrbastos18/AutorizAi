/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *  Document   : Classe Responsável
 *  Created on : 18 de nov. de 2025
 *  Author     : Vitória Piconi
 */
public class Responsavel {
    // ATRIBUTOS
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private int idLogin;
    
    // MÉTODOS
    // SETTERS
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setNome(String p_nome) {
        this.nome = p_nome;
    }

    public void setCpf(String p_cpf) {
        this.cpf = p_cpf;
    }

    public void setTelefone(String p_telefone) {
        this.telefone = p_telefone;
    }

    public void setEmail(String p_email) {
        this.email = p_email;
    }

    public void setIdLogin(int p_idLogin) {
        this.idLogin = p_idLogin;
    }
    
   
    // GETTERS
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public int getIdLogin() {
        return this.idLogin;
    }
    
}
