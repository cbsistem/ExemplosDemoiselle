package gov.pr.celepar.tabeliao.client.form;
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
import org.apache.struts.validator.ValidatorForm;
import gov.pr.celepar.tabeliao.util.Base64Utils;
import gov.pr.celepar.tabeliao.util.Util;

/**
 * Classe que extende ValidadorForm (struts) para ser utilizada para geracao de 
 * assinatura em formato PCKS7/CADES em ambiente Servidor (WEB).
 * Fornece os metodos para recuperar os arquivos ou conteudos assinados.
 *  
 * @author Thiago Meneghello. - GIC/CELEPAR
 *
 */
public class TabeliaoAssinaturaWebForm extends ValidatorForm {

	private static final long serialVersionUID = 2225185000505480970L;

	private String[] campoIds;
	private String[] campoAssinaturas;

	/**
	 * 
	 * @return Identificadores dos arquivos para assinatura, codificados em Base64.
	 */
	public String[] getCampoIds() {
		return campoIds;
	}

	/**
	 * 
	 * @param campoIds -> Identificadores dos arquivos para assinatura.
	 */
	public void setCampoIds(String[] campoIds) {
		this.campoIds = campoIds;
	}

	/**
	 * 
	 * @return array de strings com as assinaturas geradas e codificadas em Base64.
	 */
	public String[] getCampoAssinaturas() {
		return campoAssinaturas;
	}

	/**
	 * 
	 * @param campoAssinaturas -> array de strings com as assinaturas.
	 */
	public void setCampoAssinaturas(String[] campoAssinaturas) {
		this.campoAssinaturas = campoAssinaturas;
	}

	/**
	 * 
	 * @return Identificadores dos arquivos para assinatura.
	 */
	public String[] getIds(){
		if(campoIds == null) {
			return null;
		}
		return Util.decodeBase64(campoIds);
	}
	
	/**
	 * 
	 * @return matriz de bytes com as assinaturas geradas.
	 */
	public byte[][] getAssinaturas() {
		if(campoAssinaturas == null) {
			return null;
		}
		byte[][] conteudo = new byte[campoAssinaturas.length][];
		
		for(int i=0 ; i<campoAssinaturas.length ; i++) {
			conteudo[i] = Base64Utils.base64Decode(campoAssinaturas[i]);
		}
		return conteudo;
	}
	
}
