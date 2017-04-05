package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;

public class Funcionario {
    @SerializedName("id")
    public int id;
    @SerializedName("nome")
    public String nome;
    @SerializedName("email")
    public String email;
    @SerializedName("login")
    public String login;
    @SerializedName("senha")
    public String senha;

    public Endereco endereco;

    public Funcionario() {
        endereco = new Endereco();
    }

    public Funcionario(int id, String nome, String email, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }
}