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

		String[] jogos = JOptionPane.showInputDialog("Infome os n�meros jogados separados por virgula:").split(",");

		if (jogos.length > 6 || jogos.length < 6) {
			JOptionPane.showMessageDialog(null, "Quantidade de n�meros inv�lidos, tente novamente!");
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
				JOptionPane.showMessageDialog(null, "Infelizmente n�o foi desta vez, tente novamente no pr�ximo jogo!");
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Voc� acertou " + acertos.size() + " n�meros e fez uma quadra. N�meros: " + acertos.toString());
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Voc� acertou " + acertos.size() + " n�meros e fez uma quinta. N�meros: " + acertos.toString());
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Voc� acertou " + acertos.size() + " n�meros e ganhou na MEGA. N�meros: " + acertos.toString());
				break;
			default:
				JOptionPane.showMessageDialog(null, "Voc� acertou " + acertos.size() + " n�meros " + acertos.toString());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
