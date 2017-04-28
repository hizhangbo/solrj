package main.base.solr;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;
//import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhangbo on 2017/4/10.
 */
public abstract class ZqSolrClient<T> implements ISolrClient {

    private String Solr_Host;

    private String Solr_Core;

    private String USER;

    private String PASSWORD;

    protected SolrClient solrClient;

    public ZqSolrClient(String core) {
        SetSolrHost();
        SetSolrCore(core);
        try {
			InitSolrClient(Solr_Host, Solr_Core);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void SetSolrHost() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config/solr.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Solr_Host = p.getProperty("solr.host");
        USER = p.getProperty("solr.user");
        PASSWORD = p.getProperty("solr.password");
    }

    public void SetSolrCore(String core) {
        this.Solr_Core = core;
    }

    public void InitSolrClient(String host, String core) throws MalformedURLException {

//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(
//                AuthScope.ANY,
//                new UsernamePasswordCredentials(USER, PASSWORD));
//
//        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
//
//        solrClient = new HttpSolrClient.Builder(host + '/' + core).withBaseSolrUrl(host + '/' + core).withHttpClient(httpClient).build();

        // preemptive-auth
        //BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(USER, PASSWORD);

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                AuthScope.ANY,
                creds);

        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.addInterceptorFirst(new PreemptiveAuthInterceptor());
        builder.setDefaultCredentialsProvider(credsProvider);
        CloseableHttpClient httpClient = builder.build();

        //6.0以后写法
        //solrClient = new HttpSolrClient.Builder(host + '/' + core).withHttpClient(httpClient).build();
        //5.5
        solrClient = new HttpSolrClient(host + '/' + core, httpClient);
    }

    public abstract T GetModel() throws IOException, SolrServerException, ParseException;

    public abstract T GetModel(Long id) throws IOException, SolrServerException, ParseException;

    public abstract List<T> GetModels() throws IOException, SolrServerException, ParseException;

    public abstract List<T> GetModelsPage(Integer pageNumber, Integer pageSize) throws IOException, SolrServerException, ParseException;

    public abstract List<T> GetModels(Integer pageNumber, Integer pageSize, Map<String, String> parameters) throws IOException, SolrServerException, ParseException;

    public abstract void UpdateModel(T model) throws IOException, SolrServerException, IllegalAccessException;

    public abstract void UpdateModel(Long id, Map<String, Object> field) throws IOException, SolrServerException, IllegalAccessException;

    public abstract void DeleteModel(T model) throws IOException, SolrServerException;

    public abstract void AddModel(T model) throws IllegalAccessException, IOException, SolrServerException;

    public abstract T BuildModel(SolrDocument document) throws ParseException;

    public abstract void RollBack();

    static class PreemptiveAuthInterceptor implements HttpRequestInterceptor {

        public void process(HttpRequest httpRequest, org.apache.http.protocol.HttpContext httpContext) throws HttpException, IOException {
            AuthState authState = (AuthState) httpContext.getAttribute(HttpClientContext.TARGET_AUTH_STATE);
            // If no auth scheme available yet, try to initialize it
            // preemptively
            if (authState.getAuthScheme() == null) {
                CredentialsProvider credsProvider = (CredentialsProvider)
                        httpContext.getAttribute(HttpClientContext.CREDS_PROVIDER);
                HttpHost targetHost = (HttpHost) httpContext.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
                AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
                Credentials creds = credsProvider.getCredentials(authScope);
                if(creds == null){

                }
                authState.update(new BasicScheme(), creds);
            }
        }
    }
}
