package com.syr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author Ebin
 * @on 28-Sep-2018
 * @version
 */
@Configuration
@EnableAuthorizationServer
public class AuthorisationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${app.auth.token.secret}")
	private String appAuthTokenSecret;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()")
			.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("app-client")
				.authorizedGrantTypes("client-credentials", "password", "refresh_token")
				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
				.scopes("read", "write", "trust")
				.accessTokenValiditySeconds(120)
				.secret(encoder.encode(appAuthTokenSecret))
				.refreshTokenValiditySeconds(120);

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.exceptionTranslator(e -> {
					if (e instanceof OAuth2Exception) {
						OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
						
						System.out.println("Oauth Ex1.........................."+oAuth2Exception.getMessage());

						return ResponseEntity.status(oAuth2Exception.getHttpErrorCode())
								.body(new CustomOauthException(oAuth2Exception.getMessage()));
					} else {
						if (e instanceof UsernameNotFoundException) {
							OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
							
							System.out.println("Oauth Ex2.........................."+oAuth2Exception.getMessage());

							return ResponseEntity.status(oAuth2Exception.getHttpErrorCode())
									.body(new CustomOauthException(oAuth2Exception.getMessage()));
						} else {
							return ResponseEntity.status(401).body(new CustomOauthException("User Not Found"));
						}
					}
				});
	}
}
