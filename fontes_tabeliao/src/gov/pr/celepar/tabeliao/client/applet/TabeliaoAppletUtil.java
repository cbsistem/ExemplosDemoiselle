package gov.pr.celepar.tabeliao.client.applet;
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
import gov.pr.celepar.tabeliao.client.applet.cms.CMSTabeliaoSignedDataGenerator;
import gov.pr.celepar.tabeliao.util.Base64Utils;
import gov.pr.celepar.tabeliao.util.CertificationChainAndSignatureBase64;
import gov.pr.celepar.tabeliao.util.PrivateKeyAndCertChain;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AuthProvider;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;

/**
 * Classe de metodos utilitarios para a geracao das assinaturas.
 * 
 * @version 1.1
 * @author Thiago Meneghello - GIC/CELEPAR
 * @author Emerson Sachio Saito - GIC/CELEPAR
 *
 */

public class TabeliaoAppletUtil {
	
    private static final String SUN_PKCS11_PROVIDER_CLASS = "sun.security.pkcs11.SunPKCS11";
    private static final String PKCS11_KEYSTORE_TYPE = "PKCS11";
    private static final String PKCS12_KEYSTORE_TYPE = "PKCS12";
    
    
//    private static final String DIGITAL_SIGNATURE_ALGORITHM_NAME = "SHA1withRSA";
    
    public static String providerName = null;
    public static Provider pkcs11Provider = null;
    
    /**
     * Faz a leitura da Chave Privada e a Cadeia de Certificados, usando a senha informada.
     * @param arquivo - Se o certificado esta armazendo em arquivo
     * @param pin - senha do certificado
     * @param isHardware boolean - Se o certificado esta armazendo em arquivo
     * @return PrivateKeyAndCertChain Chave privada e a cadeia de certificados
     * @throws DocumentSignException
     */
	
