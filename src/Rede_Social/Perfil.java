package Rede_Social;

import java.util.*;

public class Perfil {

	private String nome;
	private String login;
	private String senha;
	private ArrayList<Post> posts = new ArrayList<>();

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
		for (Post p : posts) {
			System.out.print(p.getData() + " às " + p.getHora() + " -- " + p.getConteudo());
			System.out.println();
		}
	}
	public void postar(String conteudo) {
		Post post = new Post(conteudo);
		posts.add(post);

	}
}


	

