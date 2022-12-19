package Rede_Social;

import java.util.*;

public class RedeSocial {

    protected ArrayList<Perfil> usuarios = new ArrayList<Perfil>();
    static String resposta;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        RedeSocial redeSocial = new RedeSocial();
        redeSocial.menuInicial();
    }

    protected void menuInicial() {
        try {
            System.out.print("Bem vindo(a) à Rede Social! O que deseja fazer?\n Digite 'C' para Cadastrar-se\n Digite 'E' para entrar caso já tenha uma conta \n Digite 'F' para sair");
            resposta = sc.next();

            if (!resposta.equalsIgnoreCase("C") & !resposta.equalsIgnoreCase("E") & !resposta.equalsIgnoreCase("F")) {
                throw new InputMismatchException("Não entendemos sua requisição. Tente novamente! ");
            } else {
                validarRequisicao();
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            menuInicial();
        }

    }

    protected void isCadastrado() {
        boolean tem = false;
        String login;
        System.out.println("Digite seu login: ");
        login = sc.next();

        if (usuarios.size() == 0) {
            System.out.print("Você ainda não possui uma conta. Deseja cadastrar-se?\n 'S' para sim\n 'N' para não ");
            resposta = sc.next();

            if (resposta.equalsIgnoreCase("S")) {
                cadastrar();
            } else if (resposta.equalsIgnoreCase("N")) {
                menuInicial();
            }
        } else {
            for (Perfil usuario : usuarios) {
                if (usuario.getLogin().equals(login)) {
                    entrar(login);
                }

            }
            System.out.print("Você ainda não possui uma conta. Deseja cadastrar-se?\n 'S' para sim\n 'N' para não ");
            resposta = sc.next();

            if (resposta.equals("S")) {
                cadastrar();
            } else if (resposta.equals("N")) {
                menuInicial();
            }
        }
    }

    protected void fechar() {
        System.out.println("Application Closed!");
        System.exit(0);
    }

    protected void entrar(String login) {
        String senha;
        boolean achou = false;
        int i = 0;

        if (i == 3) {
            System.out.println("Tentativas esgotadas");
            isCadastrado();
        }

        for (Perfil usuario : usuarios) {
            if (login.equals(usuario.getLogin())) {
                System.out.println("Digite sua senha: ");
                senha = sc.next();
                achou = false;
                try {
                    if (senha.equals(usuario.getSenha())) {
                        menuUsuario(usuario);
                        achou = true;

                    } else {
                        throw new SenhaIncorretaException("Senha incorreta");
                    }
                } catch (SenhaIncorretaException e) {
                    System.out.println(e.getMessage());
                    entrar(login);
                }
            }
            if(achou){
                System.out.println("Usuário não encontrado. Por favor, faça um cadastro para continuar");
                cadastrar();
            }
        }
    }


    protected void menuUsuario(Perfil user) {
        String nome = user.getNome();
        Scanner respostaMenuInterno = new Scanner(System.in);
        String resposta;
        System.out.println("Bem vindo " + nome);
        System.out.print("O que você deseja fazer? \n Digite 'p' para fazer uma postagem \n Digite 't' para visualizar sua timeline \n Digite 's' para sair ");

        try {
            resposta = respostaMenuInterno.next();

            if (!resposta.equalsIgnoreCase("p") & !resposta.equalsIgnoreCase("t") & !resposta.equalsIgnoreCase("s")) {
                throw new InputMismatchException("Não entendemos sua solicitação.");

            } else if (resposta.equalsIgnoreCase("p")) {
                fazerPostagem(user);
                menuUsuario(user);

            } else if (resposta.equalsIgnoreCase("t")) {
                user.getTimeline();
                menuUsuario(user);

            } else if (resposta.equalsIgnoreCase("s")) {
                System.out.println("Logout feito!");
                menuInicial();
            }
        } catch (InputMismatchException e) {
            menuUsuario(user);
        }

    }

    protected void validarRequisicao() {

        if (resposta.equalsIgnoreCase("C")) {
            cadastrar();
        } else if (resposta.equalsIgnoreCase("E")) {
            isCadastrado();
        } else if (resposta.equalsIgnoreCase("F")) {
            fechar();
        }
    }

    protected void fazerPostagem(Perfil user) {
        String conteudo;
        System.out.println("Escreva o que você está pensando: ");
        sc.nextLine();
        conteudo = sc.nextLine();

        user.postar(conteudo);

    }

    protected void cadastrar() {
        String nome, login, senha;
        Scanner sc = new Scanner(System.in);
        try{
            System.out.print("Por favor, digite o nome de usuário que você deseja utilizar: ");
            nome = sc.next();
            System.out.print("Por favor, digite o email que você deseja utilizar: ");
            login = sc.next();
            System.out.print("Por favor, digite a senha você deseja utilizar: ");
            sc.nextLine();
            senha = sc.next();

            validarUsername(nome);

            usuarios.add(new Perfil(nome, login, senha));
            menuInicial();

        } catch (UsuarioJaExisteException e) {
            e.getMessage();
            menuInicial();
        }
    }
    protected void validarUsername(String nomeUsuario) throws UsuarioJaExisteException {
        for (Perfil usuario : usuarios) {
            if (nomeUsuario.equals(usuario.getNome())) {
                throw new UsuarioJaExisteException("Este nome de usuário já existe.");
            }
        }
    }

}
