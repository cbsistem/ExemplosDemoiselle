package gov.pr.celepar.tabeliao.dao.factory;
/*
Este programa � licenciado de acordo com a 
LPG-AP (LICEN�A P�BLICA GERAL PARA PROGRAMAS DE COMPUTADOR DA ADMINISTRA��O P�BLICA), 
vers�o 1.1 ou qualquer vers�o posterior.
A LPG-AP deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa.
Caso uma c�pia da LPG-AP n�o esteja dispon�vel junto com este Programa, 
voc� pode contatar o LICENCIANTE ou ent�o acessar diretamente:
http://www.celepar.pr.gov.br/licenca/LPG-AP.pdf
Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa 
� preciso estar de acordo com os termos da LPG-AP
*/
import gov.pr.celepar.tabeliao.dao.implementation.*;
import gov.pr.celepar.tabeliao.dao.*;

/**
 * Classe Factory para DAOs que utilizam Hibernate.
 * 
 * @author CodeGenerator - Esta classe foi gerada automaticamente
 * @since 1.0
 * @version 1.0, Fri Sep 01 13:27:14 BRT 2006
 *
 */
public class HibernateDAOFactory extends DAOFactory {	

	public CertificadoAcDAO getCertificadoAcDAO() {
		 return new HibernateCertificadoAcDAO();
	}

	public AcDAO getAcDAO() {
		 return new HibernateAcDAO();
	}

	public CertificadoPublicoDAO getCertificadoPublicoDAO() {
		 return new HibernateCertificadoPublicoDAO();
	}

	public CertificadoRevogadoDAO getCertificadoRevogadoDAO() {
		 return new HibernateCertificadoRevogadoDAO();
	}

	public AtualizaLCRDAO getAtualizaLCRDAO() {
		return new HibernateAtualizaLCRDAO();
	}

}
