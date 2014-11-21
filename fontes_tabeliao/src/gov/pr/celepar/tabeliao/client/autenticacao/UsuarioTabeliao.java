package gov.pr.celepar.tabeliao.client.autenticacao;
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
import gov.pr.celepar.sentinela.client.pojo.UsuarioAutenticado;

/**
 * Classe do Tabeliao que implementa gov.pr.celepar.sentinela.client.pojo.UsuarioAutenticado
 * para uso com o proto-agente SENTINELA
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */

public class UsuarioTabeliao implements UsuarioAutenticado {

	private String nome;
	private String email;
	private String cpf;
	
	/**
	 * 
	 * @param nome
	 * @param email
	 * @param cpf
	 */
	public UsuarioTabeliao(String nome, String email, String cpf){
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}
	
	@Override
	public String getCpf() {
		return cpf;
	}

	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public String getLogin() {
		return null;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getOrgaoExpedidor() {
		return null;
	}

	@Override
	public String getRg() {
		return null;
	}

	@Override
	public void setLogin(String arg0) {
		// Auto-generated method stub 
	}

}
