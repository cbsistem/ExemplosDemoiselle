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
 * Classe OID 2.16.76.1.3.7 <br>
 * <br>
 * Possui alguns atributos de pessoa juridica: <br>
 * <b>*</b> Numero do Cadastro Especifico do INSS (CEI) da pessoa juridica titular do certificado <br>
 * 
 * @author Thiago Meneghello - CELEPAR/GIC
 *
 */
public class OID_2_16_76_1_3_7 extends OIDGenerico {

	public static final String OID = "2.16.76.1.3.7";
	
	protected static final Object CAMPOS[] = {"inss",   (int) 12};

	public OID_2_16_76_1_3_7(){
	}
	
	public void inicializa(){
		super.inicializa(CAMPOS);
	}
	
	/**
	 * 
	 * @return Numero do Cadastro Especifico do INSS (CEI) da pessoa juridica titular do certificado
	 */
	public String getInss(){
		return (String)propriedades.get("inss");
	}
}
