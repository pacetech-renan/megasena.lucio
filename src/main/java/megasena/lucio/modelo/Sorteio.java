package megasena.lucio.modelo;

import java.util.Arrays;

public class Sorteio {

	private String data;

	private Integer concurso;

	private String[] dezenas;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}

	public String[] getDezenas() {
		return dezenas;
	}

	public void setDezenas(String[] dezenas) {
		this.dezenas = dezenas;
	}

	@Override
	public String toString() {
		return "Sorteio [data=" + data + ", concurso=" + concurso + ", dezenas=" + Arrays.toString(dezenas) + "]";
	}

	public Sorteio(String data, Integer concurso, String[] dezenas) {
		super();
		this.data = data;
		this.concurso = concurso;
		this.dezenas = dezenas;
	}

	public Sorteio() {
		super();
		// TODO Auto-generated constructor stub
	}

}
