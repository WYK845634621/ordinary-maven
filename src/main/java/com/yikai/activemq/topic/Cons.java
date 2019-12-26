package com.yikai.activemq.topic;

import com.yikai.util.CommonUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/13 11:11
 */
public class Cons {
    public static final String ACTIVEMQ_URL = "tcp://192.168.187.129:61616";

    public static final String TOPIC_NAME = "topic_yikai_001";

    public static void main(String[] args) throws IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(TOPIC_NAME);
            consumer = session.createConsumer(topic);
            consumer.setMessageListener((Message message) -> {
                    if (message != null && message instanceof TextMessage){
                        TextMessage t = (TextMessage)message;
                        try {
                            System.out.println(t.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.in.read();
            CommonUtil.close(null,consumer,session,connection);
        }
    }

}
