package gov.pr.celepar.tabeliao.dao.implementation;
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
import gov.pr.celepar.tabeliao.database.HibernateUtil;
import gov.pr.celepar.framework.exception.ApplicationException;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.apache.log4j.Logger;

import gov.pr.celepar.tabeliao.pojo.*;
import gov.pr.celepar.tabeliao.dao.*;

/**
 * Classe de manipulacao de objetos da classe CertificadoAc.
 * 
 * @author CodeGenerator - Esta classe foi gerada automaticamente
 * @since 1.0
 * @version 1.0, Fri Sep 01 13:27:14 BRT 2006
 *
 */
public class HibernateCertificadoAcDAO implements CertificadoAcDAO {
	
	private static Logger log = Logger.getLogger(CertificadoAcDAO.class);
	
	public HibernateCertificadoAcDAO() {
		
	}
	
	
	
	/**
	 * Listagem de objetos CertificadoAc.
	 * 
	 * @param qtdPagina quantidade de itens listados em cada pagina 
	 * @param numPagina numero da pagina a ser buscada
	 * @return Lista de objetos CertificadoAc
	 * @throws Exception Caso ocorra erro com hibernate/conexao.
	 */
	public Collection<?> listar(Integer qtdPagina, Integer numPagina) throws ApplicationException, Exception {
		Collection<?> coll = new ArrayList<Object>();
		
		try {
			Session session = HibernateUtil.currentSession();
			Criteria q = session.createCriteria(CertificadoAc.class);
			if (qtdPagina != null && numPagina != null) {
				q.setMaxResults(qtdPagina.intValue());
				q.setFirstResult( (numPagina.intValue()-1) * qtdPagina.intValue() );
			}
			coll = q.list();
		} catch (HibernateException he) {
			throw new ApplicationException("mensagem.erro.certificadoAc.listar", he);
		} catch (Exception e) {
			throw new ApplicationException("mensagem.erro.certificadoAc.listar", e, ApplicationException.ICON_ERRO);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (Exception e) {
				log.error("Erro ao Fechar Conexao com o Hibernate: ", e);				
			}
		}
		return coll;
	}
	
	/**
	 * Listagem de objetos CertificadoAc.
	 * 
	 * @return Lista de objetos CertificadoAc
	 * @throws Exception Caso ocorra erro com hibernate/conexao.
	 */
	public Collection<?> listar() throws ApplicationException, Exception {
		return this.listar(null, null);	
	}
		
	/**
	 * Obtem um objeto CertificadoAc.
	 * 
	 * @param obj Chave primaria do objeto
	 * @throws Exception Caso ocorra erro com hibernate/conexao.
	 */
	public CertificadoAc obter(long obj) throws ApplicationException, Exception {
		try {
			Session session = HibernateUtil.currentSession();
			return (CertificadoAc)session.get(CertificadoAc.class, obj);
		} catch (HibernateException e) {
			throw new ApplicationException("mensagem.erro.certificadoAc.obter", e);
		} catch (Exception e) {
			throw new ApplicationException("mensagem.erro.certificadoAc.obter", e, ApplicationException.ICON_ERRO);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (Exception e) {
				log.error("Erro ao Fechar Conexao com o Hibernate: ", e);
			}
		}	
	}
	
	private static final String buscarPorKeyIdSql = "SELECT certificadoAc " +
			"FROM gov.pr.celepar.tabeliao.pojo.CertificadoAc as certificadoAc " +
			"WHERE certificadoAc.keyId = :key ";
	public CertificadoAc buscarPorKeyId(String key) throws ApplicationException, Exception {
		try {
			Session session = HibernateUtil.currentSession();
			Query query = session.createQuery(buscarPorKeyIdSql).setCacheable(true);
			
			query.setString("key", key);
			
			return (CertificadoAc)query.uniqueResult();
		} catch (HibernateException e) {
			throw new ApplicationException("mensagem.erro.certificadoAc.buscarPorId", e);
		} catch (Exception e) {
			throw new ApplicationException("mensagem.erro.certificadoAc.buscarPorId", e, ApplicationException.ICON_ERRO);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (Exception e) {
				log.error("Erro ao Fechar Conexao com o Hibernate: ", e);
			}
		}	
	}

	private static final String buscarPorSubjectSerialSql = "SELECT certificadoAc " +
			"FROM gov.pr.celepar.tabeliao.pojo.CertificadoAc as certificadoAc " +
			"WHERE certificadoAc.numSerie = :serial " +
			"  AND ((certificadoAc.ac.pai is not null AND certificadoAc.ac.pai.dn = :subject) OR " +
			"       (certificadoAc.ac.pai is null AND certificadoAc.ac.dn = :subject))";
	public CertificadoAc buscarPorSubjectSerial(String subject, String serial) throws ApplicationException, Exception {
		try {
			Session session = HibernateUtil.currentSession();
			Query query = session.createQuery(buscarPorSubjectSerialSql).setCacheable(true);
			
			query.setString("subject", subject);
			query.setString("serial", serial);
			
			return (CertificadoAc)query.uniqueResult();
		} catch (HibernateException e) {
			throw new ApplicationException("mensagem.erro.certificadoAc.buscarPorSubjectSerial", e);
		} catch (Exception e) {
			throw new ApplicationException("mensagem.erro.certificadoAc.buscarPorSubjectSerial", e, ApplicationException.ICON_ERRO);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (Exception e) {
				log.error("Erro ao Fechar Conexao com o Hibernate: ", e);
			}
		}	
	}	
}