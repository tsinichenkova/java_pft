package pme.appmanager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HttpHelper {
    public HttpHelper() {
    }

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private void close() throws IOException {
        httpClient.close();
    }

    public String checkElastic(String patient) throws Exception {

        Properties properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        HttpGet request = new HttpGet(properties.getProperty("elastic.url") + patient);

        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(properties.getProperty("elastic.username"), properties.getProperty("elastic.password"))
        );

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }

}
