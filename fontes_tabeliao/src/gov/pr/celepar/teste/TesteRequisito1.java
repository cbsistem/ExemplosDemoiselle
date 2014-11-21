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

public class TesteRequisito1 {
	
	public static void main(String args[]) {
		
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.HIBERNATE);
		CertificadoPublicoDAO certificadoPublicoDAO = dao.getCertificadoPublicoDAO();
		
		try {
			certificadoPublicoDAO.obter(TesteContantes.ID_CERTIFICADO_PUBLICO);

//			System.out.println(tc.getX509Certificate());
			System.out.println("*** Teste para carregar os atributos do requisito 1.0");
//			System.out.println("Authority Key Identifier: " + tc.getAuthorityKeyIdentifier());
//			System.out.println("Key Usage: " + tc.getKeyUsage().getKeyUsages(" | "));
			System.out.println("Certificate Policies: " + "XXX");
//			System.out.println("CRL Distribution Points: " + tc.getCRLDistributionPoints());
			System.out.println("Subject Alternative Name: " + "XXX");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
