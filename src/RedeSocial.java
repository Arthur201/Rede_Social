import java.util.*;

public class RedeSocial {

	public static Perfil[] usuarios = new Perfil[100];
	public static int qtdUsers = 0;
	static String resposta;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		menu();

	}

	static void menu() {
		System.out.print(
				"Bem vindo(a) à Rede Social! O que deseja fazer?\n Digite 'C' para Cadastrar-se\n Digite 'E' para entrar caso já tenha uma conta \n Digite 'F' para sair");
		resposta = sc.next();
		validacaoRequisicao();
	}

	static void temCadastro() {
		boolean tem = false;
		String login;
		System.out.println("Digite seu login: ");
		login = sc.next();

		if (qtdUsers == 0) {
			System.out.print("Você ainda não possui uma conta. Deseja cadastrar-se?\n 'S' para sim\n 'N' para não ");
			resposta = sc.next();

			if (resposta.equals("S")) {
				// System.out.println("PASSOU AQUI!!");
				Perfil.cadastro();
			} else if (resposta.equals("N")) {
				// System.out.println("AQUI!!");
				menu();
			}
		} else {
			for (int i = 0; i < qtdUsers; i++) {
				if (usuarios[i].login.equals(login)) {
					tem = true;
					entrar(login);
				} else {
					tem = false;
				}
			}
		}
	}

	static void fechar() {
		System.out.println("Application Closed!");
		System.exit(0);
	}

	static void entrar(String login) {
		String senha;
		boolean achou = false;
		int i = 0;
		for (Perfil usuario : usuarios) {
			if (login.equals(usuario.login)) {
				System.out.println("Digite sua senha: ");
				senha = sc.next();
				achou = true;
				if (senha.equals(usuario.senha)) {
					menuUsuario(usuario.nome);
					break;
				} else {
					System.out.println("Senha incorreta");
					do {
						System.out.println("Digite novamente a senha: ");
						senha = sc.next();
						if (senha.equals(usuario.senha)) {
							menuUsuario(usuario.nome);
							break;
						}
						i++;
					} while(i<3);
					
					if(i==3) {
						System.out.println("Tentativas esgotadas");
						temCadastro();
					}
				}
			}
		}
		if(achou == false) {
			System.out.println("Usuário não encontrado. Por favor, faça um cadastro para continuar");
			Perfil.cadastro();
		}
	}

	static void menuUsuario(String nome) {
		Scanner respostaMenuInterno = new Scanner(System.in);
		String resposta;
		System.out.println("Bem vindo " + nome);
		System.out.print(
				"O que você deseja fazer? \n Digite 'p' para fazer uma postagem \n Digite 't' para visualizar sua timeline \n Digite 's' para sair ");
		resposta = respostaMenuInterno.next();

		if (resposta.equals("p")) {
			fazerPostagem();
			menuUsuario(nome);
		} else if (resposta.equals("t")) {
			Post.timeline();
			menuUsuario(nome);
		} else if (resposta.equals("s")) {
			System.out.println("Logout feito!");
			menu();
		}

	}

	static void validacaoRequisicao() {

		if (resposta.equals("C")) {
			Perfil.cadastro();
		} else if (resposta.equals("E")) {
			temCadastro();
		} else if (resposta.equals("F")) {
			fechar();
		}
	}

	static void fazerPostagem() {
		String data, hora, conteudo;
		System.out.println("Digite a data da postagem desejada: ");
		data = sc.next();
		System.out.println("Digite a hora da postagem: ");
		hora = sc.next();
		System.out.println("Escreva o conteúdo da sua postagem: ");
		sc.nextLine();
		conteudo = sc.nextLine();

		Perfil.postar(data, hora, conteudo);

	}

}
