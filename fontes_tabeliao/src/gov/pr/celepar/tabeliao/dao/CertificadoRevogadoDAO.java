package gov.pr.celepar.tabeliao.dao;
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
import java.util.Collection;
import java.util.List;
import gov.pr.celepar.framework.exception.ApplicationException;
import gov.pr.celepar.tabeliao.pojo.CertificadoRevogado;

/**
 * Classe de manipulacao de objetos da classe CertificadoRevogado.
 * 
 * @author CodeGenerator - Esta classe foi gerada automaticamente
 * @since 1.0
 * @version 1.0, Fri Sep 01 13:27:14 BRT 2006
 *
 */
public interface CertificadoRevogadoDAO {
	
	public void salvar(CertificadoRevogado obj) throws ApplicationException, Exception;

	public void alterar(CertificadoRevogado obj) throws ApplicationException, Exception;
	
	public void excluir(CertificadoRevogado obj) throws ApplicationException, Exception;
	
	public Collection< CertificadoRevogado> listar(Integer qtdPagina, Integer numPagina) throws ApplicationException, Exception;
	
	public Collection< CertificadoRevogado> listar() throws ApplicationException, Exception;
	
	public CertificadoRevogado obter(long obj) throws ApplicationException, Exception;
	
	public CertificadoRevogado buscarPorKeyId(String key) throws ApplicationException, Exception;
	
	public List<CertificadoRevogado> buscarCertificadosVencidos() throws ApplicationException, Exception;

	public List<CertificadoRevogado> buscarCertificadosVencidos2() throws ApplicationException, Exception;

	
}
