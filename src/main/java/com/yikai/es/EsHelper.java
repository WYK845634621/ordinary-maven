package com.yikai.es;

import org.apache.http.HttpHost;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
//            jsonMap.put("user", "hy");
//            jsonMap.put("postDate", new Date());
//            jsonMap.put("message", "messs from mc ...");
//
////            文档API
//            IndexRequest indexRequest = new IndexRequest("hello-ccc", "doc", "8")
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
            //匹配查询
//            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("user", "kimchy");
//            matchQueryBuilder.fuzziness(Fuzziness.AUTO).prefixLength(4).maxExpansions(10);
//            builder.query(matchQueryBuilder);
//            builder.query(QueryBuilders.termQuery("user", "kimchy"));
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            boolQueryBuilder.should(QueryBuilders.wildcardQuery("user","mc*"));
//            boolQueryBuilder.should(QueryBuilders.wildcardQuery("user","*k*"));
            //模糊查询
            WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("message", "*");
            boolQueryBuilder.must(wildcardQueryBuilder);
            boolQueryBuilder.must(QueryBuilders.rangeQuery("postDate").gte(new Date()).lte(new Date()));
            builder.query(boolQueryBuilder);
            builder.timeout(new TimeValue(60, TimeUnit.SECONDS)).from(0).size(5);
            builder.sort(new FieldSortBuilder("_id").order(SortOrder.DESC));
            //不按照自定义的返回结果
//            builder.fetchSource(false);
            //自定义返回字段
//            builder.fetchSource(new String[]{"host","@version","message"},null);
            SearchRequest searchRequest = new SearchRequest();
            //指定索引
            searchRequest.indices("hello-ccc");
            searchRequest.source(builder);

            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(response.toString());
//            SearchHit[] hits = response.getHits().getHits();
//            for (SearchHit hit : hits){
//                System.out.println(hit.toString());
//            }

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
