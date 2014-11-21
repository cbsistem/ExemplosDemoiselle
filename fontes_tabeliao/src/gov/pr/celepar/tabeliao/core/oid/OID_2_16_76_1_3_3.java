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
 * Classe OID 2.16.76.1.3.3 <br>
 * <br>
 * Possui alguns atributos especificos de pessoa juridica ou equipamento: <br>
 * <b>*</b> Cadastro Nacional de Pessoa Juridica (CNPJ) da pessoa juridica titular do certificado <br>
 * 
 * @author Thiago Meneghello <thiagomeneghello@ecelepar.pr.gov.br> CELEPAR/GIC
 *
 */
public class OID_2_16_76_1_3_3 extends OIDGenerico {

	public static final String OID = "2.16.76.1.3.3";
	
	public OID_2_16_76_1_3_3(){
	}
	
	public void inicializa(){
//		super.inicializa(CAMPOS);
	}
	
	/**
	 * 
	 * @return numero do Cadastro Nacional de Pessoa Juridica (CNPJ) da empresa titular do certificado
	 */
	public String getCNPJ(){
		return super.getData();
	}
}
