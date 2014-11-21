package gov.pr.celepar.tabeliao.taglibs;
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
import gov.pr.celepar.tabeliao.Constants;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.log4j.Logger;
/**
 *
 * TagLib para montagem de LOGIN com uso da Applet do Tabeliao.
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */
public class Login extends BodyTagSupport {
	
	private static Logger logger = Logger.getLogger(Login.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 133484565340795430L;

	private String corFundo;
	private String corDentro;
	private String corAbaSelecionada;
	private String corAba;
	private String tipoForm;
	private String valorBotao;
	private String caminhoBiblioteca;
	
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		StringBuffer out = new StringBuffer();
		
		drawScreenLogin(out);

		try {
			this.pageContext.getOut().write(out.toString());
		} catch (Exception ex) {
			logger.error("", ex);
			throw new JspException(ex);
		}

		return super.doEndTag();
	}
	
	private void drawScreenLogin(StringBuffer out){
		out.append("<script language=\"JavaScript\">\n")
		   .append("	function doLogin(){\n")
		   .append("		document.tabeliaoForm.submit();\n")
		   .append("	}\n")
		   .append("</script>\n")
		   .append("<form name=\"tabeliaoForm\" action=\"login.do\" method=\"post\">\n")
		   .append("<input type=\"hidden\" name=\"SENTINELA\" value=\"SENTINELA_REQUEST_LOGIN\">")
		   .append("<input type=\"hidden\" name=\"")
		   		.append(Constants.SESSION_UUID)
		   		.append("\" value=\"")
		   		.append(this.getSessionUUID())
		   		.append("\">\n")
		   .append("<input type=\"hidden\" name=\"CHAVE\">\n")
		   .append("</form>\n")
		   .append("<object codebase=\"http://java.sun.com/update/1.6.0/jinstall-6-windows-i586.cab\" name=\"TabeliaoApplet\">\n")
		   .append("	<param name=\"code\" value=\"gov.pr.celepar.tabeliao.client.applet.TabeliaoApplet\">\n")
		   .append("	<param name=\"archive\" value=\"TabeliaoApplet.jar\">\n")
		   .append("	<param name=\"type\" value=\"application/x-java-applet;version=1.6\">\n")
		   .append("	<param name=\"mayscript\" value=\"true\">\n")
		   .append("	<param name=\"scriptable\" value=\"true\">\n")
		   .append("	<comment>\n")
		   .append("	<embed\n")
		   .append("		type=\"application/x-java-applet;version=1.6\"\n")
		   .append("		code=\"gov.pr.celepar.tabeliao.client.applet.TabeliaoApplet\"\n")
		   .append("		archive=\"TabeliaoApplet.jar\"\n")
		   .append("		width=\"440\"\n")
		   .append("		height=\"181\"\n")
		   .append("		mayscript=\"true\"\n")
		   .append("		scriptable=\"true\"\n")
		   .append("		pluginspage=\"http://java.sun.com/products/plugin/index.html#download\"\n")
		   .append("		acao=\"autenticacao\"\n")
		   .append("		corFundo=\"" + corFundo + "\"\n")
		   .append("		corDentro=\"" + corDentro + "\"\n")
		   .append("		corAbaSelecionada=\"" + corAbaSelecionada + "\"\n")
		   .append("		corAba=\"" + corAba + "\"\n")
		   .append("		tipoForm=\"" + tipoForm + "\"\n")
		   .append("		valorBotao=\"" + valorBotao + "\"\n")
		   .append("		caminhoBiblioteca=\"" + caminhoBiblioteca + "\">\n")
		   .append("	</embed>\n")
		   .append("	<noembed>\n")
		   .append("		Esse navegador n�o suporta o assinador de documentos Tabeli�o,\n")
		   .append("		� necess�rio que o Java Plugin 1.6 ou superior esteja instalado.\n")
		   .append("		<script type=\"text/javascript\">\n")
		   .append("			alert('Esse navegador n�o suporta o assinador de documentos Tabeli�o,\\n' +\n")
		   .append("				'� necess�rio que o Java Plugin 1.6 ou superior esteja instalado.');\n")
		   .append("		</script>\n")
		   .append("	</noembed>\n")
		   .append("    </comment>\n")
		   .append("</object>");
		
	}
	
	private String getSessionUUID(){
		HttpSession session = this.pageContext.getSession();
		
//		String uuid = (String)session.getAttribute(Constants.SESSION_UUID);
		String uuid = (String)session.getAttribute(gov.pr.celepar.sentinela.Constants.SENTINELA_SECURITY_CODE);
		
		logger.debug("Carregando o UUID para a p�gina de login: " + uuid);
		
		return uuid;
	}

	public String getCorFundo() {
		return corFundo;
	}

	/**
	 * 
	 * @param corFundo ->  Define�a�cor�de�fundo�para�a�applet.�Valores�em�hexadecimal
	 */
	public void setCorFundo(String corFundo) {
		this.corFundo = corFundo;
	}
	
	public String getCorDentro() {
		return corDentro;
	}

	/**
	 * 
	 * @param corDentro ->  Define�a�cor�interna�para�a�applet.�Valores�em�hexadecimal
	 */
	public void setCorDentro(String corDentro) {
		this.corDentro = corDentro;
	}

	public String getCorAbaSelecionada() {
		return corAbaSelecionada;
	}

	/**
	 * 
	 * @param corAbaSelecionada ->  Define�a�cor�da aba quando selecionada na�applet.�Valores�em�hexadecimal
	 */
	public void setCorAbaSelecionada(String corAbaSelecionada) {
		this.corAbaSelecionada = corAbaSelecionada;
	}

	public String getCorAba() {
		return corAba;
	}

	/**
	 * 
	 * @param corAba ->  Define�a�cor�da aba na�applet.�Valores�em�hexadecimal
	 */
	public void setCorAba(String corAba) {
		this.corAba = corAba;
	}

	public String getTipoForm() {
		return tipoForm;
	}

	/**
	 * Cujo�valor�indica:
	 * Se�1�- Apenas�a�aba�para�uso�de�SmartCard/Token�sera�apresentada,�
	 * Se 2�- Apenas�a aba�para�uso�de�Arquivo�sera�apresentada,
	 * Se 3 - Ambas as abas para uso de SmartCard/Token ou Arquivos serao apresentadas.
	 * @param tipoForm -> Tipo�do�formulario�de�autenticacao	 *  
	 */
	public void setTipoForm(String tipoForm) {
		this.tipoForm = tipoForm;
	}

	public String getValorBotao() {
		return valorBotao;
	}

	/**
	 * 
	 * @param valorBotao -> Define o valor ou label que aparecera no botao de acao.
	 */
	public void setValorBotao(String valorBotao) {
		this.valorBotao = valorBotao;
	}

	public String getCaminhoBiblioteca() {
		return caminhoBiblioteca;
	}

	/**
	 * 
	 * @param caminhoBiblioteca -> Define o caminho da biblioteca (.so ou .dll) usada pela Leitora de Smartcard
	 *  ou Token.
	 */
	public void setCaminhoBiblioteca(String caminhoBiblioteca) {
		this.caminhoBiblioteca = caminhoBiblioteca;
	}
	
}
