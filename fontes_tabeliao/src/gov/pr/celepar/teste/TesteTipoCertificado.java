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
import gov.pr.celepar.tabeliao.core.TabeliaoCertificate;
import gov.pr.celepar.tabeliao.dao.CertificadoAcDAO;
import gov.pr.celepar.tabeliao.dao.CertificadoPublicoDAO;
import gov.pr.celepar.tabeliao.dao.factory.DAOFactory;
import gov.pr.celepar.tabeliao.pojo.CertificadoAc;
import gov.pr.celepar.tabeliao.pojo.CertificadoPublico;

public class TesteTipoCertificado {
	
	public static void main(String args[]) {
		
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.HIBERNATE);
		CertificadoAcDAO certificadoAcDAO = dao.getCertificadoAcDAO();
		CertificadoPublicoDAO certificadoPublicoDAO = dao.getCertificadoPublicoDAO();
		
		try {
			CertificadoAc ac = certificadoAcDAO.obter(814);
			TabeliaoCertificate tc = new TabeliaoCertificate(ac);

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Certificado de AC:");
			System.out.println(tc);
			
			CertificadoPublico cp = certificadoPublicoDAO.obter(15);
			tc = new TabeliaoCertificate(cp);
			 
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Certificado de Usuario:");
			System.out.println(tc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
