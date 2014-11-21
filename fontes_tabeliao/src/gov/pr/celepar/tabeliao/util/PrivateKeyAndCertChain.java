package gov.pr.celepar.tabeliao.util;
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
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * Estrutura de dados que ira deter o par de chaves e 
 * a cadeia de certificacao correspondente as chaves.
 * @author Thiago Meneghello - GIC/CELEPAR
 */
public class PrivateKeyAndCertChain {
    public PrivateKey mPrivateKey;
    public Certificate[] mCertificationChain;
}
