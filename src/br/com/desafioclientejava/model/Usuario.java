package br.com.desafioclientejava.model;

public class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String tel;

    public Usuario(String nome, String cpf, String email, String tel) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.tel = tel;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
