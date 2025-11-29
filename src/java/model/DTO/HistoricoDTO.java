/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import java.util.Date;

/**
 * Document : Classe DTO (Data Transfer Object) de Histórico para facilitar na
 * exibição na lista do histórico de Autorizações do responsável 
 * Created on : 28 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class HistoricoDTO {
    // ATRIBUTOS
    private String titulo;
    private Date dataLimite;
    private Date dataResposta; // Pode ser nulo se estiver pendente
    private String statusResposta;
    private String nomeAluno;
    private String turma;

    // MÉTODOS
    public HistoricoDTO(String p_titulo, Date p_dataLimite, Date p_dataResposta, String p_statusResposta, String p_nomeAluno, String p_turma) {
        this.titulo = p_titulo;
        this.dataLimite = p_dataLimite;
        this.dataResposta = p_dataResposta;
        this.statusResposta = p_statusResposta;
        this.nomeAluno = p_nomeAluno;
        this.turma = p_turma;
    }

    // GETTERS
    public String getTitulo() {
        return this.titulo;
    }

    public Date getDataLimite() {
        return this.dataLimite;
    }

    public Date getDataResposta() {
        return this.dataResposta;
    }

    public String getStatusResposta() {
        return this.statusResposta;
    }

    public String getNomeAluno() {
        return this.nomeAluno;
    }

    public String getTurma() {
        return this.turma;
    }
}
