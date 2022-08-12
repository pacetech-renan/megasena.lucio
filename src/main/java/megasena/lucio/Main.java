package megasena.lucio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.web.client.RestTemplate;
import megasena.lucio.modelo.Sorteio;

public class Main {

	public static void main(String[] args) {

		String urlMega = "https://loteriascaixa-api.herokuapp.com/api/mega-sena/latest";

		RestTemplate restTemplate = new RestTemplate();

		String[] jogos = JOptionPane.showInputDialog("Infome os números jogados separados por virgula:").split(",");

		if (jogos.length > 6 || jogos.length < 6) {
			JOptionPane.showMessageDialog(null, "Quantidade de números inválidos, tente novamente!");
		}

		try {

			Sorteio sorteio = restTemplate.getForEntity(urlMega, Sorteio.class).getBody();

			List<String> acertos = new ArrayList<String>();

			for (String jogo : jogos) {
				for (String dezena : sorteio.getDezenas()) {
					if (jogo.trim().equals(dezena.trim())) {
						acertos.add(dezena);
					}
				}
			}

			JOptionPane.showMessageDialog(null, "Concurso " + sorteio.getConcurso() + " realizado em " + sorteio.getData() + ".");

			switch (acertos.size()) {
			case 0:
				JOptionPane.showMessageDialog(null, "Infelizmente não foi desta vez, tente novamente no próximo jogo!");
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Você acertou " + acertos.size() + " números e fez uma quadra. Números: " + acertos.toString());
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Você acertou " + acertos.size() + " números e fez uma quinta. Números: " + acertos.toString());
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Você acertou " + acertos.size() + " números e ganhou na MEGA. Números: " + acertos.toString());
				break;
			default:
				JOptionPane.showMessageDialog(null, "Você acertou " + acertos.size() + " números " + acertos.toString());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
