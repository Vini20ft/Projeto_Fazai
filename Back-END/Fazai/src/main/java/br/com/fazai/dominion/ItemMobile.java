package br.com.fazai.dominion;

public class ItemMobile {

    private int codigo;
    private String nome;
    private String imagem;
    private String descricao;
    private double valor;
    private String tempo_estimado;

    public int getCodigo() {
	return codigo;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getImagem() {
	return imagem;
    }

    public void setImagem(String imagem) {
	this.imagem = imagem;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public double getValor() {
	return valor;
    }

    public void setValor(double valor) {
	this.valor = valor;
    }

    public String getTempo_estimado() {
	return tempo_estimado;
    }

    public void setTempo_estimado(String tempo_estimado) {
	this.tempo_estimado = tempo_estimado;
    }

}
