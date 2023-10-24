package model.factory;

import model.Personagem;

public class FactoryPersonagem {
	
	public static Personagem criaPersonagem(String nome, Integer idade, String sexo, Double altura) {
		Personagem personagem = new Personagem(nome,idade, sexo, altura);
		return personagem;
	}
}
