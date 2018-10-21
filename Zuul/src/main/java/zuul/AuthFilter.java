package zuul;

import javax.validation.Validation;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthFilter extends ZuulFilter {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 6;
	}

	public boolean shouldFilter() {
		System.out.println("printing bearer token");

		RequestContext ctx = RequestContext.getCurrentContext();

		System.out.println(ctx.get("proxy") + "-- > endpoint");
		if (ctx.get("proxy") != null && ( ctx.get("proxy").equals("userservice") || ctx.get("proxy").equals("invoicesservice") || ctx.get("proxy").equals("ordersservice") || ctx.get("proxy").equals("productsservice"))) 
		{
			return true;
		}

		return false;
	}

	public Object run() {

		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			String bearerToken = ctx.getRequest().getHeader("Authorization");
			System.out.println("printing bearer token--> " + bearerToken);
			String token = "";
			if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
				token = bearerToken.substring(7, bearerToken.length());
			}
			final String uri = "http://auth:8092/api/auth/validatetoken";

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

			// MultiValueMap<String, String> map= new
			// LinkedMultiValueMap<String, String>();
			// map.add("token", token);

			// HttpEntity<MultiValueMap<String, String>> request = new
			// HttpEntity<MultiValueMap<String, String>>(map, headers);

			JSONObject json = new JSONObject();
			json.put("token", token);
			HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);

			RestTemplate restTemplate = new RestTemplate();
			// UriComponentsBuilder builder =
			// UriComponentsBuilder.fromHttpUrl(uri)
			// .queryParam("token", token);
			boolean validate = restTemplate.postForObject(uri, entity, boolean.class);
			System.out.println("validated ? -" + validate);
			if ( ( bearerToken == null || !validate)) {
				ctx.setSendZuulResponse(false); // This makes request not
												// forwarding to micro services
				ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
				// Validation validationResponse = new Validation();
				// validationResponse.setSuccess(false);
				// validationResponse.setMessage("Invalid Access...");
				// ObjectMapper mapper = new ObjectMapper();
				// String responseBody =
				// mapper.writeValueAsString(validationResponse);
				// ctx.setResponseBody(validationResponse);
				// ctx.getResponse().setContentType("application/json");
			}
		} catch (Exception e) {
			System.out.print(e.getStackTrace());
		}

		return null;

	}

}
