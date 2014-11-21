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
import gov.pr.celepar.tabeliao.util.Base64Utils;
import gov.pr.celepar.tabeliao.util.CertificationChainAndSignatureBase64;
import gov.pr.celepar.tabeliao.util.PrivateKeyAndCertChain;
import javax.swing.JApplet;
import netscape.javascript.JSObject;
/**
 * Classe de acao para efetuar Autenticacao com certificados em arquivo ou hardware
 * 
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */
public class ActionAutenticacao implements ActionHandler {
	
	private JApplet applet;

	/**
	 * 
	 * @param applet
	 */
	public ActionAutenticacao(JApplet applet) {
		this.applet = applet;
	}

	/**
	 * @param arquivo -> informado se o certificado estiver armazenado em formato PKCS#12
	 * @param pin 
	 * @param isHardware -> true se o certificado estiver armazenado em hardware (smartcard/token)
	 */
	public boolean execute(String arquivo, String pin, boolean isHardware){
    	//Tenta pegar o keyStore que possui o certificado do usuario
		PrivateKeyAndCertChain privateKey;
		try {
			privateKey = TabeliaoAppletUtil.loadPrivateKeyAndCertChain(arquivo, pin, isHardware);
		} catch (DocumentSignException e) {
	        String mensagem = "Ocorreu um erro ao carregar a chave privada do certificado.\n" +
				"Motivo: " + e.getMessage();
			TabeliaoAppletUtil.showMensagem(applet, mensagem,2);
			return false;
		}
		
		//Aqui ja esta com o keyStore, pode assinar o UUID
		//E mandar para o tabeli�o autenticar o usuario.
		JSObject window   = JSObject.getWindow(applet);
		JSObject document = (JSObject)window.getMember("document");
        JSObject form     = (JSObject)document.getMember("tabeliaoForm");
        JSObject field    = null;
        field = (JSObject) form.getMember("TABELIAO_SESSION_UUID");
        String uuid = (String)field.getMember("value");

        //Assina o uuid e codifica em Base64 para mandar para o tabeliao.
        CertificationChainAndSignatureBase64 ccs64;
        try {
			ccs64 = TabeliaoAppletUtil.signDocument(uuid.getBytes(), privateKey, true);
		} catch (DocumentSignException e) {
			TabeliaoAppletUtil.showMensagem(applet, "Ocorreu um erro na autentica��o do usu�rio.\nMotivo: " 
								+ e.getMessage(),2);
			return false;
		}
        String assinatura = Base64Utils.base64Encode(ccs64.mSignature);
        
        field = (JSObject) form.getMember("CHAVE");
        field.setMember("value", assinatura);
        
        privateKey = null;        
        //Manda um submit para a tela de autentica��o
        window.eval("doLogin()");
              
       	return true;	   
	}	
}