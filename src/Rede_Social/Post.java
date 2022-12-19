package Rede_Social;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

	private final String LocalDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	private final String LocalHour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
	private String data;
	private String hora;
	private String conteudo;

	public Post(String conteudo){
		this.conteudo = conteudo;
		this.data = LocalDate;
		this.hora = LocalHour;
	}

	public String getConteudo(){
		return this.conteudo;
	}

	public String getData(){
		return this.data;
	}

	public String getHora(){
		return this.hora;
	}

}


