package test.shxh.business;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.base.solr.ZqSolrClient;
import main.shxh.business.SpxxSolrService;
import main.shxh.domain.Spxx;
import main.utils.solr.SolrUtil;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbo on 2017/4/10.
 */
public class SpxxSolrServiceTest {

    ZqSolrClient<Spxx> solrClient = new SpxxSolrService();

    public SpxxSolrServiceTest() throws MalformedURLException {
    }

    @Test
    public void TestGetModel() throws SolrServerException, ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(solrClient.GetModel()));
    }

    @Test
    public void TestGetModel2() throws SolrServerException, ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(solrClient.GetModel(5L)));
    }

    @Test
    public void TestGetModels() throws SolrServerException, ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(solrClient.GetModels()));
    }

    @Test
    public void TestGetModelsPage() throws SolrServerException, ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(solrClient.GetModelsPage(0,10)));
    }

    @Test
    public void TestGetModels2() throws SolrServerException, ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> params = new HashMap<String, String>();
        params.put("fq", "pm:" + SolrUtil.escapeQueryChars("2000高考总复习金版专辑(1):化学参考答案"));
        System.out.println(mapper.writeValueAsString(solrClient.GetModels(0,10,params)));
    }

    @Test
    public void TestUpdateModel() throws SolrServerException, ParseException, IOException, IllegalAccessException {
        Spxx spxx = (Spxx) solrClient.GetModel(2L);
        spxx.setZz("hello kitty");
        spxx.setCsm("123456");
        solrClient.UpdateModel(spxx);
    }

    @Test
    public void TestUpdateModel2() throws SolrServerException, ParseException, IOException, IllegalAccessException {
        Spxx spxx = (Spxx) solrClient.GetModel(2L);
        HashMap<String, Object> hash = new HashMap<String, Object>();
        hash.put("zz", "哇哇哇");
        solrClient.UpdateModel(spxx.getId(), hash);
    }

    @Test
    public void TestDeleteModel() throws IOException, SolrServerException, ParseException {
        Spxx spxx = (Spxx) solrClient.GetModel(1L);
        solrClient.DeleteModel(spxx);
    }

    @Test
    public void TestAddModel() throws IllegalAccessException, SolrServerException, IOException {
        Spxx spxx = new Spxx();
        spxx.setId(3L);
        spxx.setZz("mdzz");
        spxx.setPm("321");
        solrClient.AddModel(spxx);
    }
}
