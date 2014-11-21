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
import javax.servlet.ServletRequest;
/**
 * Classe de interface para ConteudoHandler, conteudo para o qual a assinatura sera gerada em formato CADES.
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */
public interface ConteudoHandler {
	
	public byte[] getConteudo(ServletRequest request, String id);
	
	public boolean isHash();

}
