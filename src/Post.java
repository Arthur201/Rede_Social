
public class Post {

	public String data;
	public String hora;
	public String conteudo;

	public Post(String data, String hora, String conteudo){
		this.data = data;
		this.hora = hora;
		this.conteudo = conteudo;
	}
	
	static void timeline() {
		for(int i = 0; i< Perfil.qtdPosts;i++) {
			System.out.print(Perfil.posts[i].data + " às " + Perfil.posts[i].hora + " -- " + Perfil.posts[i].conteudo);
			System.out.println();
		}
	}


}
