package com.spring.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.spring.gateway.service.CustomUserDetailService;

@Configuration
@EnableAuthorizationServer
public class OAuthServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("handbook")
				.secret(pwdEncoder.encode("handbook"))
				.authorizedGrantTypes("password","client_credentials", "refresh_token", "authorization_code", "implicit")
				//.authorizedGrantTypes("implicit")
				.scopes("read", "write")
				.autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)// .accessTokenConverter(defaultAccessTokenConverter())
					.userDetailsService(customUserDetailService);
	}

	//@Bean
	//public TokenStore tokenStore() {
	//	return new JwtTokenStore(defaultAccessTokenConverter());
	//}

	//@Bean
	//public JwtAccessTokenConverter defaultAccessTokenConverter() {
	//	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	//	converter.setSigningKey("123");
	//	return converter;
	//}

}
