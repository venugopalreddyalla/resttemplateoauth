package com.cognine.resttemplateoauth;

import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class ResourceConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${security.oauth2.client.access-token-uri}")
	private String accessTokenUri;
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		super.configure(http);
	}

	@Bean
	public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
		ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
		details.setClientId(clientId);
		details.setClientSecret(clientSecret);
		details.setId("onlinetest-oauth2-resource");
		details.setAccessTokenUri(accessTokenUri);
		details.setGrantType("password");
		details.setUsername("vasu@cognine.com");
		details.setPassword("password");
		details.setScope(Arrays.asList("read", "write", "trust"));
		return details;
		
//		ClientCredentialsResourceDetails details1 = new ClientCredentialsResourceDetails();
//		details1.setClientId(clientId);
//		details1.setClientSecret(clientSecret);
//		details1.setAccessTokenUri(accessTokenUri);
//		details1.setScope(Arrays.asList("read", "write", "trust"));
//		details1.setGrantType("client_credentials");
//		return details1;
		
	}

	@Bean
	public OAuth2RestTemplate restTemplate(OAuth2ClientContext clientContext) throws Exception {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), clientContext);
		disableCertificateChecks(restTemplate);
		return restTemplate;

	}

	private static void disableCertificateChecks(OAuth2RestTemplate oauthTemplate) throws Exception {

		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new Dumb509TrustManager() }, new java.security.SecureRandom());
		ClientHttpRequestFactory requestFactory = new SSLContextRequestFactory(sslContext);

		 HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };

	        // Install the all-trusting host verifier
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		
		
		// This is for OAuth protected resources
		oauthTemplate.setRequestFactory(requestFactory);

		// AuthorizationCodeAccessTokenProvider creates it's own RestTemplate for token
		// operations
		/*AuthorizationCodeAccessTokenProvider provider = new AuthorizationCodeAccessTokenProvider();
		provider.setRequestFactory(requestFactory);
		oauthTemplate.setAccessTokenProvider(provider);*/
		ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
		provider.setRequestFactory(requestFactory);
		oauthTemplate.setAccessTokenProvider(provider);
		
		
		
	}

}
