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
 * Classe que�permite�instanciar�um certificado�de�modo�mais�genehrico,�sem pre�-identificacao 
 * do tipo (PF,PJ ou Equipamento) atravehs de seus OID's. A classe retorna�o�objeto�conforme�
 * o�tipo�encontrado
 * 
 * @author Thiago Meneghello <thiagomeneghello@ecelepar.pr.gov.br> CELEPAR/GIC
 *
 */
public class TabeliaoSubjectAlternativeNames {

	private String                   email = null;
	private TabeliaoDadosPF          tabeliaoDadosPF = null;
	private TabeliaoDadosPJ          tabeliaoDadosPJ = null;
	private TabeliaoDadosEquipamento tabeliaoDadosEquipamento = null;
	
	/**
	 * Construtor unico
	 * @param certificate -> certificado no formato X509Certificate
	 * @see java.security.cert.X509Certificate
	 */
	public TabeliaoSubjectAlternativeNames(X509Certificate certificate) {
		CertificadoExtra ce = new CertificadoExtra(certificate);
		if(ce.isCertificadoPF()) {
			tabeliaoDadosPF = new TabeliaoDadosPF(ce.getOID_2_16_76_1_3_1(),
					ce.getOID_2_16_76_1_3_5(),
					ce.getOID_2_16_76_1_3_6());
		} else if(ce.isCertificadoPJ()) {
			tabeliaoDadosPJ = new TabeliaoDadosPJ(ce.getOID_2_16_76_1_3_2(),
					ce.getOID_2_16_76_1_3_3(),
					ce.getOID_2_16_76_1_3_4(),
					ce.getOID_2_16_76_1_3_7());
		} else if(ce.isCertificadoEquipamento()) {
			tabeliaoDadosEquipamento = new TabeliaoDadosEquipamento(ce.getOID_2_16_76_1_3_2(),
					ce.getOID_2_16_76_1_3_3(),
					ce.getOID_2_16_76_1_3_4(),
					ce.getOID_2_16_76_1_3_8());
		}
		this.email = ce.getEmail();
	}
	
	/**
	 * 
	 * @return se eh um certificado de Pessoa Fisica
	 */
	public boolean isDadosPF(){
		return tabeliaoDadosPF != null;
	}
	
	/**
	 * 
	 * @return Objeto do tipo TabeliaoDadosPF
	 * @see gov.pr.celepar.tabeliao.core.TabeliaoDadosPF
	 */
	public TabeliaoDadosPF getTabeliaoDadosPF(){
		return tabeliaoDadosPF;
	}
	/**
	 * 
	 * @return se eh um certificado de Pessoa Juridica
	 */
	public boolean isDadosPJ(){
		return tabeliaoDadosPJ != null;
	}
	
	/**
	 * 
	 * @return Objeto do tipo TabeliaoDadosPJ
	 * @see gov.pr.celepar.tabeliao.core.TabeliaoDadosPJ
	 */
	public TabeliaoDadosPJ getTabeliaoDadosPJ(){
		return tabeliaoDadosPJ;
	}
	
	/**
	 * 
	 * @return se eh um certificado de Equipamento
	 */
	public boolean isDadosEquipamento(){
		return tabeliaoDadosEquipamento != null;
	}
	
	/**
	 * 
	 * @return Objeto do tipo TabeliaoDadosEquipamento
	 * @see gov.pr.celepar.tabeliao.core.TabeliaoDadosEquipamento
	 */
	public TabeliaoDadosEquipamento getTabeliaoDadosEquipamento(){
		return tabeliaoDadosEquipamento;
	}
	
	/**
	 * 
	 * @return endereco de E-mail contido no certificado
	 */
	public String getEmail(){
		return email;
	}
}
