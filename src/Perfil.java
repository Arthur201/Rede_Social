import java.util.*;

public class Perfil {
	
	public String nome;
	public String login;
	public String senha;
	static Post[] posts = new Post[100];
	static int qtdPosts = 0;
	
	public static void main(String[] args) {
		
	}
	
	public static void postar(String data, String hora, String conteudo) {		
		Post p = new Post();
		p.data = data;
		p.hora = hora;
		p.conteudo = conteudo;
		
		posts[qtdPosts++] = p;
	}
	
	public static void cadastro() {
		Perfil pessoa = new Perfil();
		Scanner sc = new Scanner(System.in);

		System.out.println("Por favor, digite o nome de usuário que você deseja utilizar: ");
		pessoa.nome = sc.next();
		System.out.println("Por favor, digite o email que você deseja utilizar: ");
		pessoa.login = sc.next();
		System.out.println("Por favor, digite a senha você deseja utilizar: ");
		pessoa.senha = sc.next();

		RedeSocial.usuarios[RedeSocial.qtdUsers++] = pessoa;
		
		RedeSocial.menu();
		
	}
	
}
	

