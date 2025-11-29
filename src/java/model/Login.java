/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *  Document   : Classe Endereço
 *  Created on : 18 de nov. de 2025
 *  Author     : Vitória Piconi
 */
public class Login {
    // ATRIBUTOS
    private int id;
    private String email;
    private String senha;
    private String tipo_usuario; // EM VERSÃO FUTURA SERÁ TRANSFORMADO EM ENUM
    private String status_conta; // EM VERSÃO FUTURA SERÁ TRANSFORMADO EM ENUM
    private Date data_criacao;
    private Date ultimo_login;
    
    // MÉTODOS
    // SETTERS
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setEmail(String p_email) {
        this.email = p_email;
    }

    public void setSenha(String p_senha) {
        this.senha = p_senha;
    }

    public void setTipo_usuario(String p_tipo_usuario) {
        this.tipo_usuario = p_tipo_usuario;
    }

    public void setStatus_conta(String p_status_conta) {
        this.status_conta = p_status_conta;
    }

    public void setData_criacao(Date p_data_criacao) {
        this.data_criacao = p_data_criacao;
    }

    public void setUltimo_login(Date p_ultimo_login) {
        this.ultimo_login = p_ultimo_login;
    }
    
    // GETTERS
    public int getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getTipo_usuario() {
        return this.tipo_usuario;
    }

    public String getStatus_conta() {
        return this.status_conta;
    }

    public Date getData_criacao() {
        return this.data_criacao;
    }

    public Date getUltimo_login() {
        return this.ultimo_login;
    }
}
