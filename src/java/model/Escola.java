/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Blob;

/**
 *  Document   : Classe Escola
 *  Created on : 18 de nov. de 2025
 *  Author     : Zenalvo Junior
 */
public class Escola {
    // ATRIBUTOS
    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private java.sql.Blob logo;
    private int idLogin;
    
    // MÉTODOS
    // SETTERS
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setNome(String p_nome) {
        this.nome = p_nome;
    }

    public void setCnpj(String p_cnpj) {
        this.cnpj = p_cnpj;
    }

    public void setTelefone(String p_telefone) {
        this.telefone = p_telefone;
    }

    public void setEmail(String p_email) {
        this.email = p_email;
    }

    public void setLogo(Blob p_logo) {
        this.logo = p_logo;
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

    public String getCnpj() {
        return this.cnpj;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public Blob getLogo() {
        return this.logo;
    }    

    public int getIdLogin() {
        return this.idLogin;
    }
    
}
