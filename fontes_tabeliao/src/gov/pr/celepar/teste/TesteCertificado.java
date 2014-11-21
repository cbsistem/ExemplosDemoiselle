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
import java.io.FileInputStream;

public class TesteCertificado {
	
	public static void main(String args[]) {
		try {
			
			String Diretorio = "/home/esaito/certificados_icp_brasil/";
					//"/home/esaito/certificados/Chaves_publicas_usuarios/";

			FileInputStream fis = 
			new FileInputStream(Diretorio+"AC_SINCOR_G2.cer");
			
			//AC_Certisign_Multipla_G3.cer
			//AC_Certisign_G3.cer
			//AC_RFB_V2.cer
			


			//cerealista_torres.cer");
			//quaresma.der");
			//anderson.der
			//sgoda.der
			//zaniratti.cer
						

			
			TabeliaoCertificate tc = new TabeliaoCertificate(fis);
			if(tc.hasDadosPF()) {
				System.out.println("PF: "+tc.getTabeliaoDadosPF().getCPF());
				
			}
			if(tc.hasDadosPJ()) {
				System.out.println("PJ: "+tc.getTabeliaoDadosPJ().getCNPJ());
			}
			if(tc.hasDadosEquipamento()) {
				System.out.println("equipamento");
				
			}
			System.out.println("apl: "+tc.getNome());
			System.out.println("apl: "+tc.getTipoCertificado());
			System.out.println("apl: "+tc.getEmail());
			System.out.println("apl: "+tc.getCRLDistributionPoint());
			//System.out.println(tc.toString());
			//System.out.println(tc.getX509Certificate());
					
			
			fis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
