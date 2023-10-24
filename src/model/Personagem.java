package model;

public class Personagem {
	
	String nome;
	Integer idade;
	String sexo;
	Double altura;
	
	public Personagem(String nome, Integer idade, String sexo, Double altura) {
		this.nome = nome;
		this.idade = idade;
		this.sexo =sexo;
		this.altura = altura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}
	
	public String toString() {
		return "%personagem%"
				+ "%nome%" + getNome() + "%/nome%"
					+ "%idade%" + getIdade() + "%/idade%"
							+ "%sexo%" + getSexo() + "%/sexo%"
									+ "%altura%" + getAltura() + "%/altura%"
											+ "%/personagem%";
	}
	
}