    public static PrivateKeyAndCertChain loadPrivateKeyAndCertChain(String arquivo, String pin, boolean isHardware) throws DocumentSignException {
        
        if (isHardware) {
            if (arquivo.length() == 0) {
                String errorMessage = "Voc� deve selecionar uma biblioteca PKCS#11!";
                throw new DocumentSignException(errorMessage);
            }

        	KeyStore keyStore = null;
        	try {
        		keyStore = loadKeyStoreFromSmartCard(arquivo, pin);
			} catch (KeyStoreException e) {
	            String errorMessage = "N�o foi poss�vel ler o keyStore do Token/SmartCard.\n" +
	            	"Detalhe do problema: " + e.getMessage();
	            throw new DocumentSignException(errorMessage, e);
			} finally {
            	pin = null;
			}
			
			try {
			    Enumeration<String> aliasesEnum = keyStore.aliases();
			    if (aliasesEnum.hasMoreElements()) {
			        String alias = aliasesEnum.nextElement();
			        Certificate[] certificationChain = keyStore.getCertificateChain(alias);
			        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, null);
			        PrivateKeyAndCertChain result = new PrivateKeyAndCertChain();
			        result.mPrivateKey = privateKey;
			        result.mCertificationChain = certificationChain;
			        return result;
			    } else {
			        throw new KeyStoreException("O keyStore est� vazio!");
			    }
			} catch (Exception e) {
    	        String errorMessage = "N�o foi poss�vel ler a chave privada e o certificado do smart card.\n" + 
    	        	"Raz�o: " + e.getMessage();
    	        throw new DocumentSignException(errorMessage, e);
			} finally {
				keyStore = null;
			}
        } else {
        	providerName = null;
        	
            // Load the keystore from specified file using the specified password
            if (arquivo.length() == 0) {
                String errorMessage = "Voc� deve selecionar um arquivo de certificado (.PFX ou .P12)!";
                throw new DocumentSignException(errorMessage);
            }
            
            KeyStore keyStore = null;
            try {
            	keyStore = loadKeyStoreFromPFXFile(arquivo, pin);
            } catch (KeyStoreException e) {
            	pin = null;
	            String errorMessage = "N�o foi poss�vel ler o keyStore do arquivo.\n" +
	            	"Detalhe do problema: " + e.getMessage();
//            	String errorMessage = "N�o foi poss�vel ler o arquivo de certificado  (" +
//                    arquivo + ").\n senha senha informada n�o � v�lida, ou o arquivo n�o � um Certificado no formato PKCS#12 (.P12 or .PFX) " +
//                    "ou pode estar corrompido/adulterado.";
                throw new DocumentSignException(errorMessage, e);
            }
            
			try {
			    Enumeration<String> aliasesEnum = keyStore.aliases();
			    if (aliasesEnum.hasMoreElements()) {
			        String alias = aliasesEnum.nextElement();
			        Certificate[] certificationChain = keyStore.getCertificateChain(alias);
			        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, pin.toCharArray());
			        PrivateKeyAndCertChain result = new PrivateKeyAndCertChain();
			        result.mPrivateKey = privateKey;
			        result.mCertificationChain = certificationChain;
			        return result;
			    } else {
			        throw new KeyStoreException("O keyStore est� vazio!");
			    }
			} catch (Exception e) {
    	        String errorMessage = "N�o foi poss�vel ler a chave privada e o certificado do smart card.\n" + 
    	        	"Raz�o: " + e.getMessage();
    	        throw new DocumentSignException(errorMessage, e);
			} finally {
				keyStore = null;
            	pin = null;
			}
        }
    }

    /**
     * Gera assinatura de um documento no formato CMS/PKCS#7
     * @param aDocumentToSign - Documento para assinar
     * @param privateKeyAndCertChain
     * @param anexaArquivo - True se gera anexado.
     * @return CertificationChainAndSignatureBase64
     * @see CertificationChainAndSignatureBase64 
     * @throws DocumentSignException
     */
    public static CertificationChainAndSignatureBase64 signDocument(
			byte[] aDocumentToSign, PrivateKeyAndCertChain privateKeyAndCertChain, boolean anexaArquivo)
			throws DocumentSignException {
	
	    // Checa se a chave privada est� dispon�vel
	    PrivateKey privateKey = privateKeyAndCertChain.mPrivateKey;
	    if (privateKey == null) {
	        String errorMessage = "N�o achou a chave privada do smart card.";
	        throw new DocumentSignException(errorMessage);
	    }

	    // Checa se a cadeia de certifica��o em  X.509 est� dispon�vel
	    Certificate[] certChain = privateKeyAndCertChain.mCertificationChain;
	    if (certChain == null) {
	        String errorMessage = "N�o achou o certificado p�blico.";
	        throw new DocumentSignException(errorMessage);
	    }
	    
	    // Cria o objeto de resultado
	    CertificationChainAndSignatureBase64 signingResult =
	        new CertificationChainAndSignatureBase64();

	    // Salva a cadeia de certifica��o X.509 em um resultado codificado em Base64
	    try {
	        signingResult.mCertificationChain = Base64Utils.encodeX509CertChainToBase64(certChain);
	    } catch (CertificateException cee) {
	        String errorMessage = "Certificado inv�lido.";
	        throw new DocumentSignException(errorMessage);
	    }

	    // Calcula a assinatura digital para o arquivo (hash),
	    // Codifica em  Base64 e salva no resultado
	    try {
	    	CMSSignedDataGenerator signer = new CMSSignedDataGenerator();
	    	CMSProcessable proc = new CMSProcessableByteArray(aDocumentToSign);
	
	    	List<Certificate> list  = new ArrayList<Certificate>();
	    	for(int i=0 ; i<certChain.length ; i++) {
	        	list.add(certChain[i]);
	    	}
	    	
	    	CertStore certStore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(list));
	    	
	    	signer.addCertificatesAndCRLs(certStore);
	    	
	    	signer.addSigner(privateKey, (X509Certificate)certChain[0], CMSSignedDataGenerator.DIGEST_SHA1);

			CMSSignedData data = signer.generate(CMSSignedDataGenerator.DATA, proc, anexaArquivo, providerName);
			
			signingResult.mSignature = data.getEncoded();
	    } catch (Exception gsex) {
	        String errorMessage = "Erro ao assinar o arquivo.\n" +
	            "Detalhes: " + gsex.getMessage();
	        gsex.printStackTrace();
	        throw new DocumentSignException(errorMessage, gsex);
	    }
	
	    return signingResult;
	}
    
    /**
     * Assina o arquivos e retorna a assinatura e a cadeia de certificado
     * 
     * @param aFileName -> Nome do arquivo
     * @param privateKeyAndCertChain -> usando loadPrivateKeyAndCertChain 
     * @param anexaArquivo -> Anexa o arquivo com a assinatura
     * @return CertificationChainAndSignatureBase64
     * @see CertificationChainAndSignatureBase64
     * @throws DocumentSignException
     */
    public static CertificationChainAndSignatureBase64 signFile(String aFileName, PrivateKeyAndCertChain privateKeyAndCertChain, boolean anexaArquivo)
    		throws DocumentSignException {

        // Carrega o arquivo para ser assinado
        byte[] documentToSign = null;
        try {
            documentToSign = readFileInByteArray(aFileName);
        } catch (IOException ioex) {
            String errorMessage = "N�o foi poss�vel ler o arquivo para assinar " + aFileName + ".";
            throw new DocumentSignException(errorMessage, ioex);
        }

        try {

            // Efetua a assinatura.
            CertificationChainAndSignatureBase64 signingResult =
                signDocument(documentToSign, privateKeyAndCertChain, anexaArquivo);
            return signingResult;
        } finally {
        }
    }
    
    /**
     * Assina o Hash do Documento
     * @param hash -> Hash do arquivo 
     * @param privateKeyAndCertChain -> use loadPrivateKeyAndCertChain  
     * @return CertificationChainAndSignatureBase64
     * @see CertificationChainAndSignatureBase64
     * @throws DocumentSignException
     */
    public static CertificationChainAndSignatureBase64 signHashDocument(
			byte[] hash, PrivateKeyAndCertChain privateKeyAndCertChain)
			throws DocumentSignException {
	
	    // Checa se a chave privada est� dispon�vel
	    PrivateKey privateKey = privateKeyAndCertChain.mPrivateKey;
	    if (privateKey == null) {
	        String errorMessage = "N�o achou a chave privada do smart card.";
	        throw new DocumentSignException(errorMessage);
	    }

	    // Checa se a cadeia de certifica��o X.509 est� dispon�vel
	    Certificate[] certChain = privateKeyAndCertChain.mCertificationChain;
	    if (certChain == null) {
	        String errorMessage = "N�o achou o certificado p�blico.";
	        throw new DocumentSignException(errorMessage);
	    }
   
	    // Cria o objeto de resultado
	    CertificationChainAndSignatureBase64 signingResult =
	        new CertificationChainAndSignatureBase64();

	    // Salva a cadeia de certifica��o X.509 no resultado codificado em Base64
	    try {
	        signingResult.mCertificationChain = Base64Utils.encodeX509CertChainToBase64(certChain);
	    }
	    catch (CertificateException cee) {
	        String errorMessage = "Certificado inv�lido no smart card.";
	        throw new DocumentSignException(errorMessage);
	    }

	    // Calcula a assinatura digital do hash,
	    // Codifica em Base64 e salva no resultado
	    try {
	    	//Eh aqui que a assinatura do HASH eh realizada
	    	CMSTabeliaoSignedDataGenerator signer = new CMSTabeliaoSignedDataGenerator();
	    	CMSProcessable proc = new CMSProcessableByteArray(hash);
	
	    	List<Certificate> list  = new ArrayList<Certificate>();
	    	for(int i=0 ; i<certChain.length ; i++) {
	        	list.add(certChain[i]);
	    	}
	    	
	    	CertStore certStore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(list));
	    	
	    	signer.addCertificatesAndCRLs(certStore);
	    	
	    	signer.addSigner(privateKey, (X509Certificate)certChain[0], CMSTabeliaoSignedDataGenerator.DIGEST_SHA1);

			CMSSignedData data = signer.generate(CMSTabeliaoSignedDataGenerator.DIGEST_SHA1, proc, false, providerName, true);
			
			signingResult.mSignature = data.getEncoded();
	    } catch (Exception gsex) {
	        String errorMessage = "Erro ao assinar o arquivo.\n" +
	            "Detalhes: " + gsex.getMessage();
	        gsex.printStackTrace();
	        throw new DocumentSignException(errorMessage, gsex);
	    }
	
	    return signingResult;
	}
    
    /**
     * 
	 * Exibe uma caixa de dialogo com uma mensagem.
	 * 
     * @param applet
     * @param mensagem -> String com a mensagem a ser exibida
     * @param tipo -> Tipo da mensagem, sendo: 0 = Informacao, 1 = Alerta e 2 = Erro 
     */
    public static void showMensagem(JApplet applet, String mensagem, int tipo) {
    	
        String[] botao = new String[]{""};
        botao[0]= "Fechar";
        String Titulo;
        int tipoMsg;
        switch (tipo) {
        	case 0:  Titulo= "Informa��o do Sistema."; tipoMsg=JOptionPane.INFORMATION_MESSAGE;  break;
        	case 1:  Titulo= "Alerta do Sistema!"; tipoMsg=JOptionPane.WARNING_MESSAGE;  break;
        	case 2:  Titulo= "Erro na Execu��o!"; tipoMsg=JOptionPane.ERROR_MESSAGE;  break;
        	default: Titulo= "Informa��o do Sistema!"; tipoMsg=JOptionPane.INFORMATION_MESSAGE;  break;
    }                
        JOptionPane.showOptionDialog(applet,  
                mensagem,  
                Titulo,  
                JOptionPane.YES_OPTION,  
                tipoMsg,   
                null,  
                botao,
                botao[0]);
    	
    	//JOptionPane.showMessageDialog(applet, message, "Mensagem do Sistema",JOptionPane.NO_OPTION);    	
    }
    
	/**
	 * Carrega o chaveiro do smartcard/token usando a implementacao da biblioteca PKCS#11
	 * e o provider de seguranca PKCS#11 da Sun.
	 * Sera requisitado o PIN para acessar o smartcard/token
	 * 
	 * @param aPKCS11LibraryFileName -> local da .so ou .dll para Leitora ou Token
	 * @param aSmartCardPIN -> PIN para acesso ao smartcard
	 * @return Keystore
	 * @see java.security.KeyStore
	 * @throws KeyStoreException
	 */
	private static KeyStore loadKeyStoreFromSmartCard(String aPKCS11LibraryFileName, String aSmartCardPIN) throws KeyStoreException {
		
	    // First configure the Sun PKCS#11 provider. It requires a stream (or file)
	    // containing the configuration parameters - "name" and "library".
	    String pkcs11ConfigSettings = "name = SmartCard\n" + "library = " + aPKCS11LibraryFileName;
	    byte[] pkcs11ConfigBytes = pkcs11ConfigSettings.getBytes();
	    ByteArrayInputStream confStream = new ByteArrayInputStream(pkcs11ConfigBytes);
	   	    
	    // Instantiate the provider dynamically with Java reflection
        Class<?> sunPkcs11Class;
        Constructor<?> pkcs11Constr;
        
       
		try {
			sunPkcs11Class = Class.forName(SUN_PKCS11_PROVIDER_CLASS);
			pkcs11Constr = sunPkcs11Class.getConstructor(java.io.InputStream.class);
			pkcs11Provider = (Provider) pkcs11Constr.newInstance(confStream);
	        providerName = pkcs11Provider.getName();
	        Security.addProvider(pkcs11Provider);
		} catch (SecurityException e) {
			throw new KeyStoreException("Sem permiss�o para instanciar a Classe '" + SUN_PKCS11_PROVIDER_CLASS + "'.", e);
		} catch (ClassNotFoundException e) {
			throw new KeyStoreException("A Classe '" + SUN_PKCS11_PROVIDER_CLASS + "' n�o foi encontrada.", e);
		} catch (NoSuchMethodException e) {
			//Nao deve entrar aqui!!!
			throw new KeyStoreException("O construtor da Classe '" + SUN_PKCS11_PROVIDER_CLASS + "' n�o foi encontrado.", e);
		} catch (IllegalArgumentException e) {
			//Nao deve entrar aqui!!!
			throw new KeyStoreException("Argumentos inv�lidoa na chamada da Classe '" + SUN_PKCS11_PROVIDER_CLASS + "'.", e);
		} catch (InstantiationException e) {
			//Nao deve entrar aqui!!!
			throw new KeyStoreException("Ocorreu um erro ao criar uma nova inst�ncia da Classe '" + SUN_PKCS11_PROVIDER_CLASS + "'.", e);
		} catch (IllegalAccessException e) {
			//Nao deve entrar aqui!!!
			throw new KeyStoreException("Sem permiss�o para instanciar a Classe '" + SUN_PKCS11_PROVIDER_CLASS + "'.", e);
		} catch (InvocationTargetException e) {
			//Nao deve entrar aqui!!!
			throw new KeyStoreException("N�o pode chamar o m�todo da Classe '" + SUN_PKCS11_PROVIDER_CLASS + "'.", e);
		}

//	    try {
//	    } catch (Exception e) {
//	        throw new KeyStoreException("N�o foi poss�vel inicializar o provedor de seguranca PKCS#11. " +
//	            "Raz�o: " + e.getCause().getMessage());
//	    }
	
	    // Read the keystore from the smart card
	    char[] pin = aSmartCardPIN.toCharArray();
	    KeyStore keyStore = null;
		try {
			keyStore = KeyStore.getInstance(PKCS11_KEYSTORE_TYPE);
			keyStore.load(null, pin);
		} catch (KeyStoreException e) {
			throw new KeyStoreException("Erro ao tentar carregar as chaves do certificado do Token/SmartCard.\n" +
										"O Cart�o SmartCard n�o est� inserido na leitora,\n" +
										"ou o Token n�o conectado ao computador, \n" +
										"ou h� problemas t�cnicos com a Leitora/Cart�o ou Token! \n" +
										"Verifique a posi��o do Cart�o para tentar novamente.\n" +
										"Feche e abra novamente o Navegador!\n"+
										"Estando aparentemente tudo correto: Anote este Erro!,\n" +
										" Feche o Navegador e acione o suporte t�cnico!", e);
		} catch (NoSuchAlgorithmException e) {
			throw new KeyStoreException("Erro ao tentar carregar o algoritmo para validar o certificado.\n" +
										"Anote este Erro!, Feche a aplica��o e acione o suporte t�cnico!", e);
		} catch (CertificateException e) {
			throw new KeyStoreException("Erro ao tentar ler o certificado do Token/SmartCard.\n" +
										"Anote este Erro!, Feche a aplica��o e acione o suporte t�cnico!", e);
		} catch (IOException e) {
			throw new KeyStoreException("A informada Senha N�O � v�lida, \n" +
										"ou Cart�o SmartCard n�o est� inserido na leitora,\n" +
										"ou Token n�o conectado ao computador, \n" +
										"ou h� problemas t�cnicos com a Leitora/Cart�o ou Token!\n" +
										"Voc� pode informar a senha novamente! \n" +
										"CUIDADO: mais de 3 (tr�s) tentativas erradas ir�o bloquear seu CART�O/TOKEN!\n" +
										"Persistindo o problema acione o suporte t�cnico!", e);
		}
	    
	    return keyStore;
	}

	/**
	 * Efetua logout do SmartCard
	 * @throws LoginException 
	 */
	public static void logoutSmart () throws LoginException{
		if (pkcs11Provider != null){
			AuthProvider ap = (AuthProvider) pkcs11Provider;
			ap.logout();
			pkcs11Provider = null;
		}
		if (providerName != null){
			Security.removeProvider(providerName);
			providerName = null;
		}
	}

    /**
     * Carrega o chaveiro de um arquivo de certificado .PFX ou .P12 (devera estar no formato PKCS#12)
     * usando a senha para o certificado
     * @param aFileName -> local fisico onde esta armazenado o certificado
	 * @param aKeyStorePasswd -> senha para acesso ao certificado
	 * @return Keystore
	 * @see java.security.KeyStore
	 * @throws KeyStoreException
     */
    private static KeyStore loadKeyStoreFromPFXFile(String aFileName, String aKeyStorePasswd) throws KeyStoreException {
        KeyStore keyStore = null;
        FileInputStream keyStoreStream;
        char[] password = aKeyStorePasswd.toCharArray();
        
		try {
			keyStore = KeyStore.getInstance(PKCS12_KEYSTORE_TYPE);
			keyStoreStream = new FileInputStream(aFileName);
			keyStore.load(keyStoreStream, password);
		} catch (KeyStoreException e) {
			throw new KeyStoreException("N�o foi poss�vel carregar as chaves do certificado do tipo '" + PKCS12_KEYSTORE_TYPE + "'.\n" +
										"Anote este Erro!, Feche a aplica��o e acione o suporte t�cnico!", e);
		} catch (FileNotFoundException e) {
	
			throw new KeyStoreException("N�o foi poss�vel localizar o arquivo '" + aFileName + "'.\n" +
										"Informe novamente, e certifique-se de que o arquivo � v�lido.", e);
		} catch (NoSuchAlgorithmException e) {
			throw new KeyStoreException("N�o foi poss�vel encontrar o algoritmo para validar o certificado. \n" +
										"Anote este Erro!, Feche a aplica��o e acione o suporte t�cnico! ", e);
		} catch (CertificateException e) {
			throw new KeyStoreException("N�o foi poss�vel carregar o certificado do KeyStore.\n" +
										"Anote este Erro!, Feche a aplica��o e acione o suporte t�cnico!", e);
		} catch (IOException e) {
			throw new KeyStoreException("Senha informada n�o � valida!, \n" +
										"ou o arquivo pode estar corrompido/adulterado!\n" +
										"Informe a senha novamente, ou acione o suporte t�cnico!", e);
		}
		
        return keyStore;
    }	
	
	/**
	 * faz a leitura de um arquivo especifico em um array de byte.
	 * 
	 * @param aFileName -> Nome do arquivo
	 * @return byte[]
	 * @throws IOException
	 */
	private static byte[] readFileInByteArray(String aFileName)
			throws IOException {
	    File file = new File(aFileName);
	    FileInputStream fileStream = new FileInputStream(file);
	    try {
	        int fileSize = (int) file.length();
	        byte[] data = new byte[fileSize];
	        int bytesRead = 0;
	        while (bytesRead < fileSize) {
	            bytesRead += fileStream.read(data, bytesRead, fileSize-bytesRead);
	        }
	        return data;
	    }
	    finally {
	        fileStream.close();
	    }
	}
}