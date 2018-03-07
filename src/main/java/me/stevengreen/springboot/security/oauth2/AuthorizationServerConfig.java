package me.stevengreen.springboot.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Configuration for Authorization server
 *
 * @author Steven Green
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    /**
     * Constructor for dependency injection
     *
     * @param authenticationManager AuthenticationManger instance
     */
    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Creates JwtTokenStore - Doesn't really store anything since JWTs do not need to persist.
     * <p>
     * Translates access tokens to and from authentications.
     *
     * @return Token store instance
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * Creates JwtAccessTokenConverter
     * <p>
     * Signs JWTs and helps translate signed JWTs to authentications and vice versa.
     *
     * @return JwtAccessTokenConverter instance
     */
    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("abcdefg");
        return converter;
    }

    /**
     * Custom token services to set token store and enable refresh tokens
     *
     * @return DefaultTokenServices instance
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    /**
     * Configues the security of the Authorization Server, specifically the /oauth/token endpoint.
     *
     * @param security The security configurer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    /**
     * Configures fundamentals for authorization server: client id, grant types, token expiration, etc.
     *
     * @param clients The configurer for client details services
     * @throws Exception from clients.inMemory() call
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("springboot-rest-client")
                .authorizedGrantTypes("refresh_token", "password")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(60 * 10)
                .refreshTokenValiditySeconds(60 * 60);
        // Don't use a secret since it can't be stored safely on browser front-end
        //.secret("secret");
    }

    /**
     * Configures non-security related aspects of the authorization server.
     * <p>
     * Provides token store, token converter, and authentication manager (necessary for password grants)
     *
     * @param endpoints The endpoints configurer
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager);
    }

}
