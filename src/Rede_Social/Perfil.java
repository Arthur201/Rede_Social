package Rede_Social;

import java.util.*;

public class Perfil {

	protected String nome;
	private String login;
	private String senha;
	private ArrayList<Rede_Social.Post> posts = new ArrayList<>();

	public Perfil(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return this.login;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public void getTimeline() {
		for (Rede_Social.Post p : posts) {
			System.out.print(p.data + " às " + p.hora + " -- " + p.conteudo);
			System.out.println();
		}
	}
	public void postar(String conteudo) {
		Rede_Social.Post post = new Rede_Social.Post(conteudo);
		posts.add(post);

	}
}


	

