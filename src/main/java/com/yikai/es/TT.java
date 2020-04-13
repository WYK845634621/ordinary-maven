package com.yikai.es;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/1/22 15:07
 */
public class TT {
    private static final Log log = LogFactory.getLog(TT.class);

    public static void main(String[] args) {
        Date d = new Date(1580892024720L);
        String[] ss = getIndexName(d, new Date());
        for (String s: ss){
            System.out.println(s);
        }
//        RestHighLevelClient client = null;39190210
//        try {



//            client = new RestHighLevelClient(
//                    RestClient.builder(
//                            new HttpHost("10.253.46.151", 9200, "http")));
//
//
//            SearchRequest searchRequest = new SearchRequest("hello");
//            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//            searchRequest.source(searchSourceBuilder);



//            UpdateRequest request = new UpdateRequest(
//                    "hello",
//                    "doc",
//                    "1001");
//            Person person = new Person();
//            request.doc(JSON.toJSON(person),XContentType.JSON);
//            UpdateResponse updateResponse = client.update(
//                    request, RequestOptions.DEFAULT);
//            System.out.println(updateResponse.toString());

//            GetRequest getRequest = new GetRequest(
//                    "hello",
//                    "doc",
//                    "10010");
//            getRequest.fetchSourceContext(new FetchSourceContext(false));
//            boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
//            System.out.println(exists);

//            GetRequest request = new GetRequest(
//                    "hello",
//                    "doc",
//                    "1001");
//            request.fetchSourceContext(FetchSourceContext.FETCH_SOURCE);
//            GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
//            Map map = getResponse.getSource();
//            System.out.println(map.keySet());

//            IndexRequest request = new IndexRequest("hello","doc","1001");
//            Person person = new Person();
//            request.source(JSON.toJSON(person), XContentType.JSON);
//            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//            System.out.println(response.toString());

//        }catch (Exception e){
//            log.error("err in EsHelper",e);
//        }finally {
//            try {
//                client.close();
//            } catch (IOException e) {
//                log.error("err in EsHelper.finally",e);
//            }
//        }
    }


    public static final String INDEX_PREFIX = "monitor_quota_";

    private static String[] getIndexName(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            String[] indices = new String[1];
            indices[0] = INDEX_PREFIX + "*";
            return indices;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ArrayList<String> strings = new ArrayList<>();
        Date date = calendar.getTime();
        while (true) {
            String dateStr = dateToString(date, "yyyyMMdd");
            strings.add(INDEX_PREFIX + dateStr);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            date = calendar.getTime();
            if (date.getTime() > endTime.getTime()) {
                break;
            }
        }
        String[] result = new String[strings.size()];
        return strings.toArray(result);
    }

    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
