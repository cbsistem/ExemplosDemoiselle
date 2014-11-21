package gov.pr.celepar.tabeliao.core;
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
import java.security.cert.X509Certificate;

/**
 * Classe para tratamento dos usos do certificado recebido como parametro
 *  
 * @author Thiago Meneghello <thiagomeneghello@ecelepar.pr.gov.br> CELEPAR/GIC
 *
 */
public class TabeliaoKeyUsage {
	
	private static final String[] KEY_USAGE = {
	     "digitalSignature",
	     "nonRepudiation",
	     "keyEncipherment",
	     "dataEncipherment",
	     "keyAgreement",
	     "keyCertSign",
	     "cRLSign",
	     "encipherOnly",
	     "decipherOnly"	};
	
	private boolean[] keyUsage;
	
	/**
	 * 
	 * @param cert -> Certificado ICP-BRASIL
	 */
	public TabeliaoKeyUsage(X509Certificate cert){
		this.keyUsage = cert.getKeyUsage();
	}
	
	/**
	 * 
	 * @return true, se eh um certificado de assinatura (A1, A2, A3 ou A4).
	 */
	public boolean isDigitalSignature(){
		return keyUsage[0];
	}
	
	/**
	 * 
	 * @return true, se possui atributo de Nao repudio
	 */
	public boolean isNonRepudiation() {
		return keyUsage[1];
	}

	/**
	 * 
	 * @return true, se permite � cifragem � de � chave� criptografica
	 */
	public boolean isKeyEncipherment() {
		return keyUsage[2];
	}

	/**
	 * 
	 * @return true, se permite cifrar dados diretamente, exceto chaves criptograficas.
	 */
	public boolean isDataEncipherment() {
		return keyUsage[3];
	}

	/**
	 * 
	 * @return true, se permite � a � obtencao � de � chaves � de � sessao

	 */
	public boolean isKeyAgreement() {
		return keyUsage[4];
	}

	/**
	 * 
	 * @return true, se permite � que � seja � usada � para� assinatura�de�certificados
	 */
	public boolean isKeyCertSign() {
		return keyUsage[5];
	}

	/**
	 * 
	 * @return true, se permite�que�seja�usada�para�assinatura�de�LCR�(Lista�de�Certificados�Revogados)
   */
	public boolean isCRLSign() {
		return keyUsage[6];
	}

	/**
	 * 
	 * @return usado somente em conjunto com a opcao keyAgreement e indica que a chave de sessao obtida
	 *  somente pode ser�usada�para�cifrar�dados

	 */
	public boolean isEncipherOnly() {
		return keyUsage[7];
	}

	/**
	 * 
	 * @return semelhante ao encipherOnly, porem a chave de sessao�obtida�somente�pode�executar�
	 *  a�operacao�de�decifrar
	 */ 
	public boolean isDecipherOnly() {
		return keyUsage[8];
	}

	/**
	 * 
	 * @return formato texto plano da propriedade.
	 */
	public String toString(){
		String ret = "";
		for(int i=0 ; i<keyUsage.length ; i++) {
			if(keyUsage[i]){
				if(ret.length() > 0) {
					ret += ", ";
				}
				ret += KEY_USAGE[i];
			}
		}
		return ret;
	}
}
