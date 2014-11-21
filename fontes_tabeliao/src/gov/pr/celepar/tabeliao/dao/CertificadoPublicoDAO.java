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
import gov.pr.celepar.framework.exception.ApplicationException;

import gov.pr.celepar.tabeliao.pojo.*;

/**
 * Classe de manipulacao de objetos da classe CertificadoPublico.
 * 
 * @author CodeGenerator - Esta classe foi gerada automaticamente
 * @since 1.0
 * @version 1.0, Fri Sep 01 13:27:14 BRT 2006
 *
 */
public interface CertificadoPublicoDAO {
	
	public void salvar(CertificadoPublico obj) throws ApplicationException, Exception;

	public void alterar(CertificadoPublico obj) throws ApplicationException, Exception;
	
	public void excluir(CertificadoPublico obj) throws ApplicationException, Exception;
	
	public Collection<?> listar(Integer qtdPagina, Integer numPagina) throws ApplicationException, Exception;
	
	public Collection<?> listar() throws ApplicationException, Exception;
	
	public CertificadoPublico obter(long obj) throws ApplicationException, Exception;
	
	public CertificadoPublico buscarCertificadoPublicoPorCertificadoSerial(CertificadoAc certificadoAc, String numSerie) throws ApplicationException, Exception;
}
