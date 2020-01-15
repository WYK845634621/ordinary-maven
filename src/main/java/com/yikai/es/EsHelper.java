package com.yikai.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/1/15 9:24
 */
public class EsHelper {

    public static void main(String[] args) {
        RestHighLevelClient client = null;
        try {
            client = new RestHighLevelClient(RestClient.builder(new HttpHost("10.253.46.151", 9200, "http")));
            System.out.println("连接成功");

//            Map<String,Object> jsonMap = new HashMap<>();
//            jsonMap.put("user", "kimchy");
//            jsonMap.put("postDate", new Date());
//            jsonMap.put("message", "this is message");

            //文档API
//            IndexRequest indexRequest = new IndexRequest("hello-ccc", "doc", "4")
//                    .source(jsonMap);
//            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//            System.out.println(indexResponse.toString());


//            GetRequest getRequest = new GetRequest("hello-ccc", "doc", "3");
//            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//            if (getResponse.isExists()){
//                System.out.println(getResponse.getSourceAsString());
//                System.out.println(getResponse.toString());
//            }
//
//            //检查文档是否存在
//            boolean exist = client.exists(getRequest, RequestOptions.DEFAULT);
//            System.out.println(exist);


            //Search APIs
            SearchSourceBuilder builder = new SearchSourceBuilder();
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("user", "kimchy");
            builder.query(matchQueryBuilder);
//            builder.query(QueryBuilders.termQuery("user", "kimchy"));
            builder.timeout(new TimeValue(60, TimeUnit.SECONDS)).from(0).size(5);
            builder.sort(new FieldSortBuilder("_id").order(SortOrder.DESC));
            //不按照自定义的返回结果
//            builder.fetchSource(false);
            //自定义返回字段
//            builder.fetchSource(new String[]{"host","@version","message"},null);
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.source(builder);

            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit : hits){
                System.out.println(hit.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
