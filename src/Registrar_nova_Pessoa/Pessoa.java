/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Registrar_nova_Pessoa;

/**
 *
 * @author davyc joao pedro
 */
public class Pessoa {
    private String nome;
    int id;
    private String cpf;
 
    

    public Pessoa(String nome, String cpf, String cpf1, String senha) {
        this.nome = nome;
        this.id = id;
        this.cpf = cpf;
        
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    

@Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", cpf='" + cpf + '\'' +
                '}';
}
}