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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Classe de propriedades para DN - Distinguished Name
 * 
 * @author Thiago Meneghello <thiagomeneghello@ecelepar.pr.gov.br> CELEPAR/GIC
 *
 * 
 *
 */
public class TabeliaoDN extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dn = null;

	/**
	 * Construtor do CertificadoExtra
	 * @param dn
	 * @throws IOException
	 */
	
	public TabeliaoDN(String dn) throws IOException {
		super();
		this.dn = dn;
		ByteArrayInputStream bis = new ByteArrayInputStream(dn.replaceAll(",", "\n").getBytes());
		load(bis);
		bis.close();
	}
	
	/**
	 * 
	 * @return devolve em formato string
	 */
	public String toString(){
		return dn;
	}
}
