import java.util.*;

public class Perfil {
	
	public String nome;
	private String login;
	private String senha;
	static Post[] posts = new Post[100];
	static int qtdPosts = 0;
	
	public Perfil(String nome, String login, String senha){
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
		
	public static void postar(String data, String hora, String conteudo) {		
		Post p = new Post(data,hora,conteudo);
		posts[qtdPosts++] = p;
	}
	
	public static void cadastro() {
		
		Scanner sc = new Scanner(System.in);

		System.out.print("Por favor, digite o nome de usuário que você deseja utilizar: ");
		String nome = sc.next();
		
		System.out.print("Por favor, digite o email que você deseja utilizar: ");
		String login = sc.next();
		
		System.out.print("Por favor, digite a senha você deseja utilizar: ");
		sc.nextLine();
		String senha = sc.next();
		
		Perfil pessoa = new Perfil(nome,login, senha);

		RedeSocial.usuarios[RedeSocial.qtdUsers] = pessoa;
		RedeSocial.qtdUsers++;
		RedeSocial.menu();
		
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String senhaEstaCorreta(String senha) {
		if(senha.equals(this.senha)) {
			return "Senha correta";
		} else {
			return "Senha incorreta";
		}
		
	}
	
	public String getLogin() {
		return this.login;
	}
}
	

