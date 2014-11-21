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
import gov.pr.celepar.tabeliao.util.HashUtil;
import java.io.FileInputStream;
import javax.servlet.ServletRequest;

/**
 * Classe para geracao de Hash/Resumo em SHA1 do conteudo a ser assinado.
 * @author Thiago Meneghello - GIC/CELEPAR.
 *
 */
public class ConteudoHandlerHashImpl implements ConteudoHandler {
	
	/**
	 * @param request -> ServletRequest
	 * @param id -> Identificador do arquivo
	 * @return array de byte com o conteudo a ser assinado.
	 */
	public byte[] getConteudo(ServletRequest request, String id) {
		byte[] ret = null;
		try {
			FileInputStream fis = new FileInputStream(id);
			ret = new byte[(int) fis.getChannel().size()];
			fis.read(ret);
			fis.close();
			
			ret = HashUtil.sha1(ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * @return true -> O conteudo eh um hash.
	 */
	public boolean isHash(){
		return true;
	}

}
