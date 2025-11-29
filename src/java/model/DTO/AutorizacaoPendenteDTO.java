/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import java.util.Date;

/**
 * Document : Classe DTO (Data Transfer Object) de Autorização Pendente para
 * facilitar na exibição, juntando informações de tabelas diferentes do banco de dados 
 * Created on : 28 de nov. de 2025 
 * Author : Zenalvo Junior
 */
public class AutorizacaoPendenteDTO {
    // ATRIBUTOS
    private int idResposta; // ID da tabela resposta_autorizacao (para dar o update)
    private String tituloAutorizacao;
    private String descricaoAutorizacao;
    private String nomeAluno;
    private Date dataLimite;

    // CONSTRITOR
    public AutorizacaoPendenteDTO(int p_idResposta, String p_tituloAutorizacao, String p_descricaoAutorizacao, String p_nomeAluno, Date p_dataLimite) {
        this.idResposta = p_idResposta;
        this.tituloAutorizacao = p_tituloAutorizacao;
        this.descricaoAutorizacao = p_descricaoAutorizacao;
        this.nomeAluno = p_nomeAluno;
        this.dataLimite = p_dataLimite;
    }

    // GETTERS
    public int getIdResposta() {
        return this.idResposta;
    }

    public String getTituloAutorizacao() {
        return this.tituloAutorizacao;
    }

    public String getDescricaoAutorizacao() {
        return this.descricaoAutorizacao;
    }

    public String getNomeAluno() {
        return this.nomeAluno;
    }

    public Date getDataLimite() {
        return this.dataLimite;
    }
}
