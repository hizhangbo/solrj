package main.shxh.business;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import main.base.solr.ZqSolrClient;
import main.shxh.domain.Spxx;
import main.utils.common.ReflectHelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangbo on 2017/4/10.
 */
public class SpxxSolrService extends ZqSolrClient<Spxx> {

    public  SpxxSolrService() {
        super("shxh_spxx");
    }

    public SpxxSolrService(String core) {
        super(core);
    }

    public Spxx GetModel() {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","*:*");
        solrQuery.setRows(10);

        QueryResponse queryResponse = null;
		try {
			queryResponse = solrClient.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SolrDocumentList response = queryResponse.getResults();

        List<Spxx> spxxLst = new ArrayList<Spxx>();
        for (SolrDocument item : response)
        {
            Spxx spxx = BuildModel(item);
            spxxLst.add(spxx);
        }

//        List<Book> books = queryResponse.getBeans(Book.class);
        Spxx result = null;
        if(spxxLst!=null && spxxLst.size()>0){
            result = spxxLst.get(0);
        }
        return result;
    }

    public Spxx GetModel(Long id) {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","id:"+id);

        QueryResponse queryResponse = null;
		try {
			queryResponse = solrClient.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SolrDocumentList response = queryResponse.getResults();

        List<Spxx> spxxLst = new ArrayList<Spxx>();
        for (SolrDocument item : response)
        {
            Spxx spxx = BuildModel(item);
            spxxLst.add(spxx);
        }

        Spxx result = null;
        if(spxxLst!=null && spxxLst.size()>0){
            result = spxxLst.get(0);
        }
        return result;
    }

    public List<Spxx> GetModels() {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","*:*");
        solrQuery.setRows(10);

        QueryResponse queryResponse = null;
		try {
			queryResponse = solrClient.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SolrDocumentList response = queryResponse.getResults();

        List<Spxx> spxxLst = new ArrayList<Spxx>();
        for (SolrDocument item : response)
        {
            Spxx spxx = BuildModel(item);
            spxxLst.add(spxx);
        }

//        List<Book> books = queryResponse.getBeans(Book.class);
        return spxxLst;
    }

    public List<Spxx> GetModelsPage(Integer pageNumber, Integer pageSize) {
    	int startIdx = 0;
    	if(pageNumber > 1){
    		startIdx = (pageNumber - 1) * pageSize;
    	}
    	
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","*:*");
        solrQuery.setRows(pageSize);
        solrQuery.setStart(startIdx);

        QueryResponse queryResponse = null;
		try {
			queryResponse = solrClient.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SolrDocumentList response = queryResponse.getResults();

        List<Spxx> spxxLst = new ArrayList<Spxx>();
        for (SolrDocument item : response)
        {
            Spxx spxx = BuildModel(item);
            spxxLst.add(spxx);
        }

//        List<Book> books = queryResponse.getBeans(Book.class);
        return spxxLst;
    }

    public List<Spxx> GetModels(Integer pageNumber, Integer pageSize, Map<String, String> parameters) {
    	int startIdx = 0;
    	if(pageNumber > 1){
    		startIdx = (pageNumber - 1) * pageSize;
    	}
    	
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","*:*");
        solrQuery.setRows(pageSize);
        solrQuery.setStart(startIdx);
        for(Map.Entry<String,String> entry : parameters.entrySet()){
        	solrQuery.setFilterQueries(entry.getKey()+':'+ entry.getValue());
        	
            //solrQuery.setQuery(entry.getKey()+':'+ entry.getValue());
        }

        QueryResponse queryResponse = null;
		try {
			queryResponse = solrClient.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SolrDocumentList response = queryResponse.getResults();

        List<Spxx> spxxLst = new ArrayList<Spxx>();
        for (SolrDocument item : response)
        {
            Spxx spxx = BuildModel(item);
            spxxLst.add(spxx);
        }

//        List<Book> books = queryResponse.getBeans(Book.class);
        return spxxLst;
    }

    public void UpdateModel(Spxx model) {

        HashMap<String, Object> fields = null;
		try {
			fields = ReflectHelper.GetObjectField(model);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SolrInputDocument doc = new SolrInputDocument();

        doc.addField("id", model.getId());
        for (Map.Entry<String, Object> entry:fields.entrySet()){
            if(entry.getKey() != "id"){
                HashMap<String, Object> hash = new HashMap<String, Object>();
                hash.put("set", entry.getValue());

                doc.addField(entry.getKey(), hash);
            }
        }

        try {
			solrClient.add(doc);
			solrClient.commit();
	        //UpdateResponse response = solrClient.commit();
	        //int result = response.getStatus();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void UpdateModel(Long id, Map<String,Object> field) {

        SolrInputDocument doc = new SolrInputDocument();

        doc.addField("id", id);
        for (Map.Entry<String, Object> entry : field.entrySet()){
            HashMap<String, Object> hash = new HashMap<String, Object>();
            hash.put("set", entry.getValue());
            doc.addField(entry.getKey(), hash);
        }

        try {
			solrClient.add(doc);
	        solrClient.commit();
	        //UpdateResponse response = solrClient.commit();
	        //int result = response.getStatus();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void DeleteModel(Spxx model) {
        try {
			solrClient.deleteById(model.getId().toString());
			solrClient.commit();
	        //UpdateResponse response = solrClient.commit();
	        //int result = response.getStatus();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void AddModel(Spxx model) {
        Map<String, Object> fields = null;
		try {
			fields = ReflectHelper.GetObjectField(model);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        SolrInputDocument doc = new SolrInputDocument();
        for (Map.Entry<String, Object> entry:fields.entrySet()){
            doc.addField(entry.getKey(), entry.getValue());
        }
        try {
			solrClient.add(doc);
			solrClient.commit();
	        //UpdateResponse response = solrClient.commit();
	        //int result = response.getStatus();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Spxx BuildModel(SolrDocument document) {
        Spxx spxx = new Spxx();
        spxx.setZz(document.getFirstValue("zz") != null ? document.getFirstValue("zz").toString() : "");
        spxx.setBc(document.getFirstValue("bc") != null ? document.getFirstValue("bc").toString() : "");
        spxx.setDj(document.getFirstValue("dj") != null ? Double.parseDouble(document.getFirstValue("dj").toString()) : 0);
        spxx.setSpxxid(document.getFirstValue("spxxid") != null ? document.getFirstValue("spxxid").toString() : "");
        spxx.setIsbn(document.getFirstValue("isbn") != null ? document.getFirstValue("isbn").toString() : "");
        spxx.setCbn(document.getFirstValue("cbn") != null ? Short.parseShort(document.getFirstValue("cbn").toString()) : 0);
        spxx.setCbsid(document.getFirstValue("cbsid") != null ? document.getFirstValue("cbsid").toString() : "");
        spxx.setLen(document.getFirstValue("len") != null ? Integer.parseInt(document.getFirstValue("len").toString()) : 0);
        
        if(document.getFirstValue("sdhrq") != null){
            String dateStr = document.getFirstValue("sdhrq").toString();
/*
 * Java8:
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
            LocalDateTime date = LocalDateTime.parse(document.getFirstValue("sdhrq").toString(), formatter);
            Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
            spxx.setSdhrq(Date.from(instant));
*/
			try {
	            spxx.setSdhrq(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US).parse(dateStr));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //过期方法 Date date = new Date(dateStr);
        }
        spxx.setCbny(document.getFirstValue("cbny") != null ? Integer.parseInt(document.getFirstValue("cbny").toString()) : 0);
        spxx.setId(Long.parseLong(document.getFirstValue("id").toString()));
        spxx.setCsm(document.getFirstValue("csm") != null ? document.getFirstValue("csm").toString() : "");
        spxx.setPm(document.getFirstValue("pm") != null ? document.getFirstValue("pm").toString() : "");
        return spxx;
    }

    public void RollBack() {

    }
}

