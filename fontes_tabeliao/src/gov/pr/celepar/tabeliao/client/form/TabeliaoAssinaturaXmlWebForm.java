package gov.pr.celepar.tabeliao.client.form;
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
import gov.pr.celepar.tabeliao.util.Base64Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.struts.validator.ValidatorForm;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Classe que extende ValidatorForm (struts) para ser utilizada para geracao de 
 * assinatura em formato XMl em ambiente Servidor (WEB).
 * Fornece os metodos para recuperar os arquivos assinados.
 *  
 * @author Emerson Sachio Saito - GIC/CELEPAR
 *
 */
public class TabeliaoAssinaturaXmlWebForm extends ValidatorForm {
	
	private static final long serialVersionUID = 4505354277218770615L;
	
	private String[] campoArquivosXmlAssinados;

	/**
	 * 
	 * @return array de string com o conteudo de todos os arquivos que foram assinados, codificados em base64.
	 */
	public String[] getCampoArquivosXmlAssinados() {
		return campoArquivosXmlAssinados;
	}

	/**
	 * 
	 * @param campoArquivosXmlAssinados -> array de string com o conteudo dos arquivos assinados.
	 */
	public void setCampoArquivosXmlAssinados(String[] campoArquivosXmlAssinados) {
		this.campoArquivosXmlAssinados = campoArquivosXmlAssinados;
	}
	
	/**
	 * 
	 * @return matriz de bytes contendo em cada linha o conteudo dos arquivos assinados, decodificados da 
	 * base64.
	 */
	public byte[][] getConteudoCampoArquivosXmlAssinados() {
		
		if(campoArquivosXmlAssinados == null) {
			return null;
		}
		byte[][] conteudoCampo = new byte[campoArquivosXmlAssinados.length][];
		
		for(int i=0 ; i<campoArquivosXmlAssinados.length ; i++) {
			conteudoCampo[i] = Base64Utils.base64Decode(campoArquivosXmlAssinados[i]);
		}
		return conteudoCampo;
	}
	
	/**
	 * 
	 * @return Lista dos arquivos assinados no formato Document (XML), decodificados e validos.
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<Document> getArquivosXmlAssinados() throws SAXException, IOException, ParserConfigurationException {
		if(campoArquivosXmlAssinados == null) {
			return null;
		}
		
		List<Document> ArquivosXml = new ArrayList<Document>();
		
		byte[][] conteudo = new byte[campoArquivosXmlAssinados.length][];
		
		
		for(int i=0 ; i<campoArquivosXmlAssinados.length ; i++) {
			conteudo[i] = Base64Utils.base64Decode(campoArquivosXmlAssinados[i]);
		}
		
		for(int j=0 ; j<campoArquivosXmlAssinados.length ; j++) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			Document docAssinado = null;
			InputSource novoImput = new InputSource(new ByteArrayInputStream(conteudo[j]));
			docAssinado = dbf.newDocumentBuilder().parse(novoImput);
			ArquivosXml.add(docAssinado);			
		}		
		return ArquivosXml;
	}		
}