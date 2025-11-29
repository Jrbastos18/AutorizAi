/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *  Document   : Classe Destino da Autorização
 *  Created on : 26 de nov. de 2025
 *  Author     : Zenalvo Junior
 */
public class DestinoAutorizacao {
    // ATRIBUTOS
    private int id;
    private int idAutorizacao;
    private String turmaDestino; // pode ser NULL
    private boolean enviarParaTodos;
    
    // MÉTODOS
    // SETTERS
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setIdAutorizacao(int p_idAutorizacao) {
        this.idAutorizacao = p_idAutorizacao;
    }

    public void setTurmaDestino(String p_turmaDestino) {
        this.turmaDestino = p_turmaDestino;
    }

    public void setEnviarParaTodos(boolean p_enviarParaTodos) {
        this.enviarParaTodos = p_enviarParaTodos;
    }
    
    //GETTERS
    public int getId() {
        return this.id;
    }

    public int getIdAutorizacao() {
        return this.idAutorizacao;
    }

    public String getTurmaDestino() {
        return this.turmaDestino;
    }

    public boolean isEnviarParaTodos() {
        return this.enviarParaTodos;
    }  
}
