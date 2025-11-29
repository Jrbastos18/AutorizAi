/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.sql.Blob;

/**
 *  Document   : Classe Resposta Autorização
 *  Created on : 18 de nov. de 2025
 *  Author     : Zenalvo Junior
 */
public class RespostaAutorizacao {
    // ATRIBUTOS
    private int id;
    private String status_resposta; // EM VERSÃO FUTURA SERÁ TRANSFORMADO EM ENUM
    private Date data_resposta; 
    private String assinatura_digital;
    private Blob pdf_gerado;

    // MÉTODOS
    // SETTERS
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setStatus_resposta(String p_status_resposta) {
        this.status_resposta = p_status_resposta;
    }

    public void setData_resposta(Date p_data_resposta) {
        this.data_resposta = p_data_resposta;
    }

    public void setAssinatura_digital(String p_assinatura_digital) {
        this.assinatura_digital = p_assinatura_digital;
    }

    public void setPdf_gerado(Blob p_pdf_gerado) {
        this.pdf_gerado = p_pdf_gerado;
    }
    
    // GETTERS
    public int getId() {
        return this.id;
    }

    public String getStatus_resposta() {
        return this.status_resposta;
    }

    public Date getData_resposta() {
        return this.data_resposta;
    }

    public String getAssinatura_digital() {
        return this.assinatura_digital;
    }

    public Blob getPdf_gerado() {
        return this.pdf_gerado;
    }  
}
