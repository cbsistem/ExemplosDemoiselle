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
import java.io.FileInputStream;

//import gov.pr.celepar.tabeliao.facade.CertificadoFacade;
//import gov.pr.celepar.tabeliao.pojo.CertificadoAc;

public class TesteCriacaoCertificado {
	
	public static void main(String args[]) {
		
		try {
			//CertificadoAc certAc = new CertificadoAc();
			
			FileInputStream fis = new FileInputStream("/home/thiagom/workspace/gtf-tabeliao/gestao/certificados_teste/certificadoACRaiz.crt");
			byte[] data = new byte[(int)fis.getChannel().size()];
			fis.read(data);
//			certAc.setArquivo(data);
			fis.close();
			
//			CertificadoFacade.inserirCertificado(certAc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
