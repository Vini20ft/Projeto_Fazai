package http;

public class EstabelecimentoHttp {
	
	private int cnpj;
	private String especialidade;
	private String nome;
	private String razaoSocial;
	
	public EstabelecimentoHttp(int cnpj, String especialidade, String nome,
			String razaoSocial) {
		super();
		this.cnpj = cnpj;
		this.especialidade = especialidade;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

}
