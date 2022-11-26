
public class Post {

	String data;
	String hora;
	String conteudo;
	
	public static void main(String[] args) {
		
	}
	
	static void timeline() {
		for(int i = 0; i< Perfil.qtdPosts;i++) {
			System.out.print(Perfil.posts[i].data + " às " + Perfil.posts[i].hora + " -- " + Perfil.posts[i].conteudo);
			System.out.println();
		}
	}


}
