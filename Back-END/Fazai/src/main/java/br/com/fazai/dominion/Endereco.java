package br.com.fazai.dominion;

public class Endereco {
    
    

    private Localizacao localizacao;
    private String cidade;
    private String estado;
    
    public Localizacao getLocalizacao() {
	return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
	this.localizacao = localizacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
