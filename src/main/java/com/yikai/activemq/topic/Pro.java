package com.yikai.activemq.topic;

import com.yikai.util.CommonUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/13 11:10
 */
public class Pro {
    public static final String ACTIVEMQ_URL = "tcp://192.168.187.129:61616";

    public static final String TOPIC_NAME = "topic_yikai_001";

    public static void main(String[] args) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(TOPIC_NAME);
            producer = session.createProducer(topic);
            for (int i = 0; i < 10; i++) {
                TextMessage text = session.createTextMessage(new Date() + " 来自主题的消息编号:  " + i);
//                text.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                producer.send(text);

            }
            System.out.println("发送成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.close(producer,null,session,connection);
        }
    }
}
