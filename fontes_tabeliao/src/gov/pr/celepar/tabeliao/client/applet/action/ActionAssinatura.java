package gov.pr.celepar.tabeliao.client.applet.action;
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
import gov.pr.celepar.tabeliao.client.applet.DocumentSignException;
import gov.pr.celepar.tabeliao.client.applet.TabeliaoAppletUtil;
import gov.pr.celepar.tabeliao.util.CertificationChainAndSignatureBase64;
import gov.pr.celepar.tabeliao.util.PrivateKeyAndCertChain;
import java.io.FileOutputStream;
import javax.swing.JApplet;
import netscape.javascript.JSException;

/**
 * Classe de acao para efetuar Assinatura PKCS#7/CMS , em ambiente local do usuario
 * 
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */
public class ActionAssinatura implements ActionHandler {
	
	private JApplet applet;
	private String[] arquivos;
	private boolean anexaArquivo;
	
	/**
	 * 
	 * @param applet
	 * @param arquivo -> Arquivo a ser assinado.
	 * @param anexaArquivo -> true se gerar assinatura em formato anexado
	 */
	public ActionAssinatura(JApplet applet, String arquivo, boolean anexaArquivo) {
		this(applet, new String[] { arquivo }, anexaArquivo);
	}

	/**
	 * 
	 * @param applet
	 * @param arquivos -> Lista de Arquivos a serem assinados.
	 * @param anexaArquivo -> true se gerar assinatura em formato anexado
	 */
	public ActionAssinatura(JApplet applet, String[] arquivos, boolean anexaArquivo) {
		this.applet = applet;
		this.arquivos = arquivos;
		this.anexaArquivo = anexaArquivo;
	}
	
	/**
	 * 
	 * @param arquivo -> arquivo a ser assinado
	 * @param pin 
	 * @param isHardware -> true se o certificado estiver armazenado em hardware (smartcard/token)
	 */
	public boolean execute(String arquivo, String pin, boolean isHardware){
    	//Tenta pegar o keyStore que possui o certificado do camarada
		PrivateKeyAndCertChain privateKey;
		try {
			privateKey = TabeliaoAppletUtil.loadPrivateKeyAndCertChain(arquivo, pin, isHardware);
		} catch (DocumentSignException e1) {
			TabeliaoAppletUtil.showMensagem(applet, "Ocorreu um erro ao carregar a chave privada do certificado.\nMotivo: "
											+ e1.getCause().getMessage(),2);
			return false;
		}

    	try {
            // Perform the actual file signing
    		int qtdeArquivos = 0;
    		String arquivosAssinados = "";
    		for(int i=0 ; i<arquivos.length ; i++) {
    			String nomeArquivo = arquivos[i];
                CertificationChainAndSignatureBase64 signingResult = TabeliaoAppletUtil.signFile(nomeArquivo, privateKey, anexaArquivo);
                
                if (signingResult != null) {
                	
                	FileOutputStream fos = new FileOutputStream(nomeArquivo + ".pkcs7");
                	
                	fos.write(signingResult.mSignature);
                	
                	fos.flush();
                	fos.close();
                	
                	if(arquivosAssinados.length() > 0) {
                		arquivosAssinados += "\n";
                	}
                	qtdeArquivos++;
                	arquivosAssinados += nomeArquivo;
                } else {
                	
                	if(arquivosAssinados.length() > 0) {
                		if(qtdeArquivos == 1) {
                			TabeliaoAppletUtil.showMensagem(applet, "Arquivo assinado com sucesso: \n"
                										   + arquivosAssinados + "",0);
                		} else {
                			TabeliaoAppletUtil.showMensagem(applet, qtdeArquivos + " arquivos assinados com sucesso:\n" 
                											+ arquivosAssinados + "",0);
                		}
                	}
                	
                	throw new DocumentSignException("Ocorreu um erro ao assinar o arquivo '" + nomeArquivo + "'.");
                }
    		}
        	if(arquivosAssinados.length() > 0) {
        		if(qtdeArquivos == 1) {
        			TabeliaoAppletUtil.showMensagem(applet, "Arquivo assinado com sucesso: \n" 
        										+ arquivosAssinados + "",0);
        		} else {
        			TabeliaoAppletUtil.showMensagem(applet, qtdeArquivos + " arquivos assinados com sucesso:\n" 
        										+ arquivosAssinados + "",0);
        		}
        	}
        }
        catch (DocumentSignException dse) {
            // Falhou ao assinar o documento 
            String errorMessage = dse.getMessage();
			TabeliaoAppletUtil.showMensagem(applet, errorMessage,2);
			return false;
        }
        catch (SecurityException se) {
            se.printStackTrace();
			TabeliaoAppletUtil.showMensagem(applet, 
                "Erro ao acessar a unidade de disco local.\n" +
                "Este applet deve ser inicializado com as permiss�es de seguranca total.\n" +
                "Por favor, aceite o certificado quando o Plug-In do Java perguntar.",2);
			return false;
        }
        catch (JSException jse) {
            jse.printStackTrace();
            TabeliaoAppletUtil.showMensagem(applet,
                "N�o foi poss�vel acessar alguns campos do formul�rio HTML.\n" +
                "Cheque os par�metros.",2);
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            TabeliaoAppletUtil.showMensagem(applet, "Erro inesperado: " + e.getMessage(),2);
            return false;
        }        
		return true;
	}	
}