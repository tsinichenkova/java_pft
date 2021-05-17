package pme.appmanager;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
import java.util.Scanner;

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

    public void cancelCareEvent(String careEventId, String mejiId) throws IOException {

        Properties properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        //Creating a HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Creating a HttpGet object
        HttpPost httppost = new HttpPost(properties.getProperty("careEvent.url"));

        httppost.setHeader("Content-type", "application/json");
        httppost.setHeader("Kpi-User-Token", "IrIlina");
        httppost.setHeader("Kpi-User-Rights", "75020");
        httppost.setHeader("Kpi-Contract-Id", mejiId);
        StringEntity params = new StringEntity("{\"cancelCode\":\"26\", \"careEventId\":\""+careEventId+"\", \"newStatus\":\"2\", \"info\": \"\"}");
        httppost.setEntity(params);

        //Executing the Get request
        HttpResponse httpresponse = httpclient.execute(httppost);

        Scanner sc = new Scanner(httpresponse.getEntity().getContent());

        //Printing the status line
        System.out.println(httpresponse.getStatusLine());
        while(sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }

}
