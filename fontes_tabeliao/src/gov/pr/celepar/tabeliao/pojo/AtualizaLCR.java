package gov.pr.celepar.tabeliao.pojo;
// Generated 01/09/2006 13:27:07 by J-Querena using Hibernate Tools 3.1.0.beta5

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
import java.util.Date;

/**
 * Classe POJO para servico de atualizacao de LCR
 */
public class AtualizaLCR  implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 950245328620169148L;
	// Fields    
     /**
	 * 
	 */
     private String ip;
     private Date dtExecucao;
     private String servidor;
     // Constructors

    /** default constructor */
    public AtualizaLCR() {
    }

	/**
	 * 
	 * Construtor Minimo
	 * @param ip
	 * @param dtExecucao
	 */
	 
    public AtualizaLCR(String ip, Date dtExecucao) {
        this.ip = ip;
        this.dtExecucao = dtExecucao;
    }

    /**
	 * Construtor Completo
	 * @param ip
	 * @param dtExecucao
	 * @param servidor
	 */
    public AtualizaLCR(String ip, Date dtExecucao, String servidor) {
        this.ip = ip;
        this.dtExecucao = dtExecucao;
        this.servidor = servidor;
    }

	/**
	 * @return Endereco IP do Servidor de Atualiza��o
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip => Atribui o endere�o IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return Data e hora da �ltima execu��o do servi�o
	 */
	public Date getDtExecucao() {
		return dtExecucao;
	}

	/**
	 * @param dtExecucao => atribui a data e hora da �ltima execu��o do servi�o
	 */
	public void setDtExecucao(Date dtExecucao) {
		this.dtExecucao = dtExecucao;
	}

	/**
	 * @return Nome completo do Servidor de servi�o de atualiza��o
	 */
	public String getServidor() {
		return servidor;
	}

	/**
	 * @param servidor => atribui Nome completo do Servidor de servi�o de atualiza��o
	 */
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}   
    
}