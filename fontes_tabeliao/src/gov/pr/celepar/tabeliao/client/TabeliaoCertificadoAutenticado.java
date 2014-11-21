package gov.pr.celepar.tabeliao.client;
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
import gov.pr.celepar.tabeliao.core.TabeliaoCertificate;

/**
 * Classe que armazena as informacoes do Certificado que foi utilizado na autenticacao.
 * 
 * @version 1.0
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */

public class TabeliaoCertificadoAutenticado {
	
	private static ThreadLocal<TabeliaoCertificate> certificados = new ThreadLocal<TabeliaoCertificate>();
	
	private TabeliaoCertificadoAutenticado(){
		
	}
	
	/**
	 * Metodo para carregar o certificado
	 * 
	 * @param tc -> certificado digital
	 * @see TabeliaoCertificate
	 *  
	 */
	public static void setCertificadoAutenticado(TabeliaoCertificate tc){
		if(getCertificadoAutenticado() == null) {
			certificados.set(tc);
		}
	}
	
	/**
	 * Metodo para recuperar o certificado
	 * 
	 * @return  TabeliaoCertificate -> certificado digital
	 * @see TabeliaoCertificate
	 *  
	 */
	public static TabeliaoCertificate getCertificadoAutenticado(){
		return certificados.get();
	}
	
	/**
	 * Metodo para limpar as informacoes
	 *  
	 */
	public static void clearCertificadoAutenticado(){
		certificados.set(null);
	}

}
