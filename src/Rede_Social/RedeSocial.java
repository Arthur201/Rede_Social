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

    private void menuInicial() {
        try {
            System.out.print("Bem vindo(a) à Matrix, a sua Rede Social! O que deseja fazer?\n Digite 'C' para Cadastrar-se\n Digite 'E' para entrar caso já tenha uma conta \n Digite 'F' para sair");
            resposta = sc.next();

            if (!resposta.equalsIgnoreCase("C") & !resposta.equalsIgnoreCase("E") & !resposta.equalsIgnoreCase("F")) {
                throw new InputMismatchException("Não entendemos sua requisição. Tente novamente! ");
            } else {
                validarRequisicaoDoUsuario();
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            menuInicial();
        }

    }

    private void isCadastrado() {
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

            if (resposta.equalsIgnoreCase("S")) {
                cadastrar();
            } else if (resposta.equalsIgnoreCase("N")) {
                menuInicial();
            }
        }
    }

    private void fechar() {
        System.out.println("Matrix desligada com sucesso! Por favor, volte em breve!");
        System.exit(0);
    }

    private void entrar(String login) {
        String senha;
        boolean achou = false;
        int i = 0;

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


    private void menuUsuario(Perfil user) {
        Scanner respostaMenuInterno = new Scanner(System.in);
        String resposta;
        System.out.println("Bem vindo à Matrix  " + user.getNome() + " !!!");
        System.out.print("O que você deseja fazer? \n Digite 'p' para fazer uma postagem \n Digite 't' para visualizar sua timeline \n Digite 's' para sair \n Digite '1' para alterar seu username \n Digite '2' para alterar sua senha ");

        try {
            resposta = respostaMenuInterno.next();

            if (!resposta.equalsIgnoreCase("p") & !resposta.equalsIgnoreCase("t") & !resposta.equalsIgnoreCase("s") & !resposta.equals("1") & !resposta.equals("2")){
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

            } else if (resposta.equals("1")) {
                alterarUsername(user);

            } else if (resposta.equals("2")) {
                alterarSenha(user);
            }

        } catch (InputMismatchException e) {
            menuUsuario(user);
        }

    }

    private void validarRequisicaoDoUsuario() {

        if (resposta.equalsIgnoreCase("C")) {
            cadastrar();
        } else if (resposta.equalsIgnoreCase("E")) {
            isCadastrado();
        } else if (resposta.equalsIgnoreCase("F")) {
            fechar();
        }
    }

    private void fazerPostagem(Perfil user) {
        String conteudo;
        System.out.println("Escreva o que você está pensando: ");
        sc.nextLine();
        conteudo = sc.nextLine();

        user.postar(conteudo);

    }

    private void cadastrar() {
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
            validarLogin(login);

            usuarios.add(new Perfil(nome, login, senha));

            System.out.println("Cadastro realizado com sucesso! Parabéns e Bem Vindo à Matrix !");
            menuInicial();

        } catch (UsuarioJaExisteException | LoginJaExisteException e) {
            System.out.println(e.getMessage());
            cadastrar();
        }
    }
    private void validarUsername(String nomeUsuario) throws UsuarioJaExisteException {
        for (Perfil usuario : usuarios) {
            if (nomeUsuario.equals(usuario.getNome())) {
                throw new UsuarioJaExisteException("Este nome de usuário já existe.");
            }
        }
    }
    private void validarLogin(String login) throws LoginJaExisteException {
        for (Perfil usuario : usuarios) {
            if (login.equals(usuario.getLogin())) {
                throw new LoginJaExisteException("Já existe uma conta cadastrada com esse login.");
            }
        }
    }

    private void alterarSenha(Perfil usuario){
        System.out.println("Digite sua nova senha: ");
        String novaSenha = sc.next();
        usuario.setSenha(novaSenha);

        System.out.println("Senha alterada com sucesso!");
        menuUsuario(usuario);
    }

    private void alterarUsername(Perfil usuario){
        System.out.println("Digite seu novo nome de usuário: ");
        String novoUsername = sc.next();
        try {
            validarUsername(novoUsername);
            usuario.setNome(novoUsername);

            System.out.println("Parabéns " + usuario.getNome() +  " seu username foi alterado com sucesso!");
            menuUsuario(usuario);
        } catch (UsuarioJaExisteException e) {
            System.out.println(e.getMessage());
        }
    }

}
