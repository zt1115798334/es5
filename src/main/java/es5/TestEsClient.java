package es5;

import java.net.InetAddress;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class TestEsClient {

    public static void main(String[] args) {

        try {

            //设置集群名称
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", false)
                    .put("cluster.name", "elasticsearch").build();
            //创建client
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
            //搜索数据
            SearchResponse sr = client.prepareSearch("song001")
                    .execute().actionGet();
            //输出结果
            SearchHits hits = sr.getHits();
//            for (int i = 0; i < 60; i++) {
                System.out.println(hits.getAt(0).getSource().get("singer"));
//            }
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
