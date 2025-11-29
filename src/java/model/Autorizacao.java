/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.sql.Blob;

/**
 *  Document   : Classe Autorização
 *  Created on : 18 de nov. de 2025
 *  Author     : Vitória Piconi
 */
public class Autorizacao {
    // ATRIBUTOS
    private int id;
    private String titulo;
    private String descricao;
    private Date data_envio; 
    private Date data_limite;
    private Blob documento_pdf;
    private String status_global; // EM VERSÃO FUTURA SERÁ TRANSFORMADO EM ENUM

    // MÉTODOS
    // SETTERS
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setTitulo(String p_titulo) {
        this.titulo = p_titulo;
    }

    public void setDescricao(String p_descricao) {
        this.descricao = p_descricao;
    }

    public void setData_envio(Date p_data_envio) {
        this.data_envio = p_data_envio;
    }

    public void setData_limite(Date p_data_limite) {
        this.data_limite = p_data_limite;
    }

    public void setDocumento_pdf(Blob p_documento_pdf) {
        this.documento_pdf = p_documento_pdf;
    }

    public void setStatus_global(String p_status_global) {
        this.status_global = p_status_global;
    }
    
    // GETTERS
    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Date getData_envio() {
        return this.data_envio;
    }

    public Date getData_limite() {
        return this.data_limite;
    }

    public Blob getDocumento_pdf() {
        return this.documento_pdf;
    }

    public String getStatus_global() {
        return this.status_global;
    }   
}
