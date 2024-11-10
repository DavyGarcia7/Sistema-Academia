/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loja;

/**
 *
 * @author joaop
 */
public class ProdutosLoja {
    private String setor;
    protected int id;
    private String nome;
    private int quantidade;
    private double preco;
    public ProdutosLoja(int id,String nome,int quantidade,double preco)
    {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        
    }
    public int getId(){
        return id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getNome()
    {
        return nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public int getQuantidade()
    {
        return quantidade;
    }
    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }
    public double getPreco()
    {
        return preco;
    }
    public void setPreco(double preco)
    {
        this.preco = preco;
    }
            
    

}