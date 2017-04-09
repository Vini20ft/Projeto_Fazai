package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;


public class Consumidor {

    @SerializedName("id")
    public String nome;
    @SerializedName("email")
    public String email;
    @SerializedName("cpf")
    public String cpf;
    @SerializedName("telefone")
    public String telefone;

    public Consumidor() {
    }

    public Consumidor(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }
}
