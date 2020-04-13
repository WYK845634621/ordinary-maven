package com.yikai.apis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yikai.entity.Life;
import com.yikai.entity.Weather;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/2/19 14:03
 */
public class TTTT {

    @Test
    public void sss(){
        System.out.println((int)(Math.random()*3));
    }

    @Test
    public void test17(){
        //参数字符串，如果拼接在请求链接之后，需要对中文进行 URLEncode   字符集 UTF-8
        String param = "key=805639d6262d474e95945191cb63b49e&location=CN101100809";
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            //接口地址
            String url = "https://free-api.heweather.net/s6/weather";
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("accept", "*/*");
            //发送参数
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();
            //接收结果
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            //缓冲逐行读取
            while ( ( line = br.readLine() ) != null ) {
                sb.append(line);
            }
//            System.out.println(sb.toString());
            JSONObject ob = JSON.parseObject(sb.toString());
            JSONObject bb = ob.getJSONArray("HeWeather6").getJSONObject(0);

            Weather now = bb.getJSONObject("now").toJavaObject(Weather.class);
            System.out.println(now);
            JSONArray lifestyle = bb.getJSONArray("lifestyle");
            List<Life> lives = lifestyle.toJavaList(Life.class);
            lives.get(0);   //舒适指数
            lives.get(1);   //穿衣指数
            lives.get(3);   //运动指数

        } catch ( Exception ignored ) {
            ignored.printStackTrace();
        } finally {
            //关闭流
            try {
                if(is!=null){
                    is.close();
                }
                if(br!=null){
                    br.close();
                }
                if (out!=null){
                    out.close();
                }
            } catch ( Exception ignored ) {}
        }
    }


    @Test
    public void test(){

        //参数字符串，如果拼接在请求链接之后，需要对中文进行 URLEncode   字符集 UTF-8
        String param = "key=805639d6262d474e95945191cb63b49e&location=CN101100809";
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            //接口地址
            String url = "https://free-api.heweather.net/s6/weather";
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("accept", "*/*");
            //发送参数
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();
            //接收结果
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            //缓冲逐行读取
            while ( ( line = br.readLine() ) != null ) {
                sb.append(line);
            }
//            System.out.println(sb.toString());
            JSONObject ob = JSON.parseObject(sb.toString());
            JSONObject bb = ob.getJSONArray("HeWeather6").getJSONObject(0);

            Weather now = bb.getJSONObject("now").toJavaObject(Weather.class);
            System.out.println(now);
            JSONArray lifestyle = bb.getJSONArray("lifestyle");
            List<Life> lives = lifestyle.toJavaList(Life.class);


            Properties properties = new Properties();
            try {
                properties.setProperty("mail.host", "smtp.aliyun.com");
                properties.setProperty("mail.transport.protocol", "smtp");
                properties.setProperty("mail.smtp.auth", "true");
                Session session = Session.getInstance(properties);
                session.setDebug(true);
                Transport transport = session.getTransport();
                transport.connect("yikai.wang@aliyun.com", "KAI82543083");
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("yikai.wang@aliyun.com", "小楷楷", "utf-8"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("1263928214@qq.com"));
                message.setSubject("爱心提醒");
                Multipart multipart = new MimeMultipart();
                BodyPart bodyPart = new MimeBodyPart();
                String content = "hello, 亲爱滴范范 <br><hr>" + "今日份的天气情况: <br>" + "城市: 运城市垣曲县 <br> ";
                Life l1 = lives.get(0);//舒适指数
                Life l2 = lives.get(1);//穿衣指数
                Life l3 = lives.get(3);//运动指数
                String desc = "舒适指数: " + l1.getBrf() + "<br>" + "生活建议: " + l1.getTxt() + "<br>" + "穿衣指数: " + l2.getBrf() + "<br>" + "穿衣建议: " + l2.getTxt() + "<br>" + "运动指数: " + l3.getBrf() + "<br>" + "运动建议: " + l3.getTxt() + "<br> <hr> <hr>";
                String ff = "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; ———————— 一位不愿透露姓名的程序猿";
                bodyPart.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" + "<hr>" + content + now.toString() + desc + ff, "text/html;charset=UTF-8");
                multipart.addBodyPart(bodyPart);
                message.setContent(multipart, "text/html;charset=utf-8");
                transport.sendMessage(message, message.getAllRecipients());
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch ( Exception ignored ) {
            ignored.printStackTrace();
        } finally {
            //关闭流
            try {
                if(is!=null){
                    is.close();
                }
                if(br!=null){
                    br.close();
                }
                if (out!=null){
                    out.close();
                }
            } catch ( Exception ignored ) {}
        }


    }
}
