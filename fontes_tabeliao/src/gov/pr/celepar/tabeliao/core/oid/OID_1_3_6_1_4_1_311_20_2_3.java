package gov.pr.celepar.tabeliao.core.oid;
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
 * Classe OID 1.3.6.1.4.1.311.20.2.3 <br>
 * <br>
 * Atributo conhecido como UPN: Utilizado pela micro$oft para logon com SmartCard, <br>
 * Presente em alguns cartoes de determinadas autoridades.
 * NAO eh padrao da ICP-BRASIL
 * <b>*</b> <br>
 * 
 * @author Emerson Sachio Saito - CELEPAR/GIC
 *
 */
public class OID_1_3_6_1_4_1_311_20_2_3 extends OIDGenerico {

	public static final String OID = "1.3.6.1.4.1.311.20.2.3";
	
	public OID_1_3_6_1_4_1_311_20_2_3(){
	}
	
	public void inicializa(){
//		super.inicializa(CAMPOS);
	}
	
	/**
	 * 
	 * @return UPN 
	 */
	public String getUPN(){
		return super.getData();
	}
}