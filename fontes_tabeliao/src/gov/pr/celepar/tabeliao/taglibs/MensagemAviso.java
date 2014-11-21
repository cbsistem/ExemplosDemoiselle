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
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.log4j.Logger;

/**
 * TagLib para formatacao e apresentacao das mensagens de AVISO para a validacao do certificado
 * @author Thiago Meneghello - GIC/CELEPAR
 *
 */
public class MensagemAviso extends BodyTagSupport {
	
	private static Logger logger = Logger.getLogger(MensagemAviso.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 133484565340795430L;
	
	private String classe = null;
	private String src    = null;
	
	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		StringBuffer out = new StringBuffer();
		
		drawScreenMensagemAviso(out);

		try {
			this.pageContext.getOut().write(out.toString());
		} catch (Exception ex) {
			logger.error("", ex);
			throw new JspException(ex);
		}

		return super.doEndTag();
	}
	
	private void drawScreenMensagemAviso(StringBuffer out){
		ServletRequest servletRequest = this.pageContext.getRequest();
		
		String mensagem = (String)servletRequest.getAttribute(Constants.TABELIAO_MENSAGEM_AVISO);
		
		logger.debug("Mostrando a mensagem do Tabeliao");
		if(mensagem != null && ! mensagem.equals("")) {
			logger.debug("Mensagem de aviso: " + mensagem);
			out.append(mensagem);
		}
	}
	
}
