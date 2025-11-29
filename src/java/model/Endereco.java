/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *  Document   : Classe Endereço
 *  Created on : 18 de nov. de 2025
 *  Author     : Vitória Piconi
 */
public class Endereco {
    // ATRIBUTOS
    private int id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String estado;
    
    // MÉTODOS
    // SETTERS
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setCep(String p_cep) {
        this.cep = p_cep;
    }

    public void setLogradouro(String p_logradouro) {
        this.logradouro = p_logradouro;
    }

    public void setNumero(String p_numero) {
        this.numero = p_numero;
    }

    public void setComplemento(String p_complemento) {
        this.complemento = p_complemento;
    }

    public void setBairro(String p_bairro) {
        this.bairro = p_bairro;
    }

    public void setCidade(String p_cidade) {
        this.cidade = p_cidade;
    }

    public void setUf(String p_uf) {
        this.uf = p_uf;
    }

    public void setEstado(String p_estado) {
        this.estado = p_estado;
    }
    
    // GETTERS
    public int getId() {
        return this.id;
    }

    public String getCep() {
        return this.cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getUf() {
        return this.uf;
    }

    public String getEstado() {
        return this.estado;
    }
}
