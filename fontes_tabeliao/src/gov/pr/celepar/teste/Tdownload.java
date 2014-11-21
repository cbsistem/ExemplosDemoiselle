package gov.pr.celepar.teste;
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
import java.io.IOException;

import gov.pr.celepar.tabeliao.util.Download;

/**
 * @author esaito
 *
 */
public class Tdownload {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String url = "http://www.certificadodigital.com.br/repositorio/lcr/SerasaSRF.crl";
		try {
			byte[] buffer = Download.downloadByteArray(url,"proxy-02.pr.gov.br","8000", null, null);
			//byte[] buffer = Download.downloadByteArray(url,"proxy.pr.gov.br","8080", "usuario","senha");
			System.out.println(buffer.length);
			} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
