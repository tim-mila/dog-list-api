package com.alimmit.reactlistapi.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class DogApiClient {

    private static final Log LOG = LogFactory.getLog(DogApiClient.class);

    private final static String SERVICE_URL = "https://dog.ceo";
    private final static String ENCODING = "UTF-8";
    private final static ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public <T> T get(final String path, final Class<T> type, final Object...uriVariables) {
        return doRequest(fullyQualifiedUrl(path), HttpMethod.GET, type, uriVariables).getBody();
    }

    private <T> ResponseEntity<T> doRequest(final String fullyQualifiedUrl, final HttpMethod httpMethod, final Class<T> type, final Object...uriVariables) {
        return buildRestTemplate().exchange(fullyQualifiedUrl, httpMethod, new HttpEntity<>(new HttpHeaders()), type, uriVariables);
    }

    private String fullyQualifiedUrl(final String path) {
        return SERVICE_URL + path;
    }

    private RestTemplate buildRestTemplate() {

        final SSLContext sslContext = SSLContexts.createDefault();
        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.1", "TLSv1.2"}, null, new NoopHostnameVerifier());
        final CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        final RestTemplate newRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));

        final List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();

        final MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        jacksonConverter.setObjectMapper(MAPPER);
        converters.add(jacksonConverter);

        final FormHttpMessageConverter formConverter = new FormHttpMessageConverter();
        formConverter.setCharset(Charset.forName(ENCODING));
        formConverter.addPartConverter(jacksonConverter);
        converters.add(formConverter);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new StringHttpMessageConverter(Charset.forName(ENCODING)));
        newRestTemplate.setMessageConverters(converters);

//        newRestTemplate.setErrorHandler(new SalesforceHttpErrorHandler(authenticationToken));
        return newRestTemplate;
    }
}
