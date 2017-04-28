package main.base.solr;

import java.net.MalformedURLException;

/**
 * Created by zhangbo on 2017/4/10.
 */
public interface ISolrClient {

    void SetSolrHost();

    void SetSolrCore(String core);

    void InitSolrClient(String host, String core) throws MalformedURLException;
}
