/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */

package br.com.petrobras.ppgi.ca.facade

import br.com.petrobras.security.ISecurityContext
import br.com.petrobras.security.configuration.SecuritySettings
import br.com.petrobras.security.configuration.StandaloneSecurityConfigurer
import br.com.petrobras.security.exception.ConfigurationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 */
class CaContextualizedTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaContextualizedTest)

    private static ISecurityContext securityContext = null;

    private static final String login = "PPGI_JUNIT";
    private static final String pass = "kwknD8cl";

    void setUp() {
        if (securityContext == null ) {
            Properties properties = new Properties();
            try {
                //properties.load(new FileInputStream("config.properties"));
                properties.load(CaContextualizedTest.class.getClassLoader().getResourceAsStream("config.properties"));
                SecuritySettings.load(new StandaloneSecurityConfigurer(), properties, true);
            } catch (IOException e) {
                LOGGER.error("Não foi possível ler o arquivo de configurações do CA.");
                throw e;
            } catch (ConfigurationException ce) {
                LOGGER.error("Não foi possível carregar as configurações do CA.");
                throw ce;
            }
            securityContext = ISecurityContext.getContext()
            securityContext.getUserAuthenticator().logon(login, pass);
        }
    }

    public getSecurityContext(){
        return CaContextualizedTest.securityContext
    }
}
