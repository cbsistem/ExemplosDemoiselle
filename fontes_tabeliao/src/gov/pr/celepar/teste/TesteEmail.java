package gov.pr.celepar.teste;
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
import gov.pr.celepar.tabeliao.dao.CertificadoPublicoDAO;
import gov.pr.celepar.tabeliao.dao.factory.DAOFactory;

public class TesteEmail {
	
	public static void main(String args[]) {
		
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.HIBERNATE);
		dao.getCertificadoAcDAO();
		CertificadoPublicoDAO certificadoPublicoDAO = dao.getCertificadoPublicoDAO();
		
		try {
//			TabeliaoCertificado tc = null;
			
			certificadoPublicoDAO.obter(9);
			
//			System.out.println(tc.getX509Certificate());
//			System.out.println("Tipo do certificado: " + tc.getTipoCertificado());
//			System.out.println("Email: " + tc.getEmail());
			
//			System.out.println("Nome: " + tc.getNome());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
