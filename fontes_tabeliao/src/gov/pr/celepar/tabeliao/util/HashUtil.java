package gov.pr.celepar.tabeliao.util;
/*
Este programa � licenciado de acordo com a 
LPG-AP (LICEN�A P�BLICA GERAL PARA PROGRAMAS DE COMPUTADOR DA ADMINISTRA��O P�BLICA), 
vers�o 1.1 ou qualquer vers�o posterior.
A LPG-AP deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa.
Caso uma c�pia da LPG-AP n�o esteja dispon�vel junto com este Programa, 
voc� pode contatar o LICENCIANTE ou ent�o acessar diretamente:
http://www.celepar.pr.gov.br/licenca/LPG-AP.pdf
Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa 
� preciso estar de acordo com os termos da LPG-AP
*/
import java.security.MessageDigest;

/**
 * Classe utilitaria para codificacao e decodificacao em base64 de dados binarios
 * 
 * @author Thiago Meneghello - GIC/CELEPAR
 */
public class HashUtil {

	/**
	 * Gera resumo em algoritimo SHA1
	 * @param data -> array de byte
	 * @return array de byte com o resumo gerado.
	 */
	public static byte[] sha1(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(data);
			return md.digest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
