
public class Post {

	String data;
	String hora;
	String conteudo;
	
	public static void main(String[] args) {
		
	}
	
	static void timeline() {
		for(Post p : Perfil.posts) {
			System.out.print(p.data + " às " + p.hora + " -- " + p.conteudo);
			System.out.println();
		}
	}


}
