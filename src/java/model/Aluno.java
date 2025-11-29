/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *  Document   : Classe Aluno
 *  Created on : 18 de nov. de 2025
 *  Author     : Vitória Piconi
 */
public class Aluno {
    // ATRIBUTOS
    private int id;
    private String nome;
    private String cpf;
    private String ra;
    private String turma;
    private int idEscola;
    
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
   
    public void setRa(String p_ra) {
        this.ra = p_ra;
    }

    public void setTurma(String p_turma) {
        this.turma = p_turma;
    }

    public void setIdEscola(int p_idEscola) {
        this.idEscola = p_idEscola;
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

    public String getRa() {
        return this.ra;
    }

    public String getTurma() {
        return this.turma;
    }    

    public int getIdEscola() {
        return this.idEscola;
    }
    
}
