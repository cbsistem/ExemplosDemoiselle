package gov.pr.celepar.tabeliao.database;
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
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * Le arquivo de configuracao do Hibernate gerando o SessionFactory 
 * e o Configuration. Cria singleton da Session para haver somente 
 * uma sessao em todo o sistema. 
 * Fornece metodos static para recuperar a sessao.
 * 
 * @author Grupo Framework - Componentes
 * @version 2.0, 18/01/2005
 * @since 1.0
 */
public class HibernateUtil {
	
	private static Logger logger = Logger.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory;
	private static final Configuration configuration;
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> transaction = new ThreadLocal<Transaction>();
	private static final ThreadLocal<Integer> countSession = new ThreadLocal<Integer>();
	private static final ThreadLocal<Integer> count = new ThreadLocal<Integer>();
	private static final ThreadLocal<Boolean> rollback = new ThreadLocal<Boolean>();
	
	
	static {
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure("hibernate-tabeliao.cfg.xml").buildSessionFactory();
		} catch (Exception ex) {
			throw new RuntimeException("[HibernateUtil] Problema de configura��o: " + ex.getMessage(), ex);
		}
	}
	
	/**
	 * Retorna sessao do Hibernate. Se nao houver sessao,
	 * cria uma nova.
	 * 
	 * @return Session Hibernate session
	 * @throws Exception
	 */
	public static Session currentSession() throws Exception {
		if (session.get() == null || !session.get().isOpen()) {
			session.set(sessionFactory.openSession());
			countSession.set(1);
		} else {
			countSession.set(countSession.get() + 1); 
		}
		
		logger.debug("******************************************************************************************");
		logger.debug("*** CurrentSession: " + countSession.get());
		logger.debug("******************************************************************************************");
		
		session.get().setFlushMode(FlushMode.COMMIT);
		return session.get();
	}
	
	/**
	 * Fecha a sessao do Hibernate.
	 * 
	 * @return true se a sessao foi fechada e false caso contr�rio - false indica que esta dentro de uma transacao.
	 * @throws Exception
	 */
	public static boolean closeSession() throws Exception {
		
		if (transaction.get() != null) {
			logger.debug("******************************************************************************************");
			logger.debug("*** CloseSession com transa��o aberta.");
			logger.debug("******************************************************************************************");
			return false;
		}

		if(countSession.get() != null){
			logger.debug("******************************************************************************************");
			logger.debug("*** CloseSession: " + countSession.get());
			logger.debug("******************************************************************************************");

			countSession.set(countSession.get() - 1);
			
			if(countSession.get() <= 0) {
				if (session.get() != null) {
					
					logger.debug("******************************************************************************************");
					logger.debug("*** CloseSession: Fechando a sessao.");
					logger.debug("******************************************************************************************");
					
					if (session.get().isOpen() && count.get() == null)
						session.get().flush();
					if (session.get().isConnected())
						session.get().disconnect();
					session.get().close();
				}		
			} else {
				return false;
			}

			session.set(null);
			countSession.set(null);
		}

		return true;
	}
	
	/**
	 * Inicia uma transacao se for necessario. Caso ja exista uma, somente a mantem.
	 * Retorna a sessao que esta nesta transacao.
	 * 
	 * @throws Exception Problemas ao obter conexao ou iniciar transacao.
	 *
	 */
	public static void currentTransaction() throws Exception {
		
		try {
			currentSession();
		} catch (Exception e) {
			throw new Exception ("Erro ao obter sess�o do Hibernate: " + e.getMessage(), e);
		}
		
		/* se j� houver uma transa��o, somente incrementa nosso contador de transac�es. */
		if (transaction.get() != null) {
			count.set(count.get() + 1); 
		} else {
			transaction.set(session.get().beginTransaction());
			count.set(1);
			rollback.set(false);
		}
		
		logger.debug("******************************************************************************************");
		logger.debug("*** CurrentTransaction: " + count.get());
		logger.debug("******************************************************************************************");
		
	}
	
	/**
	 * Somente tem efeito se a session tiver sido obtida atraves do metodo
	 * getManagedSession. Neste caso, executa um commit na transa��o associada 
	 * caso o total de chamadas de getManagedSession tenha sido igual ao total
	 * de commits e caso nao tenha sido chamado nenhum rollback. Caso algum 
	 * rollback tenha sido chamado, executa roolback e lanca Exception.
	 * @throws Exception 
	 *
	 */
	public static void commitTransaction() throws Exception {
		if (transaction.get() == null)
			throw new Exception ("N�o existe transa��o associada a esta sess�o!");
		
		if (count.get() != null) {
			logger.debug("******************************************************************************************");
			logger.debug("*** CommitTransaction: " + count.get());
			logger.debug("******************************************************************************************");

			count.set(count.get() -1);
			/* caso n�o tenha sido executado um commit para cada begin, n�o executa commit */
			if (count.get() > 0)
				return;
			
			if (rollback.get()) {
				logger.debug("******************************************************************************************");
				logger.debug("*** CommitTransaction: Fazendo o rollback");
				logger.debug("******************************************************************************************");
				
				transaction.get().rollback();
				count.set(null);
				transaction.set(null);
				closeSession();
				throw new Exception ("Voc� esperava um \"COMMIT\", mas no lugar um \"ROLLBACK\" foi executado. " +
						             "Verifique a execu��o do seu c�digo. Provavelmente alguma transa��o aninhada executou um \"ROLLBACK\"" +
						             "mas n�o foi lancada excess�o para a classe que a chamou tratar e executar \"ROLLBACK\" tamb�m. Lembre-se:" +
						             "utilizar a mesma transa��o.");
			}

			logger.debug("******************************************************************************************");
			logger.debug("*** CommitTransaction: Fazendo o commit");
			logger.debug("******************************************************************************************");
			transaction.get().commit();
			logger.debug("******************************************************************************************");
			logger.debug("*** CommitTransaction: Limpando a transacao e sessao");
			logger.debug("******************************************************************************************");
			countSession.set(1);
			count.set(null);
			transaction.set(null);
			closeSession();
				
		} else {
			throw new Exception ("N�o existe transa��o associada a esta sess�o!");
		}
		
	}
	
	/**
	 * Somente tem efeito se a session tiver sido obtida atraves do metodo
	 * getManagedSession. Neste caso, executa um rollback na transacao associada 
	 * caso o total de chamadas de getManagedSession tenha sido igual ao total
	 * de commit/rollbacks.
	 * @throws Exception 
	 *
	 */
	public static void rollbackTransaction() throws Exception {
		if (transaction.get() == null) {
			if (rollback.get()) {
				rollback.set(false);
				throw new Exception ("\"ROLLBACK\" j� executado por um commit.");
			}
			throw new Exception ("N�o existe transa��o associada a esta sess�o!");
		}

		logger.debug("******************************************************************************************");
		logger.debug("*** RollbackTransaction: " + count.get());
		logger.debug("******************************************************************************************");
		if (count.get() != null) {
			count.set(count.get() -1);
			/* ops! foi chamado o rollback no meio da transa��o. Devemos nos certificar de que
			 * n�o haver�o commits! */
			if (count.get() > 0) {
				rollback.set(true);
				return;
			}
			
			logger.debug("******************************************************************************************");
			logger.debug("*** RollbackTransaction: Fazendo o rollback");
			logger.debug("******************************************************************************************");
			transaction.get().rollback();
			logger.debug("******************************************************************************************");
			logger.debug("*** CommitTransaction: Limpando a transacao e sessao");
			logger.debug("******************************************************************************************");
			transaction.set(null);
			rollback.set(false);
			countSession.set(1);
			closeSession();
			count.set(null);
			
		} else {
			throw new Exception ("N�o existe transa��o associada a esta sess�o!");
		}
	
	}
	
	/**
	 * Fecha a sessao do Hibernate e todas as suas conexoes.
	 * @throws Exception
	 */
	public static void shutdown() throws Exception {
		
		closeSession();
		
		session.set(null);
		transaction.set(null);
		count.set(null);
		sessionFactory.close();
		configuration.setInterceptor(null);
	}
	
	/**
	 * Retorna o objeto Configuration usado
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}
	
	/**
	 * Retorna a SessionFactory atual
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Configura um novo sessionFactory. Pode ser usado para
	 * alterar o arquivo de configuracao do Hibernate. 
	 * @param sessionFactory
	 * @throws Exception
	 */
	public static void setSessionFactory(SessionFactory sessionFactory) throws Exception {
		closeSession();
		HibernateUtil.sessionFactory = sessionFactory;
	}
}