package me.stevengreen.springboot.security.oauth2;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

	/**
     * Extracts JWT content into authentication object
     * @author Steven Green
     *
     */
    public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

		@Override
		public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
			OAuth2Authentication auth = super.extractAuthentication(map);
            auth.setDetails(map); //this will get spring to copy JWT content into Authentication
            return auth;
		}
		
		@Override
		public void configure(JwtAccessTokenConverter converter) {
			converter.setAccessTokenConverter(this);
		}
    	
    }