package org.zukdoor.zukvnc.sdk;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.zukdoor.zukvnc.sdk.models.request.SignInRequest;
import org.zukdoor.zukvnc.sdk.models.response.SignInResponse;

public abstract class AbstractClient {
	private final String username;
	private final String password;
	private String token;

	protected AbstractClient(String username, String password, String token) {
		this.username = username;
		this.password = password;
		this.token = token;
	}

	protected RequestBodySpec post(String uri, Object... uriVariables) {
		return new RequestBodySpec(uri, uriVariables);
	}

	protected ResultSpec<Void> get(String uri, Object... uriVariables) {
		var url = UriComponentsBuilder.fromUriString(uri).build(uriVariables);
		var requestEntity = RequestEntity.get(url)
			.header("Authorization", token)
			.build();
		return new ResultSpec<>(requestEntity);
	}

	protected ResultSpec<Void> delete(String uri, Object... uriVariables) {
		var url = UriComponentsBuilder.fromUriString(uri).build(uriVariables);
		var requestEntity = RequestEntity.delete(url)
			.header("Authorization", token)
			.build();
		return new ResultSpec<>(requestEntity);
	}

	public class RequestBodySpec {
		private final String uri;
		private final Object[] uriVariables;

		private RequestBodySpec(String uri, Object[] uriVariables) {
			this.uri = uri;
			this.uriVariables = uriVariables;
		}

		public <R> ResultSpec<R> body(R requestBody) {
			var url = UriComponentsBuilder.fromUriString(uri).build(uriVariables);
			var requestEntity = RequestEntity.post(url)
				.header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON)
				.body(requestBody);
			return new ResultSpec<>(requestEntity);
		}
	}

	public class ResultSpec<R> {
		private final RequestEntity<R> request;

		private ResultSpec(RequestEntity<R> request) {
			this.request = request;
		}

		public <T> T result(Class<T> clazz) {
			var restTemplate = new RestTemplate();
			try {
				return restTemplate.exchange(request,clazz)
					.getBody();
			} catch (HttpClientErrorException.Unauthorized unauthorized) {
				var headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				var httpEntity = new HttpEntity<>(new SignInRequest(username, password), headers);
				token = restTemplate
					.postForObject(Global.SIGN_IN_URL, httpEntity, SignInResponse.class)
					.getToken();
				request.getHeaders().setBearerAuth(token);
				return restTemplate.exchange(request,clazz).getBody();
			}
		}
	}

}
