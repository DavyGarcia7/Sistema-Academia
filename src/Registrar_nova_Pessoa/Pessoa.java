package Registrar_nova_Pessoa;

public class Pessoa {
    private String nome;
    protected int id;  // Alterado para protected para permitir acesso em subclasses
    private String cpf;
    private String senha;

    public Pessoa(String nome, int id, String cpf, String senha) {
        this.nome = nome;
        this.id = id;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters e Setters para acessar os campos privados, se necessário
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }}