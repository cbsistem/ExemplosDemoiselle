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
import gov.pr.celepar.tabeliao.core.TabeliaoAssinaturaEnvelopingXML;
import gov.pr.celepar.tabeliao.core.TabeliaoCertificate;
import gov.pr.celepar.tabeliao.core.validacao.TabeliaoResultadoValidacao;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ValidarXMLEnveloping {
	
	final static String DIRETORIO = "/home/esaito/arquivosXML_Assinatura/";
	//final static String DIRETORIO = "/home/esaito/arquivosXML_Assinatura/assinadosWeb/";
	public static void main(String[] args) {


		    
			//String fileName = "192143_ING_assi.xml";
			String fileName = "Enveloping_co_eclp_assinado.xml";
			//String fileName = "192143_Enveloping_assi.xml";
			
			TabeliaoAssinaturaEnvelopingXML assinaturaEnvelopingXML;
			List<TabeliaoResultadoValidacao> resultados = new ArrayList<TabeliaoResultadoValidacao>();
			List<TabeliaoCertificate> certificados = new ArrayList<TabeliaoCertificate>();
			try {				
				assinaturaEnvelopingXML = new TabeliaoAssinaturaEnvelopingXML(new FileInputStream(DIRETORIO + fileName));
				assinaturaEnvelopingXML.valida();
						
				resultados = assinaturaEnvelopingXML.getResultadosValidacoes();
				certificados = assinaturaEnvelopingXML.getCertificadosAssinantes();
				//tags = assinaturaEnvelopedXML.getTagsAssinadas();
				
				for(int j=0; j< assinaturaEnvelopingXML.getQuantidadeAssinaturas(); j++){
					System.out.println("\n---------------\n");
					System.out.println("Assinatura: "+j);
					System.out.print("Valida: "+ resultados.get(j).toString());
					System.out.println("Nome no Certificado: "+ certificados.get(j).getNome());
					System.out.println("Validade De : "+certificados.get(j).getValidadeDe());
					System.out.println("Validade Ate : "+certificados.get(j).getValidadeAte());
					System.out.println("Data da Assinatura : "+assinaturaEnvelopingXML.getDataAssinatura(j));
//					System.out.println("SPRUI : "+assinaturaEnvelopedXML.getSPURI(j));
//					System.out.println("SigPolicyId : "+assinaturaEnvelopedXML.getSigPolicyId(j));
//					System.out.println("SignaturePolicyIdentifier : "+assinaturaEnvelopedXML.getSignaturePolicyIdentifier(j).item(0).getTextContent());
//					System.out.println("SigningCertificate : "+assinaturaEnvelopedXML.getSigningCertificate(j).item(0).getTextContent());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
