package gov.pr.celepar.tabeliao.client.handler;
/*
Este programa � licenciado de acordo com a 
LPG-AP (LICEN�A P�BLICA GERAL PARA PROGRAMAS DE COMPUTADOR DA ADMINISTRA��O P�BLICA), 
vers�o 1.1 ou qualquer vers�o posterior.
A LPG-AP deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa.
Caso uma c�pia da LPG-AP n�o esteja dispon�vel junto com este Programa, voc� pode contatar o LICENCIANTE ou ent�o acessar diretamente:
http://www.celepar.pr.gov.br/licenca/LPG-AP.pdf
Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa 
� preciso estar de acordo com os termos da LPG-AP
*/
import java.io.FileInputStream;
import javax.servlet.ServletRequest;

/**
 * Implementacao da classe para geracao do ConteudoHandler
 * @author Thiago Meneghello - GIC/CELEPAR.
 *
 */
public class ConteudoHandlerImpl implements ConteudoHandler {
	
	/**
	 * 
	 * @param request -> ServletRequest
	 * @param id -> Identificador do arquivo
	 * @return array de byte com o conteudo para assinatura
	 */
	public byte[] getConteudo(ServletRequest request, String id) {
		byte[] ret = null;
		try {
			FileInputStream fis = new FileInputStream(id);
			ret = new byte[(int) fis.getChannel().size()];
			fis.read(ret);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * @return false -> O conteudo nao eh um hash. 
	 */
	public boolean isHash(){
		return false;
	}

}
