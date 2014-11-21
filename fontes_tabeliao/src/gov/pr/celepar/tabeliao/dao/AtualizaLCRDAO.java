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
import gov.pr.celepar.tabeliao.pojo.AtualizaLCR;

/**
 * Classe de manipulacao de objetos da classe AtualizaLCR.
 * 
 * @version 1.0
 *
 */
public interface AtualizaLCRDAO {	
	
	public void salvar(AtualizaLCR obj) throws ApplicationException, Exception;

	public void alterar(AtualizaLCR obj) throws ApplicationException, Exception;
	
	public void excluir(AtualizaLCR obj) throws ApplicationException, Exception;
	
	public Collection<AtualizaLCR> listar(Integer qtdPagina, Integer numPagina) throws ApplicationException, Exception;
	
	public Collection<AtualizaLCR> listar() throws ApplicationException, Exception;
	
	public Long buscarQtdLista() throws ApplicationException;
	
	public AtualizaLCR obter(long obj) throws ApplicationException, Exception;

	public AtualizaLCR buscarPorIp(String ip) throws ApplicationException, Exception;
	
	public List<AtualizaLCR> buscarServicosParados() throws ApplicationException, Exception;
}
