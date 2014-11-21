package gov.pr.celepar.tabeliao.client.applet;
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
/**
 * Classe de Excecao usada para erros de assinaturas em formato PKCS7
 * 
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */
public class DocumentSignException extends Exception {

	private static final long serialVersionUID = -4913356970514529137L;

	/**
	 * 	 
	 * @param aMessage -> Mensagem de erro
	 *
	 */
	public DocumentSignException(String aMessage) {
        super(aMessage);
    }

	/**
	 * 	 
	 * @param aMessage -> Mensagem de erro
	 * @param aCause -> Causa do erro: Throwable
	 * @see Throwable
	 */
    public DocumentSignException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }

}
